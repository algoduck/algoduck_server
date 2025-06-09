package com.koreii.algoduck.submission.controller;

import com.koreii.algoduck.base.controller.BaseApiController;
import com.koreii.algoduck.base.dto.page.PageResponse;
import com.koreii.algoduck.base.dto.response.ApiResponse;
import com.koreii.algoduck.member.security.CustomUserDetails;
import com.koreii.algoduck.submission.dto.request.SubmissionRequestDto;
import com.koreii.algoduck.submission.dto.response.JudgeProgressDto;
import com.koreii.algoduck.submission.dto.response.SubmissionResponseDto;
import com.koreii.algoduck.submission.service.SubmissionService;
import com.koreii.algoduck.submission.sse.SubmissionProgressEmitter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/submissions")
@Tag(name = "Submission", description = "문제 제출 관련 API")
@Slf4j
public class SubmissionController extends BaseApiController {
  private final SubmissionService submissionService;
  private final SubmissionProgressEmitter submissionProgressEmitter;

  @Operation(summary = "제출", description = "문제에 대한 코드를 제출합니다.")
  @PostMapping
  public ResponseEntity<ApiResponse<SubmissionResponseDto>> submit(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody SubmissionRequestDto submissionRequestDto) {
    if (userDetails == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.failure("로그인이 필요합니다."));
    }

    SubmissionResponseDto submissionResponseDto = submissionService.submit(submissionRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(submissionResponseDto));
  }

  // 첫 페이지 (lastSeenId 없이)
  @GetMapping("/page")
  public ResponseEntity<ApiResponse<PageResponse<SubmissionResponseDto>>> getPage(
      @RequestParam(required = false) Long lastSeenId,
      @RequestParam(required = false) Long firstSeenId,
      @RequestParam(defaultValue = "20") int size
  ) {
    PageResponse<SubmissionResponseDto> page;

    if (lastSeenId == null && firstSeenId == null) {
      page = submissionService.getFirstPage(size);
    } else if (lastSeenId != null) {
      page = submissionService.getNextPage(lastSeenId, size);
    } else {
      page = submissionService.getPrevPage(firstSeenId, size);
    }

    return ResponseEntity.ok(ApiResponse.success(page));
  }

  @Operation(summary = "회원 제출 목록 페이지 조회", description = "특정 회원의 제출 목록을 커서 기반으로 조회합니다.")
  @GetMapping("/page/member/{memberId}")
  public ResponseEntity<ApiResponse<PageResponse<SubmissionResponseDto>>> getPageByMemberId(
      @PathVariable Long memberId,
      @RequestParam(required = false) Long lastSeenId,
      @RequestParam(required = false) Long firstSeenId,
      @RequestParam(defaultValue = "20") int size
  ) {
    PageResponse<SubmissionResponseDto> page;

    if (lastSeenId == null && firstSeenId == null) {
      // 첫 페이지는 최신순 정렬로 조회
      page = submissionService.getNextPageByMemberId(memberId, null, size);
    } else if (lastSeenId != null) {
      page = submissionService.getNextPageByMemberId(memberId, lastSeenId, size);
    } else {
      page = submissionService.getPrevPageByMemberId(memberId, firstSeenId, size);
    }

    return ResponseEntity.ok(ApiResponse.success(page));
  }


  @GetMapping("/{submissionId}/code")
  public ResponseEntity<ApiResponse<String>> getCode(
      @PathVariable Long submissionId
  ) {
    String code = submissionService.getCode(submissionId);
    return ResponseEntity.ok(ApiResponse.success(code));
  }

  @GetMapping("/{submissionId}/progress")
  public SseEmitter subscribe(@PathVariable Long submissionId) {
    return submissionProgressEmitter.createEmitter(submissionId);
  }
}

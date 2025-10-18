package com.koreii.algoduck.submission.controller;

import com.koreii.algoduck.base.controller.BaseApiController;
import com.koreii.algoduck.base.dto.page.PageResponse;
import com.koreii.algoduck.base.dto.response.ApiResponse;
import com.koreii.algoduck.member.security.CustomUserDetails;
import com.koreii.algoduck.solved.service.SolvedProblemService;
import com.koreii.algoduck.submission.dto.request.SubmissionRequestDto;
import com.koreii.algoduck.submission.dto.response.SubmissionResponseDto;
import com.koreii.algoduck.submission.service.SubmissionService;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/submissions")
@Tag(name = "Submission", description = "문제 제출 관련 API")
@Slf4j
public class SubmissionController extends BaseApiController {
  private final SubmissionService submissionService;
  private final SolvedProblemService solvedProblemService;

  @Operation(summary = "제출", description = "문제에 대한 코드를 제출합니다.")
  @PostMapping
  public ResponseEntity<ApiResponse<SubmissionResponseDto>> submit(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody SubmissionRequestDto submissionRequestDto) {
    if (userDetails == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.failure("로그인이 필요합니다."));
    }

    SubmissionResponseDto submissionResponseDto = submissionService.submit(submissionRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(submissionResponseDto));
  }

  @Operation(summary = "제출 id로 제출 내역 가져오기", description = "제출 id로 특정 제출을 가져옵니다.")
  @GetMapping("/{submissionId}")
  public ResponseEntity<ApiResponse<SubmissionResponseDto>> getSubmission(
      @PathVariable Long submissionId
  ) {
    SubmissionResponseDto submissionResponseDto = submissionService.findBySubmissionId(submissionId);
    return ResponseEntity.ok(ApiResponse.success(submissionResponseDto));
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

  @GetMapping("/search")
  public ResponseEntity<ApiResponse<PageResponse<SubmissionResponseDto>>> searchSubmission(
      @RequestParam(required = false) String loginId,
      @RequestParam(required = false) Long problemNumber,
      @RequestParam(required = false) String status,
      @RequestParam(required = false) String language,
      @RequestParam(required = false) Long submissionId,
      @RequestParam(required = false) Long lastSeenId,
      @RequestParam(required = false) Long firstSeenId,
      @RequestParam(defaultValue = "20") int size
  ) {
    PageResponse<SubmissionResponseDto> page;

    boolean noSearchConditions = loginId == null && problemNumber == null && status == null && language == null && submissionId == null;

    if (noSearchConditions) {
      // getPage()를 직접 호출하지 말고, 그 내부에서 쓰는 service 메서드 호출
      PageResponse<SubmissionResponseDto> firstPage = submissionService.getFirstPage(size);
      return ResponseEntity.ok(ApiResponse.success(firstPage));
    }

    //  제출 번호로 단건 조회
    if (submissionId != null) {
      SubmissionResponseDto submissionResponseDto = submissionService.findBySubmissionId(submissionId);
      List<SubmissionResponseDto> list = submissionResponseDto == null
          ? List.of()
          : List.of(submissionResponseDto);
      page = PageResponse.of(list, false, false, list.size());
      return ResponseEntity.ok(ApiResponse.success(page));
    }

    //  나머지 조건 조합 검색
    if (lastSeenId == null && firstSeenId == null) { //  첫 페이지
      page = submissionService.searchSubmissions(loginId, problemNumber, status, language, null, null, size);
    } else if (lastSeenId != null) {
      page = submissionService.searchSubmissions(loginId, problemNumber, status, language, lastSeenId, null, size);
    } else {
      page = submissionService.searchSubmissions(loginId, problemNumber, status, language, null, firstSeenId, size);
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
      @PathVariable Long submissionId,
      @AuthenticationPrincipal CustomUserDetails userDetails
  ) {
    //  1. 로그인 여부 확인
    if (userDetails == null) {
      return ResponseEntity
          .status(HttpStatus.UNAUTHORIZED)
          .body(ApiResponse.failure("로그인이 필요합니다."));
    }

    Long loginMemberId = userDetails.getMemberId(); //  코드 확인하려는 회원 PK
    //  log.info("코드 확인 시도 memberId = {}", loginMemberId);

    SubmissionResponseDto submissionResponseDto = submissionService.findBySubmissionId(submissionId);

    Long ownerId = submissionResponseDto.getMemberId();     //  코드를 제출한 회원 PK

    //  log.info("코드 주인 memberId = {}", ownerId);

    Long problemId = submissionResponseDto.getProblemId();  //  코드를 제출한 문제 PK
    //  log.info("코드에 해당하는 problemId = {}", problemId);

    if (!loginMemberId.equals(ownerId) && !solvedProblemService.hasSolved(loginMemberId, problemId)) {  //  본인이 제출한 코드가 아니고, 본인이 푼 문제도 아닐 경우
      return ResponseEntity
          .status(HttpStatus.FORBIDDEN)
          .body(ApiResponse.failure("해당 문제를 푼 경우에만 다른 사람의 코드를 볼 수 있습니다."));
    }

    String code = submissionService.getCode(submissionId);
    return ResponseEntity.ok(ApiResponse.success(code));
  }
}

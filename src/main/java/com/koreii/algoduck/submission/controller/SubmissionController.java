package com.koreii.algoduck.submission.controller;

import com.koreii.algoduck.base.controller.BaseApiController;
import com.koreii.algoduck.base.dto.page.PageResponse;
import com.koreii.algoduck.base.dto.response.ApiResponse;
import com.koreii.algoduck.submission.dto.request.SubmissionRequestDto;
import com.koreii.algoduck.submission.dto.response.SubmissionResponseDto;
import com.koreii.algoduck.submission.service.SubmissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

  @Operation(summary = "제출", description = "문제에 대한 코드를 제출합니다.")
  @PostMapping
  public ResponseEntity<ApiResponse<SubmissionResponseDto>> submit(@RequestBody SubmissionRequestDto submissionRequestDto) {
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
}

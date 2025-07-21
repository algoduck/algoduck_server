package com.koreii.algoduck.submission.controller;

import com.koreii.algoduck.base.dto.response.ApiResponse;
import com.koreii.algoduck.submission.dto.request.SubmissionRequestDto;
import com.koreii.algoduck.submission.dto.response.SubmissionResponseDto;
import com.koreii.algoduck.submission.service.SubmissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/submissions/test")
@Tag(name = "Submission Test", description = "문제 제출 테스트 컨트롤러")
@Profile({"test", "develop"})
@Slf4j
public class TestSubmissionController {
  private final SubmissionService submissionService;

  @Operation(summary = "테스트 제출", description = "문제에 대한 코드를 제출합니다. 로그인이 없이도 제출할 수 있습니다.")
  @PostMapping
  public ResponseEntity<ApiResponse<SubmissionResponseDto>> submit(@RequestBody SubmissionRequestDto submissionRequestDto) {
    SubmissionResponseDto submissionResponseDto = submissionService.submit(submissionRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(submissionResponseDto));
  }

  @Operation(summary = "제출 id로 제출 내역 가져오기", description = "제출 id로 특정 제출을 가져옵니다.")
  @GetMapping("/{submissionId}")
  public ResponseEntity<ApiResponse<SubmissionResponseDto>> getWithoutLogin(
      @PathVariable Long submissionId) {
    SubmissionResponseDto response = submissionService.findBySubmissionId(submissionId);
    return ResponseEntity.ok(ApiResponse.success(response));
  }
}

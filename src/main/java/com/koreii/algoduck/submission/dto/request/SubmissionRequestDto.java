package com.koreii.algoduck.submission.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class SubmissionRequestDto {
  private Long memberId;
  private Long problemId;
  private String sourceCode;
  private Long versionId;
}

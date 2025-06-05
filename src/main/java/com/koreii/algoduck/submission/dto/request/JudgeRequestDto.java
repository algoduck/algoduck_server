package com.koreii.algoduck.submission.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class JudgeRequestDto {
  private Long problemId;
  private Long submissionId;
  private String language;
  private String version;
  private int timeLimitation;
  private int memoryLimitation;
  private String sourceCode;
}

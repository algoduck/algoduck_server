package com.koreii.algoduck.submission.dto.request;

import com.koreii.algoduck.submission.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class SubmissionUpdateRequestDto {
  private Long submissionId;
  private String codeName;
  private String codeUrl;
  private Status status;
  private String message;
  private Integer executionTime;
  private Integer memoryUsage;
}

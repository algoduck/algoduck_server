package com.koreii.algoduck.submission.dto.response;

import com.koreii.algoduck.submission.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class SubmissionSaveResponseDto {
  private Long submissionId;
  private Long memberId;
  private Long
  private Status status;
  private String message;
}

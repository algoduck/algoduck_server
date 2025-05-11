package com.koreii.algoduck.submission.dto.response;

import com.koreii.algoduck.submission.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class SubmissionResponseDto {
  private Status status;
  private String message;
}

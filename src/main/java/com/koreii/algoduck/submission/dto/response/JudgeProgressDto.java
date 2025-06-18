package com.koreii.algoduck.submission.dto.response;

import com.koreii.algoduck.submission.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class JudgeProgressDto {
  private int index;
  private Long submissionId;
  private String result;
  private String message;
  private String stdout;
  private String stderr;
  private Integer executionTime;
  private Integer memoryUsage;
  private int percentage;
}

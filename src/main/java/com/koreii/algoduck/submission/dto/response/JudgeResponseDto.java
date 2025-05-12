package com.koreii.algoduck.submission.dto.response;

import com.koreii.algoduck.submission.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class JudgeResponseDto {
  private Status result;
  private String message;
  private String stdout;
  private String stderr;
  private Integer executionTime;
  private Integer memoryUsage;
}

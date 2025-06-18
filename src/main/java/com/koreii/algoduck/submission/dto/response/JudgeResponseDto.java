package com.koreii.algoduck.submission.dto.response;

import com.koreii.algoduck.submission.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

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

  public JudgeResponseDto(JudgeProgressDto judgeProgressDto) {
    this.result = Status.valueOf(judgeProgressDto.getResult());
    this.message = judgeProgressDto.getMessage();
    this.stdout = judgeProgressDto.getStdout();
    this.stderr = judgeProgressDto.getStderr();
    this.executionTime = judgeProgressDto.getExecutionTime();
    this.memoryUsage = judgeProgressDto.getMemoryUsage();
  }
}

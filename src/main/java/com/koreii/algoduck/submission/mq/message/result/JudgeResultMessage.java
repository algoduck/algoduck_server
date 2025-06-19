package com.koreii.algoduck.submission.mq.message.result;

import com.koreii.algoduck.submission.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class JudgeResultMessage {
  private Long problemId;
  private Long submissionId;
  private Status result;
  private String message;
  private String stdout;
  private String stderr;
  private Integer executionTime;
  private Integer memoryUsage;
}

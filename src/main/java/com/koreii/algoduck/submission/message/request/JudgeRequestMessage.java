package com.koreii.algoduck.submission.message.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JudgeRequestMessage {
  private Long problemId;
  private Long submissionId;
  private String language;
  private String version;
  private int timeLimitation;
  private int memoryLimitation;
  private String sourceCode;
}

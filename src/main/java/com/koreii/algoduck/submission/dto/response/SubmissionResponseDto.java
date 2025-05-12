package com.koreii.algoduck.submission.dto.response;

import com.koreii.algoduck.submission.entity.Submission;
import com.koreii.algoduck.submission.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class SubmissionResponseDto {
  private Long submissionId;
  private Long memberId;
  private String loginId;
  private String nickname;
  private Long problemId;
  private String problemTitle;
  private Status status;
  private String message;
  private Integer executionTime;
  private Integer memoryUsage;
  private String codeName;
  private String codeUrl;

  public SubmissionResponseDto(Submission submission) {
    this.submissionId = submission.getSubmissionId();
    this.memberId = submission.getMember().getMemberId();
    this.loginId = submission.getMember().getLoginId();
    this.nickname = submission.getMember().getNickname();
    this.problemId = submission.getProblem().getProblemId();
    this.problemTitle = submission.getProblem().getTitle();
    this.status = submission.getStatus();
    this.message = submission.getMessage();
    this.codeName = submission.getCodeName();
    this.codeUrl = submission.getCodeUrl();
  }
}

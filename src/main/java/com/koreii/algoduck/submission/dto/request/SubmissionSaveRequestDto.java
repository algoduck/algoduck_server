package com.koreii.algoduck.submission.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class SubmissionSaveRequestDto {
  private Long memberId;
  private Long problemId;
  private Long versionId;

  public SubmissionSaveRequestDto(SubmissionRequestDto submissionRequestDto) {
    this.memberId = submissionRequestDto.getMemberId();
    this.problemId = submissionRequestDto.getProblemId();
    this.versionId = submissionRequestDto.getVersionId();
  }
}

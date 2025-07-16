package com.koreii.algoduck.solved.dto.response;

import com.koreii.algoduck.solved.entity.SolvedProblem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SolvedProblemResponseDto {
  private Long solvedProblemId;
  private Long memberId;
  private Long problemId;

  public SolvedProblemResponseDto(SolvedProblem solvedProblem) {
    this.solvedProblemId = solvedProblem.getSolvedProblemId();
    this.memberId = solvedProblem.getMember().getMemberId();
    this.problemId = solvedProblem.getProblem().getProblemId();
  }
}

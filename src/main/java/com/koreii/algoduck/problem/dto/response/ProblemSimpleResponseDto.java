package com.koreii.algoduck.problem.dto.response;

import com.koreii.algoduck.problem.entity.Problem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ProblemSimpleResponseDto {
  private Long problemId;
  private String title;
  private String problemNumber;
  private Integer difficulty;

  public ProblemSimpleResponseDto(Problem problem) {
    this.problemId = problem.getProblemId();
    this.title = problem.getTitle();
    this.problemNumber = problem.getProblemNumber();
    this.difficulty = problem.getDifficulty();
  }
}

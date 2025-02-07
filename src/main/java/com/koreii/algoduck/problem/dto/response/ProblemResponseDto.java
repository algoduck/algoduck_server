package com.koreii.algoduck.problem.dto.response;

import com.koreii.algoduck.problem.entity.Problem;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
public class ProblemResponseDto {
  private Long problemId;
  private String title;
  private Integer problemNumber;
  private String description;
  private String inputDescription;
  private String outputDescription;
  private Integer difficulty;

  public ProblemResponseDto(Problem problem) {
    this.problemId = problem.getProblemId();
    this.title = problem.getTitle();
    this.problemNumber = problem.getProblemNumber();
    this.description = problem.getDescription();
    this.inputDescription = problem.getInputDescription();
    this.outputDescription = problem.getOutputDescription();
    this.difficulty = problem.getDifficulty();
  }
}

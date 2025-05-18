package com.koreii.algoduck.problem.dto.response;

import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.testcase.dto.response.TestcaseResponseDto;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ProblemResponseDto {
  private Long problemId;
  private String title;
  private String problemNumber;
  private String description;
  private String inputDescription;
  private String outputDescription;
  private Integer difficulty;
  private Integer timeLimitation;
  private Integer memoryLimitation;
  @Setter
  private List<TestcaseResponseDto> testcaseResponseDtoList;

  public ProblemResponseDto(Problem problem) {
    this.problemId = problem.getProblemId();
    this.title = problem.getTitle();
    this.problemNumber = problem.getProblemNumber();
    this.description = problem.getDescription();
    this.inputDescription = problem.getInputDescription();
    this.outputDescription = problem.getOutputDescription();
    this.difficulty = problem.getDifficulty();
    this.timeLimitation = problem.getTimeLimitation();
    this.memoryLimitation = problem.getMemoryLimitation();
    this.testcaseResponseDtoList = new ArrayList<>();
  }
}

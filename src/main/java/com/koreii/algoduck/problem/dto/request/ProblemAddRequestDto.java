package com.koreii.algoduck.problem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ProblemAddRequestDto {
  private String problemNumber;
  private String title;
  private String description;
  private String inputDescription;
  private String outputDescription;
  private Integer difficulty;
}

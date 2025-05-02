package com.koreii.algoduck.problem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProblemPagingResponseDto {
  private long totalCount;
  private List<ProblemSimpleResponseDto> problems;
}

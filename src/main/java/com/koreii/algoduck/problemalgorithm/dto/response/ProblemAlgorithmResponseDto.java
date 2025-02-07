package com.koreii.algoduck.problemalgorithm.dto.response;

import com.koreii.algoduck.problemalgorithm.entity.ProblemAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ProblemAlgorithmResponseDto {
  public Long problemAlgorithmId;
  public Long problemId;
  public Long algorithmId;

  public ProblemAlgorithmResponseDto(ProblemAlgorithm problemAlgorithm) {
    this.problemAlgorithmId = problemAlgorithm.getProblemAlgorithmId();
    this.problemId = problemAlgorithm.getProblemAlgorithmId();
    this.algorithmId = problemAlgorithm.getProblemAlgorithmId();
  }
}

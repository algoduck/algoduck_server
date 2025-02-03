package com.koreii.algoduck.algorithm.dto.response;

import com.koreii.algoduck.algorithm.entity.Algorithm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class AlgorithmResponseDto {
  private Long algorithmId;
  private String algorithmName;

  public AlgorithmResponseDto(Algorithm algorithm) {
    this.algorithmId = algorithm.getAlgorithmId();
    this.algorithmName = getAlgorithmName();
  }
}

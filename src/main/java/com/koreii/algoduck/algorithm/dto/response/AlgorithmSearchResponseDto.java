package com.koreii.algoduck.algorithm.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlgorithmSearchResponseDto {
  private long totalCount;
  private List<AlgorithmResponseDto> algorithms;
}

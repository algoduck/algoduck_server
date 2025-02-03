package com.koreii.algoduck.algorithm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class AlgorithmAddRequestDto {
  private String algorithmName;
  private List<String> aliases;
}

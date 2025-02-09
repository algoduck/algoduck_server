package com.koreii.algoduck.problemimage.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ProblemImageAddRequestDto {
  private String problemImageName;
  private String problemImageUrl;
}

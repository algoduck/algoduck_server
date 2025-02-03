package com.koreii.algoduck.alias.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class AliasAddRequestDto {
  private Long algorithmId;
  private String aliasName;
}

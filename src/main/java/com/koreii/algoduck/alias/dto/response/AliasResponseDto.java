package com.koreii.algoduck.alias.dto.response;

import com.koreii.algoduck.alias.entity.Alias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class AliasResponseDto {
  private Long aliasId;
  private Long algorithmId;
  private String aliasName;

  public AliasResponseDto(Alias alias) {
    this.aliasId = alias.getAliasId();
    this.algorithmId = alias.getAlgorithm().getAlgorithmId();
    this.aliasName = alias.getAliasName();
  }
}

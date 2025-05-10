package com.koreii.algoduck.language.dto.response;

import com.koreii.algoduck.language.entity.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class LanguageResponseDto {
  private Long languageId;
  private String name;

  public LanguageResponseDto(Language language) {
    this.languageId = language.getLanguageId();
    this.name = language.getName();
  }
}

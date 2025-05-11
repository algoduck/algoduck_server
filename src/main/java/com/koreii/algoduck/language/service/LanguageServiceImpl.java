package com.koreii.algoduck.language.service;

import com.koreii.algoduck.language.dto.response.LanguageResponseDto;
import com.koreii.algoduck.language.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
  private final LanguageRepository languageRepository;

  @Override
  public LanguageResponseDto findByLanguageId(Long languageId) {
    return new LanguageResponseDto(languageRepository.findByLanguageId(languageId));
  }
}

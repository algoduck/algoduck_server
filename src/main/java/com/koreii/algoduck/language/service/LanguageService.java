package com.koreii.algoduck.language.service;

import com.koreii.algoduck.language.dto.response.LanguageResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface LanguageService {
  LanguageResponseDto findLanguageByLanguageId(Long languageId);
}

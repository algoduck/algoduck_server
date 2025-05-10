package com.koreii.algoduck.language.repository;

import com.koreii.algoduck.language.entity.Language;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository {
  Language findByLanguageId(Long languageId);
}

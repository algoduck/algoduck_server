package com.koreii.algoduck.language.repository;

import com.koreii.algoduck.language.entity.Language;
import com.koreii.algoduck.problem.entity.Problem;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LanguageRepositoryJpaImpl implements LanguageRepository {
  private final EntityManager entityManager;

  @Override
  public Language findByLanguageId(Long languageId) {
    return entityManager.find(Language.class, languageId);
  }
}

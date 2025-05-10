package com.koreii.algoduck.version.repository;

import com.koreii.algoduck.version.entity.Version;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class VersionRepositoryImpl implements VersionRepository {
  private final EntityManager entityManager;

  @Override
  public Version findByVersionId(Long versionId) {
    return entityManager.find(Version.class, versionId);
  }
}

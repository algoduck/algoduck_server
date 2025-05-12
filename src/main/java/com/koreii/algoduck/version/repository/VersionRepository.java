package com.koreii.algoduck.version.repository;

import org.springframework.stereotype.Repository;

import com.koreii.algoduck.version.entity.Version;

@Repository
public interface VersionRepository {
  Version findByVersionId(Long versionId);
}

package com.koreii.algoduck.version.service;

import com.koreii.algoduck.version.dto.response.VersionResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface VersionService {
  VersionResponseDto findByVersionId(Long versionId);
}

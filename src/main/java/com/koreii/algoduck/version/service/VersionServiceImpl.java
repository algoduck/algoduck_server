package com.koreii.algoduck.version.service;

import com.koreii.algoduck.version.dto.response.VersionResponseDto;
import com.koreii.algoduck.version.repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VersionServiceImpl implements VersionService {
  private final VersionRepository versionRepository;

  @Override
  public VersionResponseDto findByVersionId(Long versionId) {
    return new VersionResponseDto(versionRepository.findByVersionId(versionId));
  }
}

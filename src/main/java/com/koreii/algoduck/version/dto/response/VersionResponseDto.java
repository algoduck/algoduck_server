package com.koreii.algoduck.version.dto.response;

import com.koreii.algoduck.version.entity.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class VersionResponseDto {
  private Long versionId;
  private String versionName;

  public VersionResponseDto(Version version) {
    this.versionId = version.getVersionId();
    this.versionName = version.getVersionName();
  }
}

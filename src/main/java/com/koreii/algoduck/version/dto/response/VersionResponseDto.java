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
  private String languageName;
  private String extension;
  private String versionName;

  public VersionResponseDto(Version version) {
    this.versionId = version.getVersionId();
    this.languageName = version.getLanguage().getName();
    this.extension = version.getLanguage().getExtension();
    this.versionName = version.getVersionName();
  }
}

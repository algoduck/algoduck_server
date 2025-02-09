package com.koreii.algoduck.testcase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class TestcaseAddRequestDto {
  private String testcaseInputName;
  private String testcaseInputUrl;
  private String testcaseOutputName;
  private String testcaseOutputUrl;
  private boolean isPublic;
}

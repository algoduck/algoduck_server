package com.koreii.algoduck.submission.dto.request;

import com.koreii.algoduck.testcase.dto.request.TestcaseRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class JudgeRequestDto {
  private Long problemId;
  private Long problemNumber;
  private String sourceCode;
  private String languageName;
  private String versionName;
  private List<TestcaseRequestDto> testcaseList;
}

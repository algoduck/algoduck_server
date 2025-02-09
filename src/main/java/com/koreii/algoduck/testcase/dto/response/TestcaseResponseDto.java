package com.koreii.algoduck.testcase.dto.response;

import com.koreii.algoduck.testcase.entitiy.Testcase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class TestcaseResponseDto {
  private Long testcaseId;
  private Long problemId;
  private String testcaseInputName;
  private String testcaseInputUrl;
  private String testcaseOutputName;
  private String testcaseOutputUrl;
  private boolean isPublic;

  public TestcaseResponseDto(Testcase testcase) {
    this.testcaseId = testcase.getTestcaseId();
    this.problemId = testcase.getProblem().getProblemId();
    this.testcaseInputName = testcase.getTestcaseInputName();
    this.testcaseInputUrl = testcase.getTestcaseInputUrl();
    this.testcaseOutputName = testcase.getTestcaseOutputName();
    this.testcaseOutputUrl = testcase.getTestcaseOutputUrl();
    this.isPublic = testcase.getIsPublic();
  }
}

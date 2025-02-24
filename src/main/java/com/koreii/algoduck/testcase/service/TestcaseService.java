package com.koreii.algoduck.testcase.service;

import com.koreii.algoduck.testcase.dto.response.TestcaseResponseDto;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public interface TestcaseService {
  //  PK가 problemId인 문제에 테스트케이스 testcaseInput/output을 공개 상태 isPublic으로 추가함
  TestcaseResponseDto addTestcase(Long problemId, MultipartFile testcaseInput, MultipartFile testcaseOutput, boolean isPublic);

  List<TestcaseResponseDto> addTestcases(Long problemId, List<MultipartFile> testcaseInputs, List<MultipartFile> testcaseOutputs, List<Boolean> isPublics);

  TestcaseResponseDto findByTestcaseId(Long testcaseId);

  List<TestcaseResponseDto> selectTestcasesByProblemId(Long problemId);
}

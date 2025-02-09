package com.koreii.algoduck.testcase.repository;

import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.testcase.dto.request.TestcaseAddRequestDto;
import com.koreii.algoduck.testcase.dto.response.TestcaseResponseDto;
import org.springframework.stereotype.Repository;

@Repository
public interface TestcaseRepository {
  //  TestcaseService에서 테스트케이스가 저장소에 제대로 저장된 다음에 해당 경로를 이용해 DB에 저장
  TestcaseResponseDto addTestcase(Problem problem, TestcaseAddRequestDto testcaseAddRequestDto);
}

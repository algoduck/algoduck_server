package com.koreii.algoduck.testcase.repository;

import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.problemimage.dto.response.ProblemImageResponseDto;
import com.koreii.algoduck.problemimage.entity.ProblemImage;
import com.koreii.algoduck.testcase.dto.request.TestcaseAddRequestDto;
import com.koreii.algoduck.testcase.dto.response.TestcaseResponseDto;
import com.koreii.algoduck.testcase.entitiy.Testcase;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TestcaseRepositoryJpaImpl implements TestcaseRepository {
  private final EntityManager entityManager;

  @Override
  public TestcaseResponseDto addTestcase(Problem problem, TestcaseAddRequestDto testcaseAddRequestDto) {
    Testcase testcase = Testcase.builder()
        .problem(problem)
        .testcaseInputName(testcaseAddRequestDto.getTestcaseInputName())
        .testcaseInputUrl(testcaseAddRequestDto.getTestcaseInputUrl())
        .testcaseOutputName(testcaseAddRequestDto.getTestcaseOutputName())
        .testcaseOutputUrl(testcaseAddRequestDto.getTestcaseOutputUrl())
        .isPublic(testcaseAddRequestDto.isPublic())
        .build();

    entityManager.persist(testcase);
    return new TestcaseResponseDto(testcase);
  }
}

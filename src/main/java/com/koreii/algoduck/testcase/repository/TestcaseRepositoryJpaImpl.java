package com.koreii.algoduck.testcase.repository;

import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.problemimage.dto.response.ProblemImageResponseDto;
import com.koreii.algoduck.problemimage.entity.ProblemImage;
import com.koreii.algoduck.testcase.dto.request.TestcaseAddRequestDto;
import com.koreii.algoduck.testcase.dto.response.TestcaseResponseDto;
import com.koreii.algoduck.testcase.entitiy.Testcase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TestcaseRepositoryJpaImpl implements TestcaseRepository {
  private final EntityManager entityManager;

  @Override
  @Transactional
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

  @Override
  public Testcase findByTestcaseId(Long testcaseId) {
    return entityManager.find(Testcase.class, testcaseId);
  }

  @Override
  public List<TestcaseResponseDto> selectTestcasesByProblemId(Long problemId) {
    String jpql = "SELECT new com.koreii.algoduck.testcase.dto.response.TestcaseResponseDto(t) "
        + "FROM Testcase t "
        + "WHERE t.problem.problemId = :problemId";

    return entityManager.createQuery(jpql, TestcaseResponseDto.class)
        .setParameter("problemId", problemId)
        .getResultList();
  }
}

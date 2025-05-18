package com.koreii.algoduck.testcase.repository;

import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.testcase.dto.request.TestcaseRequestDto;
import com.koreii.algoduck.testcase.dto.response.TestcaseResponseDto;
import com.koreii.algoduck.testcase.entitiy.Testcase;
import jakarta.persistence.EntityManager;
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
  public TestcaseResponseDto addTestcase(Problem problem, TestcaseRequestDto testcaseRequestDto) {
    Testcase testcase = Testcase.builder()
        .problem(problem)
        .testcaseInputName(testcaseRequestDto.getTestcaseInputName())
        .testcaseInputUrl(testcaseRequestDto.getTestcaseInputUrl())
        .testcaseOutputName(testcaseRequestDto.getTestcaseOutputName())
        .testcaseOutputUrl(testcaseRequestDto.getTestcaseOutputUrl())
        .isPublic(testcaseRequestDto.isPublic())
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

  @Override
  public List<TestcaseResponseDto> selectPublicTestcasesByProblemId(Long problemId) {
    String jpql = "SELECT new com.koreii.algoduck.testcase.dto.response.TestcaseResponseDto(t) "
        + "FROM Testcase t "
        + "WHERE t.problem.problemId = :problemId and t.isPublic = true";

    return entityManager.createQuery(jpql, TestcaseResponseDto.class)
        .setParameter("problemId", problemId)
        .getResultList();
  }
}

package com.koreii.algoduck.problem.repository;

import com.koreii.algoduck.problem.dto.request.ProblemAddRequestDto;
import com.koreii.algoduck.problem.dto.response.ProblemResponseDto;
import com.koreii.algoduck.problem.dto.response.ProblemSimpleResponseDto;
import com.koreii.algoduck.problem.entity.Problem;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProblemRepositoryJpaImpl implements ProblemRepository {
  private final EntityManager entityManager;

  @Override
  @Transactional
  public Problem addProblem(ProblemAddRequestDto problemAddRequestDto) {
    Problem problem = Problem.builder()
        .problemNumber(problemAddRequestDto.getProblemNumber())
        .title(problemAddRequestDto.getTitle())
        .description(problemAddRequestDto.getDescription())
        .inputDescription(problemAddRequestDto.getInputDescription())
        .outputDescription(problemAddRequestDto.getOutputDescription())
        .difficulty(problemAddRequestDto.getDifficulty())
        .timeLimitation(problemAddRequestDto.getTimeLimitation())
        .memoryLimitation(problemAddRequestDto.getMemoryLimitation())
        .memoryLimitation(0)
        .build();

    entityManager.persist(problem);
    return problem;
  }

  @Override
  public Problem findByProblemId(Long problemId) {
    log.info("problemId = {}", problemId);
    return entityManager.find(Problem.class, problemId);
  }

  @Override
  public long countAllProblems() {
    String jpql = "SELECT COUNT(p) FROM Problem p";
    return entityManager.createQuery(jpql, Long.class).getSingleResult();
  }

  @Override
  public List<ProblemSimpleResponseDto> selectAllProblems(int pageNumber, int pageSize) {
    int offset = (pageNumber - 1) * pageSize;

    String jpql = "SELECT new com.koreii.algoduck.problem.dto.response.ProblemSimpleResponseDto(p) "
        + "FROM Problem p "
        + "ORDER BY p.problemNumber";

    return entityManager.createQuery(jpql, ProblemSimpleResponseDto.class)
        .setFirstResult(offset)
        .setMaxResults(pageSize)
        .getResultList();
  }

  @Override
  public long countProblemsWithProblemNumber(String problemNumber) {
    String jpql = "SELECT COUNT(p) "
        + "FROM Problem p "
        + "WHERE p.problemNumber LIKE :problemNumber ";

    return entityManager.createQuery(jpql, Long.class).setParameter("problemNumber", "%" + problemNumber + "%")
        .getSingleResult();
  }

  @Override
  public List<ProblemSimpleResponseDto> selectProblemsWithProblemNumber(String problemNumber, int pageNumber, int pageSize) {
    int offset = (pageNumber - 1) * pageSize;

    String jpql = "SELECT new com.koreii.algoduck.problem.dto.response.ProblemSimpleResponseDto(p) "
        + "FROM Problem p "
        + "WHERE p.problemNumber LIKE :problemNumber "
        + "ORDER BY p.problemNumber";

    return entityManager.createQuery(jpql, ProblemSimpleResponseDto.class)
        .setParameter("problemNumber", "%" + problemNumber + "%")
        .setFirstResult(offset)
        .setMaxResults(pageSize)
        .getResultList();
  }

  @Override
  public long countProblemsWithTitle(String title) {
    String jpql = "SELECT COUNT(p) "
        + "FROM Problem p "
        + "WHERE p.title LIKE :title ";

    return entityManager.createQuery(jpql, Long.class).setParameter("title", "%" + title + "%")
        .getSingleResult();
  }

  @Override
  public List<ProblemSimpleResponseDto> selectProblemsWithTitle(String title, int pageNumber, int pageSize) {
    int offset = (pageNumber - 1) * pageSize;

    String jpql = "SELECT new com.koreii.algoduck.problem.dto.response.ProblemSimpleResponseDto(p) "
        + "FROM Problem p "
        + "WHERE p.title LIKE :title "
        + "ORDER BY p.problemNumber";

    return entityManager.createQuery(jpql, ProblemSimpleResponseDto.class)
        .setParameter("title", "%" + title + "%")
        .setFirstResult(offset)
        .setMaxResults(pageSize)
        .getResultList();
  }

  @Override
  public long countProblemsWithDifficulty(List<Integer> difficulties) {
    if (difficulties.isEmpty()) {
      return countAllProblems();
    }

    String jpql = "SELECT COUNT(p) "
        + "FROM Problem p "
        + "WHERE p.difficulty IN :difficulties";

    return entityManager.createQuery(jpql, Long.class)
        .setParameter("difficulties", difficulties)
        .getSingleResult();
  }

  @Override
  public List<ProblemSimpleResponseDto> selectProblemsWithDifficulty(List<Integer> difficulties, int pageNumber, int pageSize) {
    if (difficulties.isEmpty()) {
      return selectAllProblems(pageNumber, pageSize);
    }

    int offset = (pageNumber - 1) * pageSize;

    String jpql = "SELECT new com.koreii.algoduck.problem.dto.response.ProblemSimpleResponseDto(p) "
        + "FROM Problem p "
        + "WHERE p.difficulty IN :difficulties "
        + "ORDER BY p.problemNumber";

    return entityManager.createQuery(jpql, ProblemSimpleResponseDto.class)
        .setParameter("difficulties", difficulties)
        .setFirstResult(offset)
        .setMaxResults(pageSize)
        .getResultList();
  }
}

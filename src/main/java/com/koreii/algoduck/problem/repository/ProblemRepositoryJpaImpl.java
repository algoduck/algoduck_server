package com.koreii.algoduck.problem.repository;

import com.koreii.algoduck.problem.dto.request.ProblemAddRequestDto;
import com.koreii.algoduck.problem.dto.response.ProblemResponseDto;
import com.koreii.algoduck.problem.entity.Problem;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProblemRepositoryJpaImpl implements ProblemRepository {
  private final EntityManager entityManager;

  @Override
  public ProblemResponseDto addProblem(ProblemAddRequestDto problemAddRequestDto) {
    Problem problem = Problem.builder()
        .problemNumber(problemAddRequestDto.getProblemNumber())
        .title(problemAddRequestDto.getTitle())
        .description(problemAddRequestDto.getDescription())
        .inputDescription(problemAddRequestDto.getInputDescription())
        .outputDescription(problemAddRequestDto.getOutputDescription())
        .difficulty(problemAddRequestDto.getDifficulty())
        .build();

    entityManager.persist(problem);
    return new ProblemResponseDto(problem);
  }

  @Override
  public Problem findByProblemId(Long problemId) {
    return entityManager.find(Problem.class, problemId);
  }
}

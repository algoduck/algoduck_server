package com.koreii.algoduck.problemalgorithm.repository;

import com.koreii.algoduck.algorithm.entity.Algorithm;
import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.problemalgorithm.dto.response.ProblemAlgorithmResponseDto;
import com.koreii.algoduck.problemalgorithm.entity.ProblemAlgorithm;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProblemAlgorithmRepositoryImpl implements ProblemAlgorithmRepository {
  private final EntityManager entityManager;

  @Override
  public ProblemAlgorithmResponseDto addProblemAlgorithm(Problem problem, Algorithm algorithm) {
    ProblemAlgorithm problemAlgorithm = ProblemAlgorithm.builder()
        .problem(problem)
        .algorithm(algorithm)
        .build();

    entityManager.persist(problemAlgorithm);
    return new ProblemAlgorithmResponseDto(problemAlgorithm);
  }
}

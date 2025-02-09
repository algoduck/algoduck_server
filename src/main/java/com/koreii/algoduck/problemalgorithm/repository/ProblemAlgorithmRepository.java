package com.koreii.algoduck.problemalgorithm.repository;

import com.koreii.algoduck.algorithm.entity.Algorithm;
import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.problemalgorithm.dto.response.ProblemAlgorithmResponseDto;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemAlgorithmRepository {
  //  algorithm이 쓰인 문제 problem을 추가함
  ProblemAlgorithmResponseDto addProblemAlgorithm(Problem problem, Algorithm algorithm);
}

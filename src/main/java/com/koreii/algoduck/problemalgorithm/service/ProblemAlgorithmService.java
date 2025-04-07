package com.koreii.algoduck.problemalgorithm.service;

import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.problemalgorithm.dto.response.ProblemAlgorithmResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProblemAlgorithmService {
  ProblemAlgorithmResponseDto addProblemAlgorithm(Problem problem, Long algorithmId);

  List<ProblemAlgorithmResponseDto> addProblemAlgorithms(Problem problem, List<Long> algorithmIds);
}

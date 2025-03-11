package com.koreii.algoduck.problemalgorithm.service;

import com.koreii.algoduck.algorithm.entity.Algorithm;
import com.koreii.algoduck.algorithm.repository.AlgorithmRepository;
import com.koreii.algoduck.algorithm.service.AlgorithmService;
import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.problem.service.ProblemService;
import com.koreii.algoduck.problemalgorithm.dto.response.ProblemAlgorithmResponseDto;
import com.koreii.algoduck.problemalgorithm.repository.ProblemAlgorithmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProblemAlgorithmServiceImpl implements ProblemAlgorithmService {
  private final ProblemAlgorithmRepository problemAlgorithmRepository;
  private final AlgorithmService algorithmService;

  @Override
  public List<ProblemAlgorithmResponseDto> addProblemAlgorithms(Problem problem, List<Long> algorithmIds) {
    List<ProblemAlgorithmResponseDto> problemAlgorithmResponseDtos = new ArrayList<>();

    for (Long algorithmId : algorithmIds) {
      Algorithm algorithm = algorithmService.findEntityByAlgorithmId(algorithmId);
      problemAlgorithmResponseDtos.add(problemAlgorithmRepository.addProblemAlgorithm(problem, algorithm));
    }

    return problemAlgorithmResponseDtos;
  }
}

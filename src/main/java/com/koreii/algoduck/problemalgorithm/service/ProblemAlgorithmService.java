package com.koreii.algoduck.problemalgorithm.service;

import com.koreii.algoduck.problemalgorithm.dto.response.ProblemAlgorithmResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProblemAlgorithmService {
  List<ProblemAlgorithmResponseDto> addProblemAlgorithms(Long problemId, List<Long> algorithmIds);
}

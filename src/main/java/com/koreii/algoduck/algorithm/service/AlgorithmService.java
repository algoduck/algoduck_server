package com.koreii.algoduck.algorithm.service;

import com.koreii.algoduck.algorithm.dto.request.AlgorithmAddRequestDto;
import com.koreii.algoduck.algorithm.dto.response.AlgorithmResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlgorithmService {
  AlgorithmResponseDto addAlgorithm(AlgorithmAddRequestDto algorithmAddRequestDto);

  AlgorithmResponseDto findAlgorithmById(Long algorithmId);

  List<AlgorithmResponseDto> findAlgorithmsWithName(String algorithmName);
}

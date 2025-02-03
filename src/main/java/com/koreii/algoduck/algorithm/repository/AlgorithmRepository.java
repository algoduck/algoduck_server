package com.koreii.algoduck.algorithm.repository;

import com.koreii.algoduck.algorithm.dto.request.AlgorithmAddRequestDto;
import com.koreii.algoduck.algorithm.dto.response.AlgorithmResponseDto;
import com.koreii.algoduck.algorithm.entity.Algorithm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlgorithmRepository {
  Algorithm addAlgorithm(String algorithmName);

  Algorithm findAlgorithm(Long algorithmId);

  List<AlgorithmResponseDto> findAlgorithmsWithName(String algorithmName);
}

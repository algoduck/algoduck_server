package com.koreii.algoduck.algorithm.service;

import com.koreii.algoduck.algorithm.dto.request.AlgorithmAddRequestDto;
import com.koreii.algoduck.algorithm.dto.response.AlgorithmResponseDto;
import com.koreii.algoduck.algorithm.entity.Algorithm;
import com.koreii.algoduck.algorithm.repository.AlgorithmRepository;
import com.koreii.algoduck.alias.repository.AliasRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlgorithmServiceImpl implements AlgorithmService {
  private final AlgorithmRepository algorithmRepository;
  private final AliasRepository aliasRepository;

  @Override
  public AlgorithmResponseDto addAlgorithm(AlgorithmAddRequestDto algorithmAddRequestDto) {
    Algorithm algorithm = algorithmRepository.addAlgorithm(algorithmAddRequestDto.getAlgorithmName());
    aliasRepository.bulkInsert(algorithm, algorithmAddRequestDto.getAliases());

    return new AlgorithmResponseDto(algorithm);
  }

  @Override
  public AlgorithmResponseDto findAlgorithmById(Long algorithmId) {
    Algorithm algorithm = algorithmRepository.findAlgorithm(algorithmId);
    return new AlgorithmResponseDto(algorithm);
  }

  @Override
  public List<AlgorithmResponseDto> findAlgorithmsWithName(String algorithmName) {
    return algorithmRepository.findAlgorithmsWithName(algorithmName);
  }
}

package com.koreii.algoduck.alias.service;

import com.koreii.algoduck.algorithm.entity.Algorithm;
import com.koreii.algoduck.algorithm.repository.AlgorithmRepository;
import com.koreii.algoduck.alias.dto.request.AliasAddRequestDto;
import com.koreii.algoduck.alias.dto.response.AliasResponseDto;
import com.koreii.algoduck.alias.repository.AliasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AliasServiceImpl implements AliasService {
  private final AliasRepository aliasRepository;
  private final AlgorithmRepository algorithmRepository;

  @Override
  public AliasResponseDto addAlias(AliasAddRequestDto aliasAddRequestDto) {
    Algorithm algorithm = algorithmRepository.findAlgorithm(aliasAddRequestDto.getAlgorithmId());
    return aliasRepository.addAlias(algorithm, aliasAddRequestDto.getAliasName());
  }

  @Override
  public List<AliasResponseDto> findAliasesWithAliasName(String aliasName) {
    return aliasRepository.findAliasesWithAliasName(aliasName);
  }
}

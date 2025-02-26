package com.koreii.algoduck.algorithm.service;

import com.koreii.algoduck.algorithm.dto.request.AlgorithmAddRequestDto;
import com.koreii.algoduck.algorithm.dto.response.AlgorithmResponseDto;
import com.koreii.algoduck.algorithm.entity.Algorithm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlgorithmService {
  //  기존에 존재하지 않던 알고리즘을 신규로 추가함
  //  알고리즘에 해당하는 별칭도 함께 저장할 수 있음
  AlgorithmResponseDto addAlgorithm(AlgorithmAddRequestDto algorithmAddRequestDto);

  //  PK로 알고리즘 엔티티를 찾음
  Algorithm findEntityByAlgorithmId(Long algorithmId);

  //  PK로 알고리즘 DTO를 찾음
  AlgorithmResponseDto findDtoByAlgorithmId(Long algorithmId);

  //  algorithmName이라는 이름을 포함하는 알고리즘 리스트를 찾음
  List<AlgorithmResponseDto> findAlgorithmsWithName(String algorithmName);
}

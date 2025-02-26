package com.koreii.algoduck.algorithm.repository;

import com.koreii.algoduck.algorithm.dto.response.AlgorithmResponseDto;
import com.koreii.algoduck.algorithm.entity.Algorithm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlgorithmRepository {
  //  기존에 존재하지 않던 알고리즘을 추가함
  Algorithm addAlgorithm(String algorithmName);

  //  PK로 알고리즘을 찾음
  Algorithm findByAlgorithmId(Long algorithmId);

  //  algorithmName이라는 이름을 포함하는 알고리즘 리스트를 찾음
  List<AlgorithmResponseDto> findAlgorithmsWithName(String algorithmName);
}

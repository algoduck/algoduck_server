package com.koreii.algoduck.algorithm.repository;

import com.koreii.algoduck.algorithm.entity.Algorithm;
import org.springframework.stereotype.Repository;

@Repository
public interface AlgorithmRepository {
  Algorithm findAlgorithm(Long algorithmId);
}

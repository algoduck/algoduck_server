package com.koreii.algoduck.algorithm.repository;

import com.koreii.algoduck.algorithm.entity.Algorithm;
import com.koreii.algoduck.member.dto.response.MemberResponseDto;
import com.koreii.algoduck.member.entity.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AlgorithmRepositoryJpaImpl implements AlgorithmRepository {
  private final EntityManager entityManager;

  @Override
  public Algorithm findAlgorithm(Long algorithmId) {
    return entityManager.find(Algorithm.class, algorithmId);
  }
}

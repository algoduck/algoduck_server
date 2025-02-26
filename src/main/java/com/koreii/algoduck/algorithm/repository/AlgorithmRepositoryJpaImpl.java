package com.koreii.algoduck.algorithm.repository;

import com.koreii.algoduck.algorithm.dto.request.AlgorithmAddRequestDto;
import com.koreii.algoduck.algorithm.dto.response.AlgorithmResponseDto;
import com.koreii.algoduck.algorithm.entity.Algorithm;
import com.koreii.algoduck.alias.dto.response.AliasResponseDto;
import com.koreii.algoduck.member.dto.response.MemberResponseDto;
import com.koreii.algoduck.member.entity.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AlgorithmRepositoryJpaImpl implements AlgorithmRepository {
  private final EntityManager entityManager;

  @Override
  public Algorithm addAlgorithm(String algorithmName) {
    Algorithm algorithm = Algorithm.builder()
        .algorithmName(algorithmName)
        .problemCount(0)
        .build();

    return algorithm;
  }

  @Override
  public Algorithm findByAlgorithmId(Long algorithmId) {
    return entityManager.find(Algorithm.class, algorithmId);
  }

  @Override
  public List<AlgorithmResponseDto> findAlgorithmsWithName(String algorithmName) {
    String jpql = "SELECT new com.koreii.algoduck.algorithm.dto.response.AlgorithmResponseDto(a) "
        + "FROM Algorithm a "
        + "WHERE a.algorithmName LIKE :algorithmName "
        + "ORDER BY a.algorithmName";

    return entityManager.createQuery(jpql, AlgorithmResponseDto.class)
        .setParameter("algorithmName", "%" + algorithmName + "%")
        .getResultList();
  }

  @Override
  public long countAll() {
    String jpql = "SELECT COUNT(a) FROM Algorithm a";
    return entityManager.createQuery(jpql, Long.class).getSingleResult();
  }

  @Override
  public List<AlgorithmResponseDto> findAll(int pageNumber, int pageSize) {
    int offset = (pageNumber - 1) * pageSize;

    String jpql = "SELECT new com.koreii.algoduck.algorithm.dto.response.AlgorithmResponseDto(a) "
        + "FROM Algorithm a "
        + "ORDER BY a.problemCount DESC";

    return entityManager.createQuery(jpql, AlgorithmResponseDto.class)
        .setFirstResult(offset)
        .setMaxResults(pageSize)
        .getResultList();
  }

}

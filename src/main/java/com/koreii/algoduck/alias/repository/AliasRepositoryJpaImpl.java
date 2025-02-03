package com.koreii.algoduck.alias.repository;

import com.koreii.algoduck.algorithm.entity.Algorithm;
import com.koreii.algoduck.alias.dto.response.AliasResponseDto;
import com.koreii.algoduck.alias.entity.Alias;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AliasRepositoryJpaImpl implements AliasRepository {
  private final EntityManager entityManager;

  @Override
  public AliasResponseDto addAlias(Algorithm algorithm, String aliasName) {
    Alias alias = Alias.builder()
        .aliasName(aliasName)
        .algorithm(algorithm)
        .build();

    entityManager.persist(alias);
    return new AliasResponseDto(alias);
  }

  @Override
  public List<AliasResponseDto> findAliasesWithAliasName(String aliasName) {
    String jpql = "SELECT new com.koreii.algoduck.alias.dto.response.AliasResponseDto(a) "
        + "FROM Alias a "
        + "WHERE a.aliasName LIKE :aliasName "
        + "ORDER BY a.aliasName";

    return entityManager.createQuery(jpql, AliasResponseDto.class)
        .setParameter("aliasName", "%" + aliasName + "%")
        .getResultList();
  }
}

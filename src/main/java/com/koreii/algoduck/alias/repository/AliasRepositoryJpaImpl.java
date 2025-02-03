package com.koreii.algoduck.alias.repository;

import com.koreii.algoduck.algorithm.entity.Algorithm;
import com.koreii.algoduck.alias.dto.response.AliasResponseDto;
import com.koreii.algoduck.alias.entity.Alias;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.koreii.algoduck.util.constants.Constants.BATCH_SIZE;

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
  public void bulkInsert(Algorithm algorithm, List<String> aliasNames) {
    int count = 0;

    for (String aliasName : aliasNames) {
      Alias alias = Alias.builder()
          .aliasName(aliasName)
          .algorithm(algorithm)
          .build();

      entityManager.persist(alias);
      count++;

      if (count % BATCH_SIZE == 0) { // 50개씩 배치 처리
        entityManager.flush(); // 현재까지 INSERT된 객체를 DB에 반영
        entityManager.clear(); // 영속성 컨텍스트 초기화 (메모리 절약)
      }
    }

    if (count % BATCH_SIZE != 0) { // 남은 데이터 처리 (예: 49개만 저장된 경우)
      entityManager.flush();
      entityManager.clear();
    }
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

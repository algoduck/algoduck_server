package com.koreii.algoduck.alias.repository;

import com.koreii.algoduck.algorithm.dto.response.AlgorithmResponseDto;
import com.koreii.algoduck.algorithm.entity.Algorithm;
import com.koreii.algoduck.alias.dto.request.AliasAddRequestDto;
import com.koreii.algoduck.alias.dto.response.AliasResponseDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AliasRepository {
  //  algorithm에 해당하는 별칭을 1개 추가함
  AliasResponseDto addAlias(Algorithm algorithm, String aliasName); //  알고리즘 분류 별칭 추가

  //  algorithm에 해당하는 별칭 여러 개를 한꺼번에 추가함
  void bulkInsert(Algorithm algorithm, List<String> aliasNames);

  //  aliasName이 포함된 별칭 리스트를 찾음
  List<AliasResponseDto> findAliasesWithAliasName(String aliasName);             //  aliasName이 포함된 별칭 리스트 반환
}

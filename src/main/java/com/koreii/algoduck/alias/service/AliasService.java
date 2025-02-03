package com.koreii.algoduck.alias.service;

import com.koreii.algoduck.alias.dto.request.AliasAddRequestDto;
import com.koreii.algoduck.alias.dto.response.AliasResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AliasService {
  AliasResponseDto addAlias(AliasAddRequestDto aliasAddRequestDto);

  List<AliasResponseDto> findAliasesWithAliasName(String aliasName);
}

package com.koreii.algoduck.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class LoginRequestDto {
  private String loginId;
  private String password;
}

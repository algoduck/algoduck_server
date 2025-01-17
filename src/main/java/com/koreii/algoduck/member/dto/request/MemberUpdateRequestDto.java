package com.koreii.algoduck.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class MemberUpdateRequestDto {
  private Long memberId;
  private String password;
  private String statusMessage;
}

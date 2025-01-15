package com.koreii.algoduck.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MemberSimpeResponseDto {
  private Long memberId;
  private String loginId;
  private String nickname;
  private Integer solved;
}

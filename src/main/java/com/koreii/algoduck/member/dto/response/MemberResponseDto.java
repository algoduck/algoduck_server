package com.koreii.algoduck.member.dto.response;

import com.koreii.algoduck.member.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MemberResponseDto {
  private Long memberId;
  private String loginId;
  private String nickname;
  private Integer solved;
  private Role role;
  private String profileImageUrl;
  private String statusMessage;
}

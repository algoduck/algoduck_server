package com.koreii.algoduck.member.dto.request;

import com.koreii.algoduck.member.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@Getter
public class MemberSaveRequestDto {
  private String loginId;
  @Setter
  private String password;
  private String email;
  private String nickname;
  private Role role;
  private String statusMessage;
}

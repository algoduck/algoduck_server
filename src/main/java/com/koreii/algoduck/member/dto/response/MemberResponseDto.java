package com.koreii.algoduck.member.dto.response;

import com.koreii.algoduck.member.entity.Member;
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
  private String email;
  private String nickname;
  private Integer solved;
  private Role role;
  private String profileImageUrl;
  private String statusMessage;

  public MemberResponseDto(Member member) {
    this.memberId = member.getMemberId();
    this.loginId = member.getLoginId();
    this.email = member.getEmail();
    this.nickname = member.getNickname();
    this.solved = member.getSolved();
    this.role = member.getRole();
    this.profileImageUrl = member.getProfileImageUrl();
    this.statusMessage = member.getStatusMessage();
  }
}

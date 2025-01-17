package com.koreii.algoduck.member.dto.response;

import com.koreii.algoduck.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MemberSimpleResponseDto {
  private Long memberId;
  private String loginId;
  private String nickname;
  private Integer solved;

  public MemberSimpleResponseDto(Member member) {
    this.memberId = member.getMemberId();
    this.loginId = member.getLoginId();
    this.nickname = member.getNickname();
    this.solved = member.getSolved();
  }
}

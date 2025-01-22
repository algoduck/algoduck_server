package com.koreii.algoduck.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
public class MemberUpdateRequestDto {
  private Long memberId;
  private String loginId;
  private String password;
  private String statusMessage;
  private String beforeProfileImageUrl;
}

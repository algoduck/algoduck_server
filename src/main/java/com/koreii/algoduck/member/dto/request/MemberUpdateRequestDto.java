package com.koreii.algoduck.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@Getter
@ToString
public class MemberUpdateRequestDto {
  private String loginId;
  private String password;
  private String statusMessage;
  private String beforeProfileImageUrl;
}

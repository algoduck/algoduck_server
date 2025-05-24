package com.koreii.algoduck.member.security;

import com.koreii.algoduck.member.entity.Member;
import com.koreii.algoduck.member.enums.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
  private final Member member;

  public Long getMemberId() {
    return member.getMemberId();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + member.getRole().name()));
  }

  @Override
  public String getPassword() {
    return member.getPassword();
  }

  @Override
  public String getUsername() {
    return member.getLoginId();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true; // 계정 만료 안 함
  }

  @Override
  public boolean isAccountNonLocked() {
    return true; // 잠금 없음
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true; // 비밀번호 유효
  }

  @Override
  public boolean isEnabled() {
    return member.getMemberStatus() == MemberStatus.ACTIVE; // 탈퇴 요청 여부 등 고려
  }
}

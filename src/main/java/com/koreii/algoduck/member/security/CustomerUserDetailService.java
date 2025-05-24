package com.koreii.algoduck.member.security;

import com.koreii.algoduck.member.entity.Member;
import com.koreii.algoduck.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailService implements UserDetailsService {
  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
    Member member = memberRepository.findByLoginId(loginId).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

    return new CustomUserDetails(member);
  }
}

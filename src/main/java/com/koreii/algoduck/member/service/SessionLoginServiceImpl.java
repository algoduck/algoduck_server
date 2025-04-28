package com.koreii.algoduck.member.service;

import com.koreii.algoduck.exceptions.member.LoginFailureException;
import com.koreii.algoduck.member.dto.response.MemberResponseDto;
import com.koreii.algoduck.member.entity.Member;
import com.koreii.algoduck.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionLoginServiceImpl implements LoginService {
  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public MemberResponseDto login(String loginId, String password, HttpServletRequest request) {
    Member member = memberRepository.findByLoginId(loginId)
        .orElseThrow(() -> new LoginFailureException("아이디 또는 비밀번호가 올바르지 않습니다."));

    if (!passwordEncoder.matches(password, member.getPassword())) {
      throw new LoginFailureException("아이디 또는 비밀번호가 올바르지 않습니다.");
    }

    //  기존 세션이 있으면 사용, 없으면 새로 생성
    HttpSession session = request.getSession(true);

    //  세션에 로그인 회원 정보 저장
    session.setAttribute("loginMember", member);

    return new MemberResponseDto(member);
  }

  public void logout(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }
  }
}

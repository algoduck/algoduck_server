package com.koreii.algoduck.member.service;

import com.koreii.algoduck.exceptions.member.LoginFailureException;
import com.koreii.algoduck.member.dto.response.MemberResponseDto;
import com.koreii.algoduck.member.entity.Member;
import com.koreii.algoduck.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import static com.koreii.algoduck.util.constants.Constants.LOGIN_MEMBER;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionLoginServiceImpl implements LoginService {
  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public Member login(String loginId, String password, HttpServletRequest request) {
    Member member = memberRepository.findByLoginId(loginId)
        .orElseThrow(() -> new LoginFailureException("아이디 또는 비밀번호가 올바르지 않습니다."));

    if (!passwordEncoder.matches(password, member.getPassword())) {
      throw new LoginFailureException("아이디 또는 비밀번호가 올바르지 않습니다.");
    }

    //  기존 세션이 있으면 사용, 없으면 새로 생성
    HttpSession session = request.getSession(true);
    log.info("세션 유효시간 (초): " + session.getMaxInactiveInterval());
    //  세션에 로그인 회원 정보 저장
    session.setAttribute(LOGIN_MEMBER, member);

    return member;
  }

  public void logout(HttpServletRequest request) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    log.info("authentication = {}", authentication);

    if (authentication != null) {
      new SecurityContextLogoutHandler().logout(request, null, authentication);
    } else {
      HttpSession session = request.getSession(false);
      if (session != null) {
        session.invalidate();
      }
      SecurityContextHolder.clearContext();
    }
  }
}

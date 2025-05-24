package com.koreii.algoduck.member.service;

import com.koreii.algoduck.member.dto.response.MemberResponseDto;
import com.koreii.algoduck.member.entity.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.koreii.algoduck.util.constants.Constants.LOGIN_MEMBER;

public interface LoginService {
  Member login(String loginId, String password, HttpServletRequest request);

  void logout(HttpServletRequest request);

  default MemberResponseDto getMyInfo(HttpSession session) {
    if (session == null || session.getAttribute(LOGIN_MEMBER) == null) {
      return null;
    }

    Member member = (Member) session.getAttribute(LOGIN_MEMBER);
    return new MemberResponseDto(member);
  }
}

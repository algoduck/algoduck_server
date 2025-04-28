package com.koreii.algoduck.member.service;

import com.koreii.algoduck.member.dto.response.MemberResponseDto;
import jakarta.servlet.http.HttpServletRequest;

public interface LoginService {
  MemberResponseDto login(String loginId, String password, HttpServletRequest request);

  void logout(HttpServletRequest request);
}

package com.koreii.algoduck.interceptor;

import com.koreii.algoduck.util.constants.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.koreii.algoduck.util.constants.Constants.LOGIN_MEMBER;

@Component
@Slf4j
public class SessionLoginInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    HttpSession session = request.getSession(false);

    if (session == null || session.getAttribute(LOGIN_MEMBER) == null) {
      log.info("로그인되지 않은 사용자 요청 차단. 요청 URI: {}", request.getRequestURI());
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return false;
    }

    return true;
  }
}

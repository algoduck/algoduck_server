package com.koreii.algoduck.config;

import com.koreii.algoduck.interceptor.SessionLoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
  private final HandlerInterceptor loginInterceptor;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/v1/**") // API 경로
        .allowedOrigins("http://localhost:3000") // 허용할 클라이언트 주소
        .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드
        .allowedHeaders("*") // 허용할 헤더
        .allowCredentials(true); // 인증 정보 포함 여부
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loginInterceptor)
        .addPathPatterns("/**") // 일단 모든 요청에 대해 로그인 검사
        .excludePathPatterns("/**"); // 비로그인 허용하는 일부 URL 제외
  }
}

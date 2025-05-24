package com.koreii.algoduck.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
  private final HandlerInterceptor loginInterceptor;
  @Value("${app.default.client-domain-name}")
  private String clientDomainName;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**") // API 경로
        .allowedOriginPatterns("http://localhost:3000", clientDomainName) // 허용할 클라이언트 주소
        .allowedMethods("GET", "POST", "PUT", "DELETE, OPTIONS") // 허용할 HTTP 메서드
        .allowedHeaders("*") // 허용할 헤더
        .allowCredentials(true); // 인증 정보 포함 여부, 세션 쿠키 허용
  }
}

package com.koreii.algoduck.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/v1/**") // API 경로
        .allowedOrigins("http://localhost:3000") // 허용할 클라이언트 주소
        .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드
        .allowedHeaders("*") // 허용할 헤더
        .allowCredentials(true); // 인증 정보 포함 여부
  }
}

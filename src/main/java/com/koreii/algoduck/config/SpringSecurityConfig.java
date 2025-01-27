package com.koreii.algoduck.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/api/v1/members/join",
                "/api/v1/members/check-*",
                "/swagger-ui/**",
                "/v3/api-docs/**"
            ).permitAll() //  인증 없이 허용
            .anyRequest().authenticated() //  나머지는 인증 필요
        ).httpBasic(Customizer.withDefaults()); //  기본 HTTP Basic 설정

    return http.build();
  }
}

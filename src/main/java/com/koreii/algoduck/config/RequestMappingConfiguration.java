package com.koreii.algoduck.config;

import com.koreii.algoduck.base.handler.CustomRequestMappingHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class RequestMappingConfiguration extends DelegatingWebMvcConfiguration {
  @Override
  protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
    return new CustomRequestMappingHandler();
  }
}

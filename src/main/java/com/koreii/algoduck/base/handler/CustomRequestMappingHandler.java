package com.koreii.algoduck.base.handler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CustomRequestMappingHandler extends RequestMappingHandlerMapping {
  @Override
  protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
    RequestMappingInfo requestMappingInfo = super.getMappingForMethod(method, handlerType);

    if (requestMappingInfo == null) {
      return null;
    }

    List<String> superclassUrlPatterns = new ArrayList<>();
    Class<?> superclass = handlerType.getSuperclass();

    for (; superclass != Object.class; superclass = superclass.getSuperclass()) {
      if (superclass.isAnnotationPresent(RequestMapping.class)) {
        superclassUrlPatterns.add(0, superclass.getAnnotation(RequestMapping.class).value()[0]);
      }
    }

    if (!superclassUrlPatterns.isEmpty()) {
      RequestMappingInfo superclassRequestMappingInfo = RequestMappingInfo.paths(String.join("", superclassUrlPatterns)).build();
      return superclassRequestMappingInfo.combine(requestMappingInfo);
    } else {
      return requestMappingInfo;
    }
  }
}
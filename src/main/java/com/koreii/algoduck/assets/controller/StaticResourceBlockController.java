package com.koreii.algoduck.assets.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticResourceBlockController {

  @GetMapping({
      "/favicon.ico",
      "/robots.txt",
      "/apple-touch-icon.png",
      "/apple-touch-icon-precomposed.png",
      "/site.webmanifest",
      "/browserconfig.xml",
      "/manifest.json",
      "/logo192.png",
      "/logo512.png"
  })
  public void blockStaticRequests(HttpServletResponse response) {
    response.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204 No Content
  }
}

package com.koreii.algoduck.data;

import jakarta.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
@RequiredArgsConstructor
public class DataInitializer {
  private final Environment env;
  private final DataSource dataSource;

  @PostConstruct
  public void init() throws Exception {
    String ddlAuto = env.getProperty("spring.jpa.hibernate.ddl-auto");

    if ("create".equalsIgnoreCase(ddlAuto)) {
      try (Connection conn = dataSource.getConnection()) {
        // classpath:init-data.sql 실행
        ScriptUtils.executeSqlScript(conn, new org.springframework.core.io.ClassPathResource("init-db.sql"));
        System.out.println("init-db.sql executed!");
      }
    }
  }
}

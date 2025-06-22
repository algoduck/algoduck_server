package com.koreii.algoduck;

import com.koreii.algoduck.config.TestMockConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static junit.framework.TestCase.assertTrue;

@ActiveProfiles("test")
@Testcontainers
@SpringBootTest(
    properties = {
        "spring.cloud.aws.credentials.access-key=dummy-access-key",
        "spring.cloud.aws.credentials.secret-key=dummy-secret-key",
        "app.default.profile-image-url=https://dummy.url",
        "spring.cloud.aws.s3.testcase_bucket=dummy-bucket"
    }
)
@Import(TestMockConfig.class)
class AlgoduckApplicationTests {

  @Container
  static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
      .withDatabaseName("testdb")
      .withUsername("testuser")
      .withPassword("testpass");

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", mysql::getJdbcUrl);
    registry.add("spring.datasource.username", mysql::getUsername);
    registry.add("spring.datasource.password", mysql::getPassword);
  }

  @Test
  void contextLoads() {
  }
}


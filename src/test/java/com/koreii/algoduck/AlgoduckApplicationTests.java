package com.koreii.algoduck;

import com.koreii.algoduck.config.TestMockConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

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

  @Container
  static RabbitMQContainer rabbitMQ = new RabbitMQContainer("rabbitmq:3.11-management")
      .withExposedPorts(5672, 15672)
      .withUser("testuser", "testpass")
      .withVhost("/")
      .withPermission("/", "testuser", ".*", ".*", ".*");

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", mysql::getJdbcUrl);
    registry.add("spring.datasource.username", mysql::getUsername);
    registry.add("spring.datasource.password", mysql::getPassword);

    registry.add("spring.rabbitmq.host", rabbitMQ::getHost);
    registry.add("spring.rabbitmq.port", () -> rabbitMQ.getMappedPort(5672));
    registry.add("spring.rabbitmq.username", () -> "testuser");
    registry.add("spring.rabbitmq.password", () -> "testpass");
    registry.add("spring.rabbitmq.ssl.enabled", () -> false); // ← 이 줄 추가
  }


  @Test
  void contextLoads() {
    // 단순 컨텍스트 로딩 테스트
  }
}

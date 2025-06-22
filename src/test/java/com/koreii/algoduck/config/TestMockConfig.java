package com.koreii.algoduck.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class TestMockConfig {

  @Bean
  public RabbitTemplate rabbitTemplate() {
    return mock(RabbitTemplate.class);
  }

  @Bean
  public AmqpAdmin amqpAdmin() {
    return mock(AmqpAdmin.class);
  }
}
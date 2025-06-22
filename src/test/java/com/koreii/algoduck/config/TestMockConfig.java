package com.koreii.algoduck.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class TestMockConfig {

  @Bean(name = "mockRabbitTemplate")
  public RabbitTemplate rabbitTemplate() {
    return mock(RabbitTemplate.class);
  }

  @Bean(name = "mockAmqpAdmin")
  public AmqpAdmin amqpAdmin() {
    return mock(AmqpAdmin.class);
  }
}
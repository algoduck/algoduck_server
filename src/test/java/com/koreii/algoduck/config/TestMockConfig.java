package com.koreii.algoduck.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;

@TestConfiguration
@Profile("test")
public class TestMockConfig {

  @Bean
  @ConditionalOnMissingBean(RabbitTemplate.class)
  public RabbitTemplate rabbitTemplate() {
    return mock(RabbitTemplate.class);
  }

  @Bean
  @ConditionalOnMissingBean(AmqpAdmin.class)
  public AmqpAdmin amqpAdmin() {
    return mock(AmqpAdmin.class);
  }
}
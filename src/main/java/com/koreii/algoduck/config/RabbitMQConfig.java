package com.koreii.algoduck.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {
  @Value("${rabbitmq.queue.request}")
  private String requestQueueName;

  @Value("${rabbitmq.queue.progress}")
  private String progressQueueName;

  @Value("${rabbitmq.queue.result}")
  private String resultQueueName;

  @Bean
  public Queue judgeRequestQueue() {
    return new Queue(requestQueueName, true);  // durable
  }

  @Bean
  public Queue judgeProgressQueue() {
    return new Queue(progressQueueName, true);
  }

  @Bean
  public Queue judgeResultQueue() {
    return new Queue(resultQueueName, true);
  }

  @Bean
  public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
    return new RabbitAdmin(connectionFactory);
  }

  @Bean
  public ApplicationRunner initQueues(RabbitAdmin rabbitAdmin) {
    return args -> rabbitAdmin.initialize(); // 큐 등록 트리거
  }

  @Bean
  public Jackson2JsonMessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate template = new RabbitTemplate(connectionFactory);
    template.setMessageConverter(messageConverter());
    return template;
  }
}

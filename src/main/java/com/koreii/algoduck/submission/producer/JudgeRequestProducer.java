package com.koreii.algoduck.submission.producer;

import com.koreii.algoduck.submission.message.request.JudgeRequestMessage;
import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JudgeRequestProducer {
  private final RabbitTemplate rabbitTemplate;

  @Value("${spring.rabbitmq.queue.name}")
  private String queueName;

  public void sendJudgeRequest(JudgeRequestMessage message) {
    rabbitTemplate.convertAndSend(queueName, message);
  }
}

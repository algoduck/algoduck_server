package com.koreii.algoduck.submission.producer;

import com.koreii.algoduck.submission.message.request.JudgeRequestMessage;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JudgeRequestProducer {
  private final RabbitTemplate rabbitTemplate;

  @Value("${rabbitmq.queue.request}")
  private String queueName;

  public void sendJudgeRequest(JudgeRequestMessage message) {
    log.info("message = {}", message);
    rabbitTemplate.convertAndSend(queueName, message);
  }
}

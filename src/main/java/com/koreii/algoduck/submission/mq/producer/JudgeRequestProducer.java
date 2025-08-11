package com.koreii.algoduck.submission.mq.producer;

import com.koreii.algoduck.submission.mq.message.request.JudgeRequestMessage;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

import static com.koreii.algoduck.util.constants.Constants.SHARD_COUNT;

@Component
@RequiredArgsConstructor
@Slf4j
public class JudgeRequestProducer {
  private final RabbitTemplate rabbitTemplate;

  @Value("${rabbitmq.queue.request}")
  private String requestQueuePrefix;
  private final AtomicInteger roundRobin = new AtomicInteger(0);

  public void sendJudgeRequest(JudgeRequestMessage message) {
    log.info("message = {}", message);
    int idx = Math.floorMod(roundRobin.getAndIncrement(), SHARD_COUNT); // 0..shardCount-1
    String queue = requestQueuePrefix + "-" + idx;             // judge-request-0/1/2/3
    log.info("send to {}, msg={}", queue, message);
    rabbitTemplate.convertAndSend(queue, message);
  }
}

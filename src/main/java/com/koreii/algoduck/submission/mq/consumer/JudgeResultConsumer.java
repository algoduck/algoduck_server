package com.koreii.algoduck.submission.mq.consumer;

import com.koreii.algoduck.submission.dto.request.SubmissionUpdateRequestDto;
import com.koreii.algoduck.submission.mq.message.result.JudgeResultMessage;
import com.koreii.algoduck.submission.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JudgeResultConsumer {
  private final SubmissionService submissionService;

  // judge-result-0/1/2/3 동시에 감시
  @RabbitListener(queues = {
      "${rabbitmq.queue.result}-0"
      //      "${rabbitmq.queue.result}-1",
      //      "${rabbitmq.queue.result}-2",
      //      "${rabbitmq.queue.result}-3"
  })
  public void receiveJudgeResult(JudgeResultMessage resultMessage) {
    log.info("채점 결과 수신: {}", resultMessage);

    SubmissionUpdateRequestDto updateDto = SubmissionUpdateRequestDto.builder()
        .submissionId(resultMessage.getSubmissionId())
        .status(resultMessage.getResult())
        .message(resultMessage.getMessage())
        .executionTime(resultMessage.getExecutionTime())
        .memoryUsage(resultMessage.getMemoryUsage())
        .build();

    try {
      submissionService.updateSubmission(updateDto);
    } catch (Exception e) {
      log.info("채점 결과 처리 중 무시할 예외 발생");
    }
  }
}

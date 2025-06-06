package com.koreii.algoduck.submission.sse;

import com.koreii.algoduck.submission.dto.response.JudgeProgressDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class SubmissionProgressEmitter {

  private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();

  public SseEmitter createEmitter(Long submissionId) {
    SseEmitter emitter = new SseEmitter(60 * 1000L);
    emitters.put(submissionId, emitter);

    emitter.onTimeout(() -> {
      log.warn("SSE 타임아웃 발생: submissionId = {}", submissionId);
      emitter.complete();
      emitters.remove(submissionId);

    });
    emitter.onCompletion(() -> emitters.remove(submissionId));

    return emitter;
  }

  public void sendProgress(Long submissionId, JudgeProgressDto progressDto) {
    SseEmitter emitter = emitters.get(submissionId);
    if (emitter != null) {
      try {
        emitter.send(SseEmitter.event()
            .name("progress")
            .data(progressDto));

        if (!"PASS".equals(progressDto.getResult())) {
          emitter.complete();
          emitters.remove(submissionId);
        }
      } catch (IOException e) {
        emitter.completeWithError(e);
      }
    }
  }
}
package com.koreii.algoduck.submission.sse;

import com.koreii.algoduck.submission.dto.response.JudgeProgressDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SubmissionProgressEmitter {

  private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();

  public SseEmitter createEmitter(Long submissionId) {
    SseEmitter emitter = new SseEmitter(60 * 1000L);
    emitters.put(submissionId, emitter);

    emitter.onTimeout(() -> emitters.remove(submissionId));
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
      } catch (IOException e) {
        emitter.completeWithError(e);
      }
    }
  }
}
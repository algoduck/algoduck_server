package com.koreii.algoduck.submission.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koreii.algoduck.submission.controller.SubmissionController;
import com.koreii.algoduck.submission.dto.request.JudgeRequestDto;
import com.koreii.algoduck.submission.dto.response.JudgeProgressDto;
import com.koreii.algoduck.submission.dto.response.JudgeResponseDto;
import com.koreii.algoduck.submission.sse.SubmissionProgressEmitter;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class JudgeWebSocketClient extends WebSocketClient {
  private static final ObjectMapper objectMapper = new ObjectMapper();
  private final JudgeRequestDto judgeRequestDto;
  private final SubmissionProgressEmitter submissionProgressEmitter;
  private final CompletableFuture<JudgeResponseDto> future;

  public JudgeWebSocketClient(URI serverUri, JudgeRequestDto judgeRequestDto, SubmissionProgressEmitter submissionProgressEmitter, CompletableFuture<JudgeResponseDto> future) {
    super(serverUri);
    this.judgeRequestDto = judgeRequestDto;
    this.submissionProgressEmitter = submissionProgressEmitter;
    this.future = future;

  }

  @Override
  public void onOpen(ServerHandshake handshakedata) {
    try {
      send(new ObjectMapper().writeValueAsString(judgeRequestDto));
    } catch (JsonProcessingException e) {
      onError(e);
    }
  }

  @Override
  public void onMessage(String message) {
    try {
      JudgeProgressDto progressDto = objectMapper.readValue(message, JudgeProgressDto.class);
      log.info("채점 진행중: {}%", progressDto.getPercentage());

      //  실시간 진행률을 클라이언트에 전송 (SSE)
      submissionProgressEmitter.sendProgress(judgeRequestDto.getSubmissionId(), progressDto);

      //  최종 성공
      if ("AC".equals(progressDto.getResult())) {
        log.info("Accepted");
        future.complete(new JudgeResponseDto(progressDto));
        close();
      }  else if (!"PASS".equals(progressDto.getResult())) {  //  중단 조건 도달 (e.g. WA, TLE, RE...)
        log.warn("중단 - {}", progressDto.getResult());
        future.complete(new JudgeResponseDto(progressDto));
        close();
      }
    } catch (JsonProcessingException e) {
      onError(e);
    }
  }

  @Override
  public void onClose(int code, String reason, boolean remote) {
    log.info("WebSocket 연결 종료");
  }

  @Override
  public void onError(Exception ex) {
    log.error("예외 발생", ex);
    future.completeExceptionally(ex);
  }
}

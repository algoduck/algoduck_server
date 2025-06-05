package com.koreii.algoduck.submission.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koreii.algoduck.submission.dto.request.JudgeRequestDto;
import com.koreii.algoduck.submission.dto.response.JudgeProgressDto;
import com.koreii.algoduck.submission.dto.response.JudgeResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class JudgeWebSocketClient extends WebSocketClient {
  private static final ObjectMapper objectMapper = new ObjectMapper();
  private final JudgeRequestDto judgeRequestDto;
  private final CompletableFuture<JudgeResponseDto> future;

  public JudgeWebSocketClient(URI serverUri, JudgeRequestDto judgeRequestDto, CompletableFuture<JudgeResponseDto> future) {
    super(serverUri);
    this.judgeRequestDto = judgeRequestDto;
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
      JudgeProgressDto progress = objectMapper.readValue(message, JudgeProgressDto.class);
      log.info("채점 진행중: {}%", progress.getPercentage());

      //  최종 성공
      if ("AC".equals(progress.getResult())) {
        future.complete(new JudgeResponseDto(progress));
        close();
      }

      //  중단 조건 도달 (e.g. WA, TLE, RE...)
      if (!"PASS".equals(progress.getResult())) {
        log.warn("중단 - {}", progress.getResult());
        future.complete(new JudgeResponseDto(progress));
        close();
      }
    } catch (JsonProcessingException e) {
      onError(e);
    }
    // 결과를 DB에 저장하거나 SSE로 React에 push
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

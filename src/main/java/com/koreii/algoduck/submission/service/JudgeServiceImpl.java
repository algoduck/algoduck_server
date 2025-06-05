package com.koreii.algoduck.submission.service;

import com.koreii.algoduck.submission.dto.request.JudgeRequestDto;
import com.koreii.algoduck.submission.dto.request.SubmissionRequestDto;
import com.koreii.algoduck.submission.dto.response.JudgeResponseDto;
import com.koreii.algoduck.submission.websocket.JudgeWebSocketClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class  JudgeServiceImpl implements JudgeService {
  @Value("${judge.server.ws-judge}")
  private String wsJudgeUrl;
  @Value("${judge.server.https-judge}")
  private String httpsJudgeUrl;

  @Async("judgeExecutor")
  @Override
  public CompletableFuture<JudgeResponseDto> requestJudge(JudgeRequestDto judgeRequestDto) {
    CompletableFuture<JudgeResponseDto> future = new CompletableFuture<>();

    try {
      URI uri = new URI(wsJudgeUrl);

      log.info("uri = {}", uri);
      JudgeWebSocketClient judgeWebSocketClient = new JudgeWebSocketClient(uri, judgeRequestDto, future);
      judgeWebSocketClient.connect();
    } catch (URISyntaxException e) {
      log.error("잘못된 Judge 서버 URI");
      future.completeExceptionally(e);
    }

    return future;
  }
}

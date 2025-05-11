package com.koreii.algoduck.submission.service;

import com.koreii.algoduck.submission.dto.request.JudgeRequestDto;
import com.koreii.algoduck.submission.dto.response.JudgeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class JudgeServiceImpl implements JudgeService {
  private final RestTemplate restTemplate;

  @Value("${judge.server.judge}")
  private String judgeUrl;

  @Async("judgeExecutor")
  @Override
  public CompletableFuture<JudgeResponseDto> requestJudge(JudgeRequestDto judgeRequestDto) {
    ResponseEntity<JudgeResponseDto> response = restTemplate.postForEntity(judgeUrl, judgeRequestDto, JudgeResponseDto.class);
    return CompletableFuture.completedFuture(response.getBody());
  }
}

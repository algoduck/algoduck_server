package com.koreii.algoduck.submission.service;

import com.koreii.algoduck.submission.dto.request.JudgeRequestDto;
import com.koreii.algoduck.submission.dto.request.SubmissionRequestDto;
import com.koreii.algoduck.submission.dto.response.JudgeResponseDto;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public interface JudgeService {
  CompletableFuture<JudgeResponseDto> requestJudge(JudgeRequestDto judgeRequestDto);
}

package com.koreii.algoduck.submission.controller;

import com.koreii.algoduck.base.dto.response.ApiResponse;
import com.koreii.algoduck.submission.dto.request.SubmissionRequestDto;
import com.koreii.algoduck.submission.dto.response.SubmissionResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Slf4j
class TestSubmissionControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void simulateMassiveMaliciousSubmissionsWithResultCheck() throws InterruptedException {
    int totalSubmissions = 100;

    for (int i = 0; i < totalSubmissions; i++) {
      // 랜덤 memberId (1 ~ 1_000_000)
      Long memberId = (long) (Math.random() * 1_000_000) + 1;

      SubmissionRequestDto dto = new SubmissionRequestDto(
          memberId,
          1003L,
          "public class Main {\n\tpublic static void main(String[] args) {\n\t\twhile(true);\n\t}\n}",
          1001L
      );

      // POST 요청 타입 지정 (제출)
      ParameterizedTypeReference<ApiResponse<SubmissionResponseDto>> postTypeRef =
          new ParameterizedTypeReference<>() {
          };

      ResponseEntity<ApiResponse<SubmissionResponseDto>> postResponse = restTemplate.exchange(
          "/submissions/test",
          HttpMethod.POST,
          new HttpEntity<>(dto),
          postTypeRef
      );

      assertThat(postResponse.getStatusCode()).isIn(HttpStatus.CREATED, HttpStatus.OK);
      SubmissionResponseDto submission = postResponse.getBody().getData();
      assertThat(submission.getSubmissionId()).isNotNull();

      Long submissionId = submission.getSubmissionId();
      log.info("submissionId = {}", submissionId);

      // GET 요청 타입 지정 (채점 결과 조회)
      ParameterizedTypeReference<ApiResponse<SubmissionResponseDto>> getTypeRef =
          new ParameterizedTypeReference<>() {
          };

      SubmissionResponseDto result = null;

      // 채점 완료될 때까지 polling (최대 10초, 500ms 간격)
      for (int j = 0; j < 20; j++) {
        ResponseEntity<ApiResponse<SubmissionResponseDto>> getResponse = restTemplate.exchange(
            "/submissions/test/" + submissionId,
            HttpMethod.GET,
            null,
            getTypeRef
        );

        result = getResponse.getBody().getData();
        if (!"JUDGING".equals(result.getStatus().name())) {
          break;
        }

        Thread.sleep(500);
      }

      log.info("Submission #{} → 최종 상태: {}", submissionId, result.getStatus());
      assertThat(result.getStatus().name()).isNotEqualTo("JUDGING");
    }
  }

  @Test
  void simulateConcurrentMaliciousSubmissionsWithDelayedResultCheck() throws InterruptedException {
    int totalSubmissions = 1000;
    List<Long> submissionIds = new ArrayList<>();

    // 1단계: 동시에 제출 요청 보내고 submissionId 수집
    ParameterizedTypeReference<ApiResponse<SubmissionResponseDto>> postTypeRef =
        new ParameterizedTypeReference<>() {
        };

    for (int i = 0; i < totalSubmissions; i++) {
      Long memberId = (long) (Math.random() * 1_000_000) + 1;

      SubmissionRequestDto dto = new SubmissionRequestDto(
          memberId,
          1003L,
          "public class Main {\n\tpublic static void main(String[] args) {\n\t\twhile(true);\n\t}\n}",
          1001L
      );

      ResponseEntity<ApiResponse<SubmissionResponseDto>> postResponse = restTemplate.exchange(
          "/submissions/test",
          HttpMethod.POST,
          new HttpEntity<>(dto),
          postTypeRef
      );

      assertThat(postResponse.getStatusCode()).isIn(HttpStatus.CREATED, HttpStatus.OK);
      SubmissionResponseDto submission = postResponse.getBody().getData();
      assertThat(submission.getSubmissionId()).isNotNull();

      submissionIds.add(submission.getSubmissionId());
      log.info("제출 완료: submissionId = {}", submission.getSubmissionId());
    }

    // 잠깐 기다렸다가 (ex. Judge 서버에서 처리할 시간 줌)
    Thread.sleep(2000);

    // 2단계: 각 제출에 대한 결과 확인
    ParameterizedTypeReference<ApiResponse<SubmissionResponseDto>> getTypeRef =
        new ParameterizedTypeReference<>() {
        };

    for (Long submissionId : submissionIds) {
      SubmissionResponseDto result = null;

      for (int j = 0; j < 20; j++) {
        ResponseEntity<ApiResponse<SubmissionResponseDto>> getResponse = restTemplate.exchange(
            "/submissions/test/" + submissionId,
            HttpMethod.GET,
            null,
            getTypeRef
        );

        result = getResponse.getBody().getData();
        if (!"JUDGING".equals(result.getStatus().name())) {
          break;
        }

        Thread.sleep(500);
      }

      assertThat(result).isNotNull();
      log.info("Submission #{} → 최종 상태: {}", submissionId, result.getStatus());
      assertThat(result.getStatus().name()).isNotEqualTo("JUDGING");
    }
  }

}

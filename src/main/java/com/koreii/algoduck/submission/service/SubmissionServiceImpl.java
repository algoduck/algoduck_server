package com.koreii.algoduck.submission.service;

import com.koreii.algoduck.base.dto.page.PageResponse;
import com.koreii.algoduck.exceptions.file.submission.SubmissionFailureException;
import com.koreii.algoduck.file.FileStorageService;
import com.koreii.algoduck.problem.dto.response.ProblemResponseDto;
import com.koreii.algoduck.problem.service.ProblemService;
import com.koreii.algoduck.submission.dto.request.JudgeRequestDto;
import com.koreii.algoduck.submission.dto.request.SubmissionRequestDto;
import com.koreii.algoduck.submission.dto.request.SubmissionSaveRequestDto;
import com.koreii.algoduck.submission.dto.request.SubmissionUpdateRequestDto;
import com.koreii.algoduck.submission.dto.response.JudgeResponseDto;
import com.koreii.algoduck.submission.dto.response.SubmissionResponseDto;
import com.koreii.algoduck.submission.repository.SubmissionRepository;
import com.koreii.algoduck.version.dto.response.VersionResponseDto;
import com.koreii.algoduck.version.service.VersionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubmissionServiceImpl implements SubmissionService {
  private final VersionService versionService;
  private final ProblemService problemService;
  private final JudgeService judgeService;
  private final SubmissionRepository submissionRepository;
  private final FileStorageService fileStorageService;

  @Value("${spring.cloud.aws.s3.submission_bucket}")
  private String bucketName;
  private VersionResponseDto versionResponseDto;

  @Override
  @Transactional
  public SubmissionResponseDto submit(SubmissionRequestDto submissionRequestDto) {
    ProblemResponseDto problemResponseDto = problemService.findDtoByProblemId(submissionRequestDto.getProblemId());
    VersionResponseDto versionResponseDto = versionService.findByVersionId(submissionRequestDto.getVersionId());

    SubmissionSaveRequestDto submissionSaveRequestDto = new SubmissionSaveRequestDto(submissionRequestDto);
    SubmissionResponseDto submissionResponseDto = submissionRepository.saveSubmission(submissionSaveRequestDto);

    String sourceCode = submissionRequestDto.getSourceCode();
    String codeName = problemResponseDto.getProblemNumber() + "." + versionResponseDto.getExtension();
    Path tempFile = null;
    MultipartFile multipartFile = null;

    try {
      tempFile = Files.createTempFile(codeName, "." + versionResponseDto.getExtension());
      Files.write(tempFile, sourceCode.getBytes(StandardCharsets.UTF_8));
      File file = tempFile.toFile();

      try (FileInputStream fis = new FileInputStream(file)) {
        multipartFile = new MockMultipartFile(
            file.getName(),
            file.getName(),
            "text/plain",
            fis
        );
      }

      // 비동기 S3 업로드
      CompletableFuture<String> uploadResult = fileStorageService.uploadFile(
          bucketName,
          "submission/" + submissionRequestDto.getProblemId(),
          multipartFile
      );

      uploadResult.thenAccept(codeUrl -> {
        SubmissionUpdateRequestDto updateDto = SubmissionUpdateRequestDto.builder()
            .codeName(codeName)
            .codeUrl(codeUrl)
            .build();
        updateSubmission(updateDto);
      }).exceptionally(ex -> {
        log.error("소스코드 업로드 실패", ex);
        return null;
      });

    } catch (Exception e) {
      log.error("{} 파일 업로드 실패 (소스코드 전송 실패)", codeName, e);
      throw new SubmissionFailureException("소스코드 전송 실패", e);
    } finally {
      if (tempFile != null) {
        try {
          Files.deleteIfExists(tempFile);
        } catch (IOException e) {
          log.warn("임시 파일 삭제 실패: {}", tempFile, e);
        }
      }
    }

    JudgeRequestDto judgeRequestDto = JudgeRequestDto.builder()
        .problemId(submissionRequestDto.getProblemId())
        .language(versionResponseDto.getLanguageName())
        .version(versionResponseDto.getVersionName())
        .timeLimitation(problemResponseDto.getTimeLimitation())
        .memoryLimitation(problemResponseDto.getMemoryLimitation())
        .sourceCode(sourceCode)
        .build();

    CompletableFuture<JudgeResponseDto> judgeResult = judgeService.requestJudge(judgeRequestDto);
    judgeResult.thenAccept(judgeResponseDto -> {
      SubmissionUpdateRequestDto submissionUpdateRequestDto = SubmissionUpdateRequestDto.builder()
            .status(judgeResponseDto.getResult())
            .message(judgeResponseDto.getMessage())
            .executionTime(judgeResponseDto.getExecutionTime())
            .memoryUsage(judgeResponseDto.getMemoryUsage())
            .build();
      updateSubmission(submissionUpdateRequestDto);
    })
        .exceptionally(ex -> {
          log.error("채점 실패", ex);
          return null;
        });

    // 즉시 사용자에게 "채점 중" 메시지 반환
    return submissionResponseDto;
  }

  @Override
  public SubmissionResponseDto findBySubmissionId(Long submissionId) {
    return new SubmissionResponseDto(submissionRepository.findBySubmissionId(submissionId));
  }

  @Override
  @Transactional
  public SubmissionResponseDto updateSubmission(SubmissionUpdateRequestDto submissionUpdateRequestDto) {
    return submissionRepository.updateSubmission(submissionUpdateRequestDto);
  }

  // 첫 페이지 (가장 최근 제출부터 시작)
  @Override
  public PageResponse<SubmissionResponseDto> getFirstPage(int pageSize) {
    return submissionRepository.findNextPage(null, pageSize);
  }

  // 다음 페이지 요청 (현재 마지막 ID 기준으로 다음 데이터 조회)
  @Override
  public PageResponse<SubmissionResponseDto> getNextPage(Long lastSeenId, int pageSize) {
    return submissionRepository.findNextPage(lastSeenId, pageSize);
  }

  // 이전 페이지 요청 (현재 첫 ID 기준으로 더 최신 데이터 조회)
  @Override
  public PageResponse<SubmissionResponseDto> getPrevPage(Long firstSeenId, int pageSize) {
    return submissionRepository.findPrevPage(firstSeenId, pageSize);
  }

}

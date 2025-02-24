package com.koreii.algoduck.testcase.service;

import java.util.ArrayList;
import java.util.List;

import com.koreii.algoduck.problemimage.dto.response.ProblemImageResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.koreii.algoduck.exceptions.file.FileUploadFailException;
import com.koreii.algoduck.file.FileStorageService;
import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.problem.repository.ProblemRepository;
import com.koreii.algoduck.testcase.dto.request.TestcaseAddRequestDto;
import com.koreii.algoduck.testcase.dto.response.TestcaseResponseDto;
import com.koreii.algoduck.testcase.repository.TestcaseRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TestcaseServiceImpl implements TestcaseService {
  private final TestcaseRepository testcaseRepository;
  private final ProblemRepository problemRepository;
  private final FileStorageService fileStorageService;

  @Value("${spring.cloud.aws.s3.testcase_bucket}")
  private String bucketName;

  @Override
  @Transactional
  public TestcaseResponseDto addTestcase(Long problemId, MultipartFile testcaseInput, MultipartFile testcaseOutput, boolean isPublic) {
    Problem problem = problemRepository.findByProblemId(problemId);
    String testcaseInputName = testcaseInput.getName();
    String testcaseOutputName = testcaseOutput.getName();

    String testcaseInputUrl = null;
    String testcaseOutputUrl = null;

    try {
      testcaseInputUrl = fileStorageService.uploadFile(bucketName, "testcase/" + problem.getProblemId() + "/input", testcaseInput);
      testcaseOutputUrl = fileStorageService.uploadFile(bucketName, "testcase/" + problem.getProblemId() + "/output", testcaseOutput);
    } catch (FileUploadFailException e) {
      log.error("Fail to upload testcase file", e);
      throw e;
    }

    try {
      TestcaseAddRequestDto testcaseAddRequestDto = TestcaseAddRequestDto.builder()
          .testcaseInputName(testcaseInputName)
          .testcaseInputUrl(testcaseInputUrl)
          .testcaseOutputName(testcaseOutputName)
          .testcaseOutputUrl(testcaseOutputUrl)
          .isPublic(isPublic)
          .build();

      return testcaseRepository.addTestcase(problem, testcaseAddRequestDto);
    } catch (Exception e) {
      log.error("testcase DB insert fail", e);
      fileStorageService.deleteFile(bucketName, testcaseInputUrl);
      fileStorageService.deleteFile(bucketName, testcaseOutputUrl);
      throw e;
    }
  }

  @Override
  @Transactional
  public List<TestcaseResponseDto> addTestcases(Long problemId, List<MultipartFile> testcaseInputs, List<MultipartFile> testcaseOutputs, List<Boolean> isPublics) {
    List<TestcaseResponseDto> testcaseResponseDtos = new ArrayList<>();

    for (int i = 0; i < testcaseInputs.size(); i++) {
      MultipartFile testcaseInput = testcaseInputs.get(i);
      MultipartFile testcaseOutput = testcaseOutputs.get(i);
      boolean isPublic = isPublics.get(i);

      try {
        TestcaseResponseDto testcaseResponseDto = addTestcase(problemId, testcaseInput, testcaseOutput, isPublic);
        testcaseResponseDtos.add(testcaseResponseDto);
      } catch (FileUploadFailException e) {
        log.error("Failed to upload testcase file to file storage, but continuing...");
      } catch (Exception e) {
        log.error("Failed to insert testcase to DB, but continuing...");
      }
    }

    return testcaseResponseDtos;
  }

  @Override
  public TestcaseResponseDto findByTestcaseId(Long testcaseId) {
    return new TestcaseResponseDto(testcaseRepository.findByTestcaseId(testcaseId));
  }

  @Override
  public List<TestcaseResponseDto> selectTestcasesByProblemId(Long problemId) {
    return testcaseRepository.selectTestcasesByProblemId(problemId);
  }
}

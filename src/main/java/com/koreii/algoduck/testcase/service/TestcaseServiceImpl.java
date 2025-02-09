package com.koreii.algoduck.testcase.service;

import com.koreii.algoduck.file.FileStorageService;
import com.koreii.algoduck.problem.dto.response.ProblemResponseDto;
import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.problem.repository.ProblemRepository;
import com.koreii.algoduck.testcase.dto.response.TestcaseResponseDto;
import com.koreii.algoduck.testcase.repository.TestcaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
@RequiredArgsConstructor
public class TestcaseServiceImpl implements TestcaseService {
  private final TestcaseRepository testcaseRepository;
  private final ProblemRepository problemRepository;
  private final FileStorageService fileStorageService;

  @Value("${spring.cloud.aws.s3.testcase_bucket}")
  private String bucketName;
  
  @Override
  public TestcaseResponseDto addTestcase(Long problemId, MultipartFile testcaseInput, MultipartFile testcaseOutput, boolean isPublic) {
    Problem problem = problemRepository.findByProblemId(problemId);


    return null;
  }
}

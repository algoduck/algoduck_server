package com.koreii.algoduck.problemimage.service;

import com.koreii.algoduck.exceptions.file.FileUploadFailException;
import com.koreii.algoduck.file.FileStorageService;
import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.problem.repository.ProblemRepository;
import com.koreii.algoduck.problemimage.dto.request.ProblemImageAddRequestDto;
import com.koreii.algoduck.problemimage.dto.response.ProblemImageResponseDto;
import com.koreii.algoduck.problemimage.entity.ProblemImage;
import com.koreii.algoduck.problemimage.repository.ProblemImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProblemImageServiceImpl implements ProblemImageService {
  private final ProblemImageRepository problemImageRepository;
  private final ProblemRepository problemRepository;
  private final FileStorageService fileStorageService;

  @Value("s3.problem_image_bucket")
  private String bucketName;

  @Override
  public ProblemImageResponseDto addProblemImage(Long problemId, MultipartFile problemImage) {
    Problem problem = problemRepository.findByProblemId(problemId);
    String problemImageName = problemImage.getName();

    String problemImageUrl = null;

    try {
      problemImageUrl = fileStorageService.uploadFile(bucketName, "problem-image/" + problem.getProblemId(), problemImage);
    } catch (FileUploadFailException e) {
      log.error("Fail to upload problem image file", e);
      throw e;
    }

    try {
      ProblemImageAddRequestDto problemImageAddRequestDto = ProblemImageAddRequestDto.builder()
          .problemImageName(problemImageName)
          .problemImageUrl(problemImageUrl)
          .build();

      return problemImageRepository.addProblemImage(problem, problemImageAddRequestDto);
    } catch (Exception e) {
      log.error("problem image DB insert fail");
      fileStorageService.deleteFile(bucketName, problemImageUrl);
      throw e;
    }
  }

  @Override
  public ProblemImage findByProblemImageId(Long problemImageId) {
    return problemImageRepository.findByProblemImageId(problemImageId);
  }
}

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProblemImageServiceImpl implements ProblemImageService {
  private final ProblemImageRepository problemImageRepository;
  private final FileStorageService fileStorageService;

  @Value("s3.problem_image_bucket")
  private String bucketName;

  @Override
  @Transactional
  public ProblemImageResponseDto addProblemImage(Problem problem, MultipartFile problemImage) {
    String problemImageName = problemImage.getName();

    String problemImageUrl = null;

    try {
      problemImageUrl = fileStorageService.uploadFile(bucketName, "problem-image/" + problem.getProblemId(), problemImage).get();
    } catch (Exception e) {
      log.error("Fail to upload problem image file", e);
      throw new FileUploadFailException(e);
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
  @Transactional
  public List<ProblemImageResponseDto> addProblemImages(Problem problem, List<MultipartFile> problemImageFiles) {
    List<ProblemImageResponseDto> problemImageResponseDtos = new ArrayList<>();

    for (MultipartFile problemImage : problemImageFiles) {
      try {
        ProblemImageResponseDto problemImageResponseDto = addProblemImage(problem, problemImage);
        problemImageResponseDtos.add(problemImageResponseDto);
      } catch (FileUploadFailException e) {
        log.error("Failed to upload image file to file storage, but continuing...");
      } catch (Exception e) {
        log.error("Failed to insert problem image to DB, but continuing...");
      }
    }

    return problemImageResponseDtos;
  }

  @Override
  public ProblemImage findByProblemImageId(Long problemImageId) {
    return problemImageRepository.findByProblemImageId(problemImageId);
  }
}

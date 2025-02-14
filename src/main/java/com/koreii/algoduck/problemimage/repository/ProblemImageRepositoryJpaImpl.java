package com.koreii.algoduck.problemimage.repository;

import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.problemimage.dto.request.ProblemImageAddRequestDto;
import com.koreii.algoduck.problemimage.dto.response.ProblemImageResponseDto;
import com.koreii.algoduck.problemimage.entity.ProblemImage;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class ProblemImageRepositoryJpaImpl implements ProblemImageRepository {
  private final EntityManager entityManager;

  @Value("${spring.cloud.aws.s3.testcase_bucket}")
  private String bucketName;

  @Override
  @Transactional
  public ProblemImageResponseDto addProblemImage(Problem problem, ProblemImageAddRequestDto problemImageAddRequestDto) {
    ProblemImage problemImage = ProblemImage.builder()
        .problem(problem)
        .problemImageName(problemImageAddRequestDto.getProblemImageName())
        .problemImageUrl(problemImageAddRequestDto.getProblemImageUrl())
        .build();

    entityManager.persist(problemImage);
    return new ProblemImageResponseDto(problemImage);
  }

  @Override
  public ProblemImage findByProblemImageId(Long problemImageId) {
    return entityManager.find(ProblemImage.class, problemImageId);
  }
}

package com.koreii.algoduck.problemimage.repository;

import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.problemimage.dto.request.ProblemImageAddRequestDto;
import com.koreii.algoduck.problemimage.dto.response.ProblemImageResponseDto;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemImageRepository {
  //  ProblemImageService에서 문제 이미지가 저장소에 제대로 저장된 다음에 해당 경로를 이용해 DB에 저장
  ProblemImageResponseDto addProblemImage(Problem problem, ProblemImageAddRequestDto problemImageAddRequestDto);
}

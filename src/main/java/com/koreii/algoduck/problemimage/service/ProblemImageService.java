package com.koreii.algoduck.problemimage.service;

import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.problemimage.dto.response.ProblemImageResponseDto;
import com.koreii.algoduck.problemimage.entity.ProblemImage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ProblemImageService {
  ProblemImageResponseDto addProblemImage(Problem problem, MultipartFile problemImageFile);

  List<ProblemImageResponseDto> addProblemImages(Problem problem, List<MultipartFile> problemImageFiles);

  ProblemImage findByProblemImageId(Long problemImageId);
}

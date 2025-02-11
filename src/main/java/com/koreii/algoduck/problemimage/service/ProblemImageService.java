package com.koreii.algoduck.problemimage.service;

import com.koreii.algoduck.problemimage.dto.response.ProblemImageResponseDto;
import com.koreii.algoduck.problemimage.entity.ProblemImage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ProblemImageService {
  ProblemImageResponseDto addProblemImage(Long problemId, MultipartFile problemImageFile);

  ProblemImage findByProblemImageId(Long problemImageId);
}

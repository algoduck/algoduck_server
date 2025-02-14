package com.koreii.algoduck.problemimage.service;

import com.koreii.algoduck.problemimage.dto.response.ProblemImageResponseDto;
import com.koreii.algoduck.problemimage.entity.ProblemImage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ProblemImageService {
  ProblemImageResponseDto addProblemImage(Long problemId, MultipartFile problemImageFile);

  List<ProblemImageResponseDto> addProblemImages(Long problemId, List<MultipartFile> problemImageFiles);

  ProblemImage findByProblemImageId(Long problemImageId);
}

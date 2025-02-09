package com.koreii.algoduck.problemimage.dto.response;

import com.koreii.algoduck.problemimage.entity.ProblemImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ProblemImageResponseDto {
  private Long problemImageId;
  private Long problemId;
  private String problemImageName;
  private String problemImageUrl;

  public ProblemImageResponseDto(ProblemImage problemImage) {
    this.problemImageId = problemImage.getProblemImageId();
    this.problemId = problemImage.getProblem().getProblemId();
    this.problemImageName = problemImage.getProblemImageName();
    this.problemImageUrl = problemImage.getProblemImageUrl();
  }
}

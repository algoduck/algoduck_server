package com.koreii.algoduck.problemimage.entity;

import com.koreii.algoduck.problem.entity.Problem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "problem_images")
@SequenceGenerator(
    name = "PROBLEM_IMAGES_SEQ_GENERATOR",
    sequenceName = "PROBLEM_IMAGES_SEQ"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProblemImage {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROBLEM_IMAGES_SEQ_GENERATOR")
  @Column(name = "problem_image_id")
  private Long problemImageId;

  @ManyToOne
  @JoinColumn(name = "problem_id")
  private Problem problem;

  @Column(name = "problem_image_name", nullable = false)
  @Setter
  private String problemImageName;

  @Column(name = "problem_image_url", nullable = false, columnDefinition = "TEXT")
  @Setter
  private String problemImageUrl;
}

package com.koreii.algoduck.problem.entity;

import com.koreii.algoduck.base.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "problems")
@SequenceGenerator(
    name = "PROBLEMS_SEQ_GENERATOR",
    sequenceName = "PROBLEMS_SEQ"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Problem extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROBLEMS_SEQ_GENERATOR")
  @Column(name = "problem_id")
  private Long problemId;

  @Column(name = "problem_number", unique = true, nullable = false)
  private Integer problemNumber;

  @Column(nullable = false, columnDefinition = "TEXT")
  @Setter
  private String description;

  @Column(name = "input_description", nullable = false, columnDefinition = "TEXT")
  @Setter
  private String inputDescription;

  @Column(name = "output_description", nullable = false, columnDefinition = "TEXT")
  @Setter
  private String outputDescription;

  @Column(nullable = false)
  @Setter
  private Integer difficulty;
}

package com.koreii.algoduck.problemalgorithm.entity;

import com.koreii.algoduck.algorith.entity.Algorithm;
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

@Entity
@Table(name = "problems_algorithms")
@SequenceGenerator(
    name = "PROBLEMS_ALGORITHMS_SEQ_GENERATOR",
    sequenceName = "PROBLEMS_ALGORITHMS_SEQ"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProblemAlgorithm {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROBLEMS_ALGORITHMS_SEQ_GENERATOR")
  @Column(name = "problem_algorithm_id")
  private Long problemAlgorithmId;

  @ManyToOne
  @JoinColumn(name = "problemId")
  private Problem problem;

  @ManyToOne
  @JoinColumn(name = "algorithmId")
  private Algorithm algorithm;
}

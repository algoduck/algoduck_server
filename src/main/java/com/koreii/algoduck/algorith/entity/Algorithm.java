package com.koreii.algoduck.algorith.entity;

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
@Table(name = "algorithms")
@SequenceGenerator(
    name = "ALGORITHMS_SEQ_GENERATOR",
    sequenceName = "ALGORITHMS_SEQ"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Algorithm {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALGORITHMS_SEQ_GENERATOR")
  @Column(name = "algorithm_id")
  private Long algorithmId;

  @Column(name = "algorithm_name", nullable = false)
  @Setter
  private String algorithmName;
}

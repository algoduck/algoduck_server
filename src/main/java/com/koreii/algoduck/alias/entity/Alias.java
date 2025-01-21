package com.koreii.algoduck.alias.entity;

import com.koreii.algoduck.algorith.entity.Algorithm;
import com.koreii.algoduck.base.entity.BaseTimeEntity;
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
@Table(name = "aliases")
@SequenceGenerator(
    name = "ALIASES_SEQ_GENERATOR",
    sequenceName = "ALIASES_SEQ"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Alias extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALIASES_SEQ_GENERATOR")
  @Column(name = "alias_id")
  private Long aliasId;

  @ManyToOne
  @JoinColumn(name = "algorithm_id")
  private Algorithm algorithm;

  @Column(name = "alias_name", nullable = false)
  @Setter
  private String aliasName;
}

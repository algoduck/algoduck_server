package com.koreii.algoduck.version.entity;

import com.koreii.algoduck.base.entity.BaseTimeEntity;
import com.koreii.algoduck.language.entity.Language;
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
@Table(name = "versions")
@SequenceGenerator(
    name = "VERSIONS_SEQ_GENERATOR",
    sequenceName = "VERSIONS_SEQ"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Version extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VERSIONS_SEQ_GENERATOR")
  @Column(name = "version_id")
  private Long versionId;

  @ManyToOne
  @JoinColumn(name = "language_id")
  private Language language;

  @Column(name = "version_name", nullable = false)
  @Setter
  private String versionName;
}

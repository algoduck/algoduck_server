package com.koreii.algoduck.language.entity;

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
@Table(name = "languages")
@SequenceGenerator(
    name = "LANGUAGES_SEQ_GENERATOR",
    sequenceName = "LANGUAGES_SEQ"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Language {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LANGUAGES_SEQ_GENERATOR")
  @Column(name = "language_id")
  private Long languageId;

  @Column(nullable = false)
  @Setter
  private String name;
}

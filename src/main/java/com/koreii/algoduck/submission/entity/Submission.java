package com.koreii.algoduck.submission.entity;

import com.koreii.algoduck.base.entity.BaseTimeEntity;
import com.koreii.algoduck.member.entity.Member;
import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.submission.enums.Status;
import com.koreii.algoduck.version.entity.Version;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "submissions")
@SequenceGenerator(
    name = "SUBMISSIONS_SEQ_GENERATOR",
    sequenceName = "SUBMISSIONS_SEQ",
    allocationSize = 1
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Submission extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBMISSIONS_SEQ_GENERATOR")
  @Column(name = "submission_id")
  private Long submissionId;

  @ManyToOne
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @ManyToOne
  @JoinColumn(name = "problem_id", nullable = false)
  private Problem problem;

  @Column(name = "code_name", nullable = false)
  @Setter
  private String codeName;

  @Column(name = "code_url", nullable = false, columnDefinition = "TEXT")
  private String codeUrl;

  @OneToOne
  @JoinColumn(name = "version_id", nullable = false)
  private Version version;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  @Setter
  private Status status;

  @Column(name = "execution_time", nullable = false)
  @Setter
  private Integer executionTime;

  @Column(name = "memory_usage", nullable = false)
  @Setter
  private Integer memoryUsage;
}

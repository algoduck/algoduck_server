package com.koreii.algoduck.testcase.entitiy;

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
@Table(name = "testcases")
@SequenceGenerator(
    name = "TESTCASES_SEQ_GENERATOR",
    sequenceName = "TESTCASES_SEQ"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Testcase {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TESTCASES_SEQ_GENERATOR")
  @Column(name = "testcase_id")
  private Long testcaseId;

  @ManyToOne
  @JoinColumn(name = "problem_id")
  private Problem problem;

  @Column(name = "testcase_input_name", nullable = false)
  @Setter
  private String testcaseInputName;

  @Column(name = "testcase_input_url", nullable = false, columnDefinition = "TEXT")
  private String testcaseInputUrl;

  @Column(name = "testcase_output_name", nullable = false)
  @Setter
  private String testcaseOutputName;

  @Column(name = "testcase_Output_url", nullable = false, columnDefinition = "TEXT")
  private String testcaseOutputUrl;

  @Column(name = "is_public", nullable = false)
  private Boolean isPublic;
}

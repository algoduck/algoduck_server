package com.koreii.algoduck.solved.entity;

import com.koreii.algoduck.member.entity.Member;
import com.koreii.algoduck.problem.entity.Problem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "solved_problems", uniqueConstraints = {
    @UniqueConstraint(
        name = "uq_member_problem",
        columnNames = {"member_id", "problem_id"}
    )
})
@SequenceGenerator(
    name = "SOLVED_PROBLEMS_SEQ_GENERATOR",
    sequenceName = "SOLVED_PROBLEMS_SEQ",
    allocationSize = 1
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SolvedProblem {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBMISSIONS_SEQ_GENERATOR")
  @Column(name = "solved_problem_id")
  private Long solvedProblemId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "problem_id", nullable = false)
  private Problem problem;
}

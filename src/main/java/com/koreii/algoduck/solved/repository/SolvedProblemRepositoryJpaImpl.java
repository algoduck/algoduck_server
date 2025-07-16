package com.koreii.algoduck.solved.repository;

import com.koreii.algoduck.member.entity.Member;
import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.solved.entity.SolvedProblem;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SolvedProblemRepositoryJpaImpl implements SolvedProblemRepository {
  private final EntityManager entityManager;

  @Override
  public boolean existsByMemberAndProblem(Member member, Problem problem) {
    String jpql = "SELECT COUNT(sp) FROM SolvedProblem sp WHERE sp.member = :member AND sp.problem = :problem";

    Long count = entityManager.createQuery(jpql, Long.class)
        .setParameter("member", member)
        .setParameter("problem", problem)
        .getSingleResult();

    return count > 0;

  }

  @Override
  @Transactional
  public void addSolvedProblem(Member member, Problem problem) {
    SolvedProblem solvedProblem = SolvedProblem.builder()
        .member(member)
        .problem(problem)
        .build();

    entityManager.persist(solvedProblem);
  }
}

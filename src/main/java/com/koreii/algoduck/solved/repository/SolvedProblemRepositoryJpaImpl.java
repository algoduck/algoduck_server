package com.koreii.algoduck.solved.repository;

import com.koreii.algoduck.member.entity.Member;
import com.koreii.algoduck.problem.dto.response.ProblemSimpleResponseDto;
import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.solved.entity.SolvedProblem;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SolvedProblemRepositoryJpaImpl implements SolvedProblemRepository {
  private final EntityManager entityManager;

  @Override
  public long getSolvedProblemsCount(Long memberId) {
    String jpql = "SELECT COUNT(sp) FROM SolvedProblem sp WHERE sp.member.memberId = :memberId";
    Long count = entityManager.createQuery(jpql, Long.class)
        .setParameter("memberId", memberId)
        .getSingleResult();

    return count;
  }

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
  @Transactional(propagation = REQUIRES_NEW)
  public void addSolvedProblem(Member member, Problem problem) {
    SolvedProblem solvedProblem = SolvedProblem.builder()
        .member(member)
        .problem(problem)
        .build();

    log.info("HELLO");
    entityManager.persist(solvedProblem);
    log.info("WORLD");
  }

  @Override
  public List<ProblemSimpleResponseDto> getSolvedProblems(Long memberId, int pageNumber, int pageSize) {
    int offset = (pageNumber - 1) * pageSize;

    String jpql = "SELECT new com.koreii.algoduck.problem.dto.response.ProblemSimpleResponseDto(sp.problem) "
        + "FROM SolvedProblem sp "
        + "WHERE sp.member.memberId =: memberId "
        + "ORDER BY sp.problem.problemId";

    return entityManager.createQuery(jpql, ProblemSimpleResponseDto.class)
        .setFirstResult(offset)
        .setMaxResults(pageSize)
        .setParameter("memberId", memberId)
        .getResultList();
  }

  @Override
  public boolean hasSolved(Long memberId, Long problemId) {
    String jpql = "SELECT COUNT(sp) FROM SolvedProblem sp WHERE sp.member.id = :memberId AND sp.problem.id = :problemId";
    Long count = entityManager.createQuery(jpql, Long.class)
        .setParameter("memberId", memberId)
        .setParameter("problemId", problemId)
        .getSingleResult();
    return count > 0;
  }
}

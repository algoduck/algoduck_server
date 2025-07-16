package com.koreii.algoduck.solved.repository;

import com.koreii.algoduck.member.entity.Member;
import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.solved.entity.SolvedProblem;
import org.springframework.stereotype.Repository;

@Repository
public interface SolvedProblemRepository {
  boolean existsByMemberAndProblem(Member member, Problem problem);

  void addSolvedProblem(Member member, Problem problem);
}

package com.koreii.algoduck.solved.repository;

import com.koreii.algoduck.member.entity.Member;
import com.koreii.algoduck.problem.dto.response.ProblemSimpleResponseDto;
import com.koreii.algoduck.problem.entity.Problem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolvedProblemRepository {
  long getSolvedProblemsCount(Long memberId);

  boolean existsByMemberAndProblem(Member member, Problem problem);

  void addSolvedProblem(Member member, Problem problem);

  List<ProblemSimpleResponseDto> getSolvedProblems(Long memberId, int pageNumber, int pageSize);
}

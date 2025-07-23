package com.koreii.algoduck.solved.service;

import com.koreii.algoduck.member.entity.Member;
import com.koreii.algoduck.problem.dto.response.ProblemSimpleResponseDto;
import com.koreii.algoduck.problem.entity.Problem;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface SolvedProblemService {
  void addSolvedProblem(Member member, Problem problem);

  long getSolvedProblemsCount(long memberId);

  List<ProblemSimpleResponseDto> getSolvedProblems(Long memberId, int pageNumber, int pageSize);
}

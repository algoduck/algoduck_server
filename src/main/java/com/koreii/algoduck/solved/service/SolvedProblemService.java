package com.koreii.algoduck.solved.service;

import com.koreii.algoduck.member.entity.Member;
import com.koreii.algoduck.problem.entity.Problem;
import org.springframework.stereotype.Service;

@Service
public interface SolvedProblemService {
  void addSolvedProblem(Member member, Problem problem);
}

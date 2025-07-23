package com.koreii.algoduck.solved.service;

import com.koreii.algoduck.member.entity.Member;
import com.koreii.algoduck.problem.dto.response.ProblemSimpleResponseDto;
import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.solved.repository.SolvedProblemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SolvedProblemServiceImpl implements SolvedProblemService {
  private final SolvedProblemRepository solvedProblemRepository;

  @Override
  @Transactional
  public void addSolvedProblem(Member member, Problem problem) {
    try {
      log.info("HERE!!!!");
      solvedProblemRepository.addSolvedProblem(member, problem);
      log.info("THERE!!!!");
      member.increaseSolved();
    } catch (Exception e) {
      log.warn("이미 푼 문제입니다. 중복 INSERT 시도 무시");
    }
  }

  @Override
  public long getSolvedProblemsCount(long memberId) {
    return solvedProblemRepository.getSolvedProblemsCount(memberId);
  }

  @Override
  public List<ProblemSimpleResponseDto> getSolvedProblems(Long memberId, int pageNumber, int pageSize) {
    return solvedProblemRepository.getSolvedProblems(memberId, pageNumber, pageSize);
  }
}

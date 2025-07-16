package com.koreii.algoduck.solved.service;

import com.koreii.algoduck.member.entity.Member;
import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.solved.repository.SolvedProblemRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SolvedProblemServiceImpl implements SolvedProblemService {
  private final SolvedProblemRepository solvedProblemRepository;

  @Override
  @Transactional
  public void addSolvedProblem(Member member, Problem problem) {
    try {
      solvedProblemRepository.addSolvedProblem(member, problem);
      member.increaseSolved();
    } catch (PersistenceException e) {
      log.warn("이미 푼 문제입니다. 중복 INSERT 시도 무시");
    }
  }
}

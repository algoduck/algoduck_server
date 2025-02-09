package com.koreii.algoduck.problem.repository;

import com.koreii.algoduck.problem.dto.request.ProblemAddRequestDto;
import com.koreii.algoduck.problem.dto.response.ProblemResponseDto;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository {
  //  문제를 추가함
  ProblemResponseDto addProblem(ProblemAddRequestDto problemAddRequestDto);
}

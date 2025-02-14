package com.koreii.algoduck.problem.repository;

import com.koreii.algoduck.problem.dto.request.ProblemAddRequestDto;
import com.koreii.algoduck.problem.dto.response.ProblemResponseDto;
import com.koreii.algoduck.problem.dto.response.ProblemSimpleResponseDto;
import com.koreii.algoduck.problem.entity.Problem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemRepository {
  //  문제 이미지, 테스트케이스가 존재할 경우, 이미지와 테스트케이스가 정상적으로 저장된 다음에 문제를 저장함
  ProblemResponseDto addProblem(ProblemAddRequestDto problemAddRequestDto);

  //  PK로 문제를 찾음
  Problem findByProblemId(Long problemId);

  //  모든 문제 수를 가져옴
  long countAllProblems();

  //  모든 문제를 가져옴
  List<ProblemSimpleResponseDto> selectAllProblems(int pageNumber, int pageSize);

  //  문제 번호가 포함된 문제 수를 가져옴
  long countProblemsWithProblemNumber(String problemNumber);

  //  문제 번호가 포함된 문제를 찾음
  List<ProblemSimpleResponseDto> selectProblemsWithProblemNumber(String problemNumber, int pageNumber, int pageSize);

  //  문제 제목을 포함하는 문제 수를 가져옴
  long countProblemsWithTitle(String title);

  //  문제 제목을 포함하는 문제를 찾음
  List<ProblemSimpleResponseDto> selectProblemsWithTitle(String title, int pageNumber, int pageSize);

  //  주어진 난이도에 해당하는 문제 수를 가져옴
  long countProblemsWithDifficulty(List<Integer> difficulties);

  //  주어진 난이도에 해당하는 문제를 찾음
  List<ProblemSimpleResponseDto> selectProblemsWithDifficulty(List<Integer> difficulties, int pageNumber, int pageSize);
}

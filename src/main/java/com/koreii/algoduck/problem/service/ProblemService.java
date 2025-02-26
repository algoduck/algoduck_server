package com.koreii.algoduck.problem.service;

import com.koreii.algoduck.problem.dto.request.ProblemAddRequestDto;
import com.koreii.algoduck.problem.dto.response.ProblemResponseDto;
import com.koreii.algoduck.problem.dto.response.ProblemSimpleResponseDto;
import com.koreii.algoduck.problem.entity.Problem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ProblemService {
  ProblemResponseDto addProblem(ProblemAddRequestDto problemAddRequestDto, List<MultipartFile> inputTestcases, List<MultipartFile> outputTestcases, List<Boolean> isPublics, List<MultipartFile> problemImages, List<Long> algorithms);

  long countAllProblems();

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

  Problem findByProblemId(Long problemId);
}

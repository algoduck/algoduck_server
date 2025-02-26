package com.koreii.algoduck.problem.service;

import com.koreii.algoduck.file.FileStorageService;
import com.koreii.algoduck.problem.dto.request.ProblemAddRequestDto;
import com.koreii.algoduck.problem.dto.response.ProblemResponseDto;
import com.koreii.algoduck.problem.dto.response.ProblemSimpleResponseDto;
import com.koreii.algoduck.problem.entity.Problem;
import com.koreii.algoduck.problem.repository.ProblemRepository;
import com.koreii.algoduck.problemalgorithm.service.ProblemAlgorithmService;
import com.koreii.algoduck.problemimage.service.ProblemImageService;
import com.koreii.algoduck.testcase.repository.TestcaseRepository;
import com.koreii.algoduck.testcase.service.TestcaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProblemServiceImpl implements ProblemService {
  private final ProblemRepository problemRepository;
  private final ProblemImageService problemImageService;
  private final ProblemAlgorithmService problemAlgorithmService;
  private final TestcaseService testcaseService;

  @Override
  @Transactional
  public ProblemResponseDto addProblem(ProblemAddRequestDto problemAddRequestDto, List<MultipartFile> inputTestcases, List<MultipartFile> outputTestcases, List<Boolean> isPublics, List<MultipartFile> problemImages, List<Long> algorithmIds) {
    ProblemResponseDto problemResponseDto = problemRepository.addProblem(problemAddRequestDto);
    Long problemId = problemResponseDto.getProblemId();

    problemImageService.addProblemImages(problemId, problemImages);
    problemAlgorithmService.addProblemAlgorithms(problemId, algorithmIds);
    testcaseService.addTestcases(problemId, inputTestcases, outputTestcases, isPublics);

    return problemResponseDto;
  }

  @Override
  public long countAllProblems() {
    return problemRepository.countAllProblems();
  }

  @Override
  public List<ProblemSimpleResponseDto> selectAllProblems(int pageNumber, int pageSize) {
    return problemRepository.selectAllProblems(pageNumber, pageSize);
  }

  @Override
  public long countProblemsWithProblemNumber(String problemNumber) {
    return problemRepository.countProblemsWithProblemNumber(problemNumber);
  }

  @Override
  public List<ProblemSimpleResponseDto> selectProblemsWithProblemNumber(String problemNumber, int pageNumber, int pageSize) {
    return problemRepository.selectProblemsWithProblemNumber(problemNumber, pageNumber, pageSize);
  }

  @Override
  public long countProblemsWithTitle(String title) {
    return problemRepository.countProblemsWithTitle(title);
  }

  @Override
  public List<ProblemSimpleResponseDto> selectProblemsWithTitle(String title, int pageNumber, int pageSize) {
    return problemRepository.selectProblemsWithTitle(title, pageNumber, pageSize);
  }

  @Override
  public long countProblemsWithDifficulty(List<Integer> difficulties) {
    return problemRepository.countProblemsWithDifficulty(difficulties);
  }

  @Override
  public List<ProblemSimpleResponseDto> selectProblemsWithDifficulty(List<Integer> difficulties, int pageNumber, int pageSize) {
    return problemRepository.selectProblemsWithDifficulty(difficulties, pageNumber, pageSize);
  }

  @Override
  public Problem findByProblemId(Long problemId) {
    return problemRepository.findByProblemId(problemId);
  }
}

package com.koreii.algoduck.problem.controller;

import com.koreii.algoduck.base.controller.BaseApiController;
import com.koreii.algoduck.base.dto.response.ApiResponse;
import com.koreii.algoduck.problem.dto.response.ProblemPagingResponseDto;
import com.koreii.algoduck.problem.dto.response.ProblemResponseDto;
import com.koreii.algoduck.problem.dto.response.ProblemSimpleResponseDto;
import com.koreii.algoduck.problem.service.ProblemService;
import com.koreii.algoduck.solved.service.SolvedProblemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/problems")
@Tag(name = "Problems", description = "문제 관련 API")
@Slf4j
public class ProblemController extends BaseApiController {
  private final ProblemService problemService;
  private final SolvedProblemService solvedProblemService;

  @Operation(summary = "모든 문제 조회", description = "모든 회원 문제를 페이징 처리하여 반환합니다.")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<ProblemPagingResponseDto>> findAllProblems(
      @RequestParam int pageNumber,
      @RequestParam int pageSize) {
    long totalCount = problemService.countAllProblems();

    long start = System.currentTimeMillis();

    List<ProblemSimpleResponseDto> problems = problemService.selectAllProblems(pageNumber, pageSize);

    ProblemPagingResponseDto responseDto = ProblemPagingResponseDto.builder()
        .totalCount(totalCount)
        .problems(problems)
        .build();

    long end = System.currentTimeMillis();
    log.info("문제 전체 조회 소요 시간: {} ms", (end - start));

    return ResponseEntity.ok(ApiResponse.success(responseDto));
  }

  @Operation(summary = "특정 문제 상세 조회", description = "문제 ID로 문제 정보를 조회합니다.")
  @GetMapping("/id/{problemId}")
  public ResponseEntity<ApiResponse<ProblemResponseDto>> findProblemByProblemId(@PathVariable Long problemId) {
    ProblemResponseDto problem = problemService.findDtoByProblemId(problemId);

    if (problem == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.failure("해당 문제를 찾을 수 없습니다."));
    }
    return ResponseEntity.ok(ApiResponse.success(problem));
  }

  @Operation(summary = "특정 회원이 푼 문제 조회", description = "특정 회원이 문제를 페이징 처리하여 반환합니다.")
  @GetMapping(path = "/solved/{memberId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<ProblemPagingResponseDto>> getSolvedProblems(
      @PathVariable long memberId,
      @RequestParam int pageNumber,
      @RequestParam int pageSize) {
    long totalCount = solvedProblemService.getSolvedProblemsCount(memberId);
    List<ProblemSimpleResponseDto> problems = solvedProblemService.getSolvedProblems(memberId, pageNumber, pageSize);

    ProblemPagingResponseDto responseDto = ProblemPagingResponseDto.builder()
        .totalCount(totalCount)
        .problems(problems)
        .build();

    return ResponseEntity.ok(ApiResponse.success(responseDto));
  }

  @Operation(summary = "문제 리스트에서 문제 검색", description = "조건에 맞는 문제를 검색합니다.")
  @GetMapping("/search")
  public ResponseEntity<ApiResponse<ProblemPagingResponseDto>> searchProblems(
      @RequestParam String type,
      @RequestParam String query,
      @RequestParam int pageNumber,
      @RequestParam int pageSize
  ) {
    List<ProblemSimpleResponseDto> problemSimpleResponseDtos = null;
    long totalCount = 0;

    switch (type) {
      case "number":
        totalCount = problemService.countProblemsWithProblemNumber(query);
        problemSimpleResponseDtos = problemService.selectProblemsWithProblemNumber(query, pageNumber, pageSize);
        break;
      case "title":
        totalCount = problemService.countProblemsWithTitle(query);
        problemSimpleResponseDtos = problemService.selectProblemsWithTitle(query, pageNumber, pageSize);
        break;
      default:
        List<Integer> difficulties = new ArrayList<>();
        String[] difficultiesArray = query.split(",");
        for (String difficulty : difficultiesArray) {
          difficulties.add(Integer.parseInt(difficulty));
        }
        totalCount = problemService.countProblemsWithDifficulty(difficulties);
        problemSimpleResponseDtos = problemService.selectProblemsWithDifficulty(difficulties, pageNumber, pageSize);
        break;
    }

    ProblemPagingResponseDto problemPagingResponseDto = ProblemPagingResponseDto.builder()
        .totalCount(totalCount)
        .problems(problemSimpleResponseDtos)
        .build();

    return ResponseEntity.ok(ApiResponse.success(problemPagingResponseDto));
  }
}
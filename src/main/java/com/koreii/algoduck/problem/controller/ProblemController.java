package com.koreii.algoduck.problem.controller;

import com.koreii.algoduck.base.controller.BaseApiController;
import com.koreii.algoduck.base.dto.response.ApiResponse;
import com.koreii.algoduck.member.dto.request.MemberSaveRequestDto;
import com.koreii.algoduck.member.dto.response.MemberPagingResponseDto;
import com.koreii.algoduck.member.dto.response.MemberResponseDto;
import com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto;
import com.koreii.algoduck.member.service.MemberService;
import com.koreii.algoduck.problem.dto.request.ProblemAddRequestDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

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
    List<ProblemSimpleResponseDto> problems = problemService.selectAllProblems(pageNumber, pageSize);

    ProblemPagingResponseDto responseDto = ProblemPagingResponseDto.builder()
        .totalCount(totalCount)
        .problems(problems)
        .build();

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
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<ProblemPagingResponseDto>> getSolvedProblems(
      @RequestParam long memberId,
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
}

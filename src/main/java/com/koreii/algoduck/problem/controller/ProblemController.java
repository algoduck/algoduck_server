package com.koreii.algoduck.problem.controller;

import com.koreii.algoduck.base.controller.BaseApiController;
import com.koreii.algoduck.member.dto.request.MemberSaveRequestDto;
import com.koreii.algoduck.member.dto.response.MemberResponseDto;
import com.koreii.algoduck.problem.dto.request.ProblemAddRequestDto;
import com.koreii.algoduck.problem.dto.response.ProblemResponseDto;
import com.koreii.algoduck.problem.service.ProblemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

  @Operation(
      summary = "문제 추가",
      description = "새로운 문제를 추가합니다."
  )
  @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ProblemResponseDto> addProblem(@RequestPart(value = "problemAddRequestDto") ProblemAddRequestDto problemAddRequestDto,
                                                       @RequestPart(value = "inputTestcases", required = false) List<MultipartFile> inputTestcases,
                                                       @RequestPart(value = "outputTestcases", required = false) List<MultipartFile> outputTestcases,
                                                       @RequestPart(value = "isPublics", required = false) List<Boolean> isPublics,
                                                       @RequestPart(value = "problemImages", required = false) List<MultipartFile> problemImages,
                                                       @RequestPart(value = "algorithmIds") List<Long> algorithmIds) {
    try {
      ProblemResponseDto problemResponseDto = problemService.addProblem(problemAddRequestDto, inputTestcases, outputTestcases, isPublics, problemImages, algorithmIds);
      return ResponseEntity.status(HttpStatus.CREATED).body(problemResponseDto);
    } catch (Exception e) {
      log.error("prbolem add failed", e);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "join failed", e);
    }
  }
}

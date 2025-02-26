package com.koreii.algoduck.algorithm.controller;

import com.koreii.algoduck.algorithm.dto.request.AlgorithmAddRequestDto;
import com.koreii.algoduck.algorithm.dto.response.AlgorithmResponseDto;
import com.koreii.algoduck.algorithm.service.AlgorithmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/algorithms")
@Tag(name = "Algorithms", description = "알고리즘 관련 API")
public class AlgorithmController {
  private final AlgorithmService algorithmService;

  @Operation(
      summary = "알고리즘 추가",
      description = "새로운 알고리즘을 추가합니다."
  )
  @PostMapping
  public ResponseEntity<AlgorithmResponseDto> addAlgorithm(AlgorithmAddRequestDto algorithmAddRequestDto) {
    AlgorithmResponseDto algorithmResponseDto = algorithmService.addAlgorithm(algorithmAddRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(algorithmResponseDto);
  }

  @Operation(summary = "특정 알고리즘 검색", description = "알고리즘 ID로 알고리즘을 조회합니다.")
  @GetMapping("/{algorithmId}")
  public ResponseEntity<AlgorithmResponseDto> findByAlgorithmId(@PathVariable(name = "algorithmId") Long algorithmId) {
    AlgorithmResponseDto algorithmResponseDto = algorithmService.findDtoByAlgorithmId(algorithmId);
    return ResponseEntity.status(HttpStatus.OK).body(algorithmResponseDto);
  }

  @Operation(summary = "이름으로 알고리즘 검색", description = "알고리즘 이름으로 알고리즘을 검색합니다.")
  @GetMapping("/search-by-algorithm-name")
  public ResponseEntity<List<AlgorithmResponseDto>> findAlgorithmsWithAlgorithmName(@RequestParam String algorithmName) {
    List<AlgorithmResponseDto> algorithmResponseDtos = algorithmService.findAlgorithmsWithName(algorithmName);
    return ResponseEntity.ok(algorithmResponseDtos);
  }


}

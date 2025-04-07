package com.koreii.algoduck.base.handler;

import com.koreii.algoduck.base.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  //  필수 파라미터가 누락된 경우
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ApiResponse<Void>> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.failure("필수 파라미터가 누락되었습니다: " + e.getParameterName()));
  }

  // 클라이언트 요청이 잘못된 경우 (예: JSON 형식 오류, 부적절한 입력값)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(
      MethodArgumentNotValidException e) {
    Map<String, String> fieldErrors = new HashMap<>();
    e.getBindingResult().getFieldErrors().forEach(error ->
        fieldErrors.put(error.getField(), error.getDefaultMessage())
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.<Map<String, String>>builder()
        .success(false)
        .message("입력값이 유효하지 않습니다: " + e.getMessage())
        .data(fieldErrors)
        .build());
  }

  // 존재하지 않는 리소스를 요청한 경우 (예: 없는 회원 ID 조회)
  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<ApiResponse<Void>> handleNoSuchElementException(
      NoSuchElementException e) {

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ApiResponse.failure("리소스를 찾을 수 없습니다: " + e.getMessage()));
  }

  // 기본적인 400번대 에러 (기타 Bad Request 예외)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException e) {

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ApiResponse.failure("리소스를 찾을 수 없습니다: " + e.getMessage()));
  }

  // 기타 예외 처리 (500번대 서버 에러)
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ApiResponse.failure("서버 오류가 발생했습니다: " + e.getMessage()));
  }
}

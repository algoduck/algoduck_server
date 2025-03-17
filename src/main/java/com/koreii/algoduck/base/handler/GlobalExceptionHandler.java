package com.koreii.algoduck.base.handler;

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
  public ResponseEntity<Map<String, Object>> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
    errorResponse.put("error", "Bad Request");
    errorResponse.put("message", e.getMessage());

    return ResponseEntity.badRequest().body(errorResponse);
  }

  // 클라이언트 요청이 잘못된 경우 (예: JSON 형식 오류, 부적절한 입력값)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidationExceptions(
      MethodArgumentNotValidException e) {

    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
    errorResponse.put("error", "Validation Error");

    // 필드별 에러 메시지 추가
    Map<String, String> fieldErrors = new HashMap<>();
    e.getBindingResult().getFieldErrors().forEach(error ->
        fieldErrors.put(error.getField(), error.getDefaultMessage())
    );
    errorResponse.put("message", fieldErrors);

    return ResponseEntity.badRequest().body(errorResponse);
  }

  // 존재하지 않는 리소스를 요청한 경우 (예: 없는 회원 ID 조회)
  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<Map<String, Object>> handleNoSuchElementException(
      NoSuchElementException e) {

    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("status", HttpStatus.NOT_FOUND.value());
    errorResponse.put("error", "Not Found");
    errorResponse.put("message", e.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  // 기본적인 400번대 에러 (기타 Bad Request 예외)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException ex) {

    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
    errorResponse.put("error", "Bad Request");
    errorResponse.put("message", "잘못된 요청 형식입니다.");

    return ResponseEntity.badRequest().body(errorResponse);
  }

  // 기타 예외 처리 (500번대 서버 에러)
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {

    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    errorResponse.put("error", "Internal Server Error");
    errorResponse.put("message", ex.getMessage());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }
}

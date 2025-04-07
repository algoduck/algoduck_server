package com.koreii.algoduck.base.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResponse<T> {
  private boolean success;
  private String message;
  private T data;

  public static <T> ApiResponse<T> success(T data) {
    return ApiResponse.<T>builder()
        .success(true)
        .message("요청 성공")
        .data(data)
        .build();
  }

  public static <T> ApiResponse<T> failure(String errorMessage) {
    return ApiResponse.<T>builder()
        .success(false)
        .message(errorMessage)
        .data(null)
        .build();
  }
}

package com.koreii.algoduck.submission.enums;

public enum Status {
  COMPILE_ERROR("COMPILE_ERROR"),
  JUDGING("JUDGING"),
  WRONG_ANSWER("WRONG_ANSWER"),
  TIME_LIMIT_EXCEEDED("TIME_LIMIT_EXCEEDED"),
  MEMORY_LIMIT_EXCEEDED("MEMORY_LIMIT_EXCEEDED"),
  RUNTIME_ERROR("RUNTIME_ERROR"),
  ACCEPTED("ACCEPTED");

  private final String status;

  Status(String status) {
    this.status = status;
  }
}

package com.koreii.algoduck.submission.enums;

public enum Status {
  CE("COMPILE_ERROR"),
  JUDGING("JUDGING"),
  WA("WRONG_ANSWER"),
  TLE("TIME_LIMIT_EXCEEDED"),
  MLE("MEMORY_LIMIT_EXCEEDED"),
  RE("RUNTIME_ERROR"),
  AC("ACCEPTED");

  Status(String status) {
  }
}

package com.koreii.algoduck.exceptions;

public class NicknamePolicyViolationException extends RuntimeException {
  public NicknamePolicyViolationException() {
  }

  public NicknamePolicyViolationException(String message) {
    super(message);
  }

  public NicknamePolicyViolationException(String message, Throwable cause) {
    super(message, cause);
  }

  public NicknamePolicyViolationException(Throwable cause) {
    super(cause);
  }
}

package com.koreii.algoduck.exceptions;

public class LoginIdPolicyViolationException extends RuntimeException {
  public LoginIdPolicyViolationException() {
  }

  public LoginIdPolicyViolationException(String message) {
    super(message);
  }

  public LoginIdPolicyViolationException(String message, Throwable cause) {
    super(message, cause);
  }

  public LoginIdPolicyViolationException(Throwable cause) {
    super(cause);
  }
}

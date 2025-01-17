package com.koreii.algoduck.exceptions;

public class PasswordPolicyViolationException extends RuntimeException {
  public PasswordPolicyViolationException() {
  }

  public PasswordPolicyViolationException(String message) {
    super(message);
  }

  public PasswordPolicyViolationException(String message, Throwable cause) {
    super(message, cause);
  }

  public PasswordPolicyViolationException(Throwable cause) {
    super(cause);
  }
}

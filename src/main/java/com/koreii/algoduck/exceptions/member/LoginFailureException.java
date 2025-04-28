package com.koreii.algoduck.exceptions.member;

public class LoginFailureException extends RuntimeException {
  public LoginFailureException() {
  }

  public LoginFailureException(String message) {
    super(message);
  }

  public LoginFailureException(String message, Throwable cause) {
    super(message, cause);
  }

  public LoginFailureException(Throwable cause) {
    super(cause);
  }
}

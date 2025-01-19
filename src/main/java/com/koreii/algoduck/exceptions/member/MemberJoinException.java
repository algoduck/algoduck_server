package com.koreii.algoduck.exceptions.member;

public class MemberJoinException extends RuntimeException {
  public MemberJoinException() {
  }

  public MemberJoinException(String message) {
    super(message);
  }

  public MemberJoinException(String message, Throwable cause) {
    super(message, cause);
  }

  public MemberJoinException(Throwable cause) {
    super(cause);
  }
}

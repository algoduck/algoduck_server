package com.koreii.algoduck.exceptions.member;

public class MemberUpdateException extends RuntimeException {
  public MemberUpdateException() {
  }

  public MemberUpdateException(String message) {
    super(message);
  }

  public MemberUpdateException(String message, Throwable cause) {
    super(message, cause);
  }

  public MemberUpdateException(Throwable cause) {
    super(cause);
  }
}

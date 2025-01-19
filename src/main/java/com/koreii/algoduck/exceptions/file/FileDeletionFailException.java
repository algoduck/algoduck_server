package com.koreii.algoduck.exceptions.file;

public class FileDeletionFailException extends RuntimeException {
  public FileDeletionFailException() {
  }

  public FileDeletionFailException(String message) {
    super(message);
  }

  public FileDeletionFailException(String message, Throwable cause) {
    super(message, cause);
  }

  public FileDeletionFailException(Throwable cause) {
    super(cause);
  }
}

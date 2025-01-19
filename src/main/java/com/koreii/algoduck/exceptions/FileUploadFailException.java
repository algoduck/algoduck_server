package com.koreii.algoduck.exceptions;

public class FileUploadFailException extends RuntimeException {
  public FileUploadFailException() {
  }

  public FileUploadFailException(String message) {
    super(message);
  }

  public FileUploadFailException(String message, Throwable cause) {
    super(message, cause);
  }

  public FileUploadFailException(Throwable cause) {
    super(cause);
  }
}

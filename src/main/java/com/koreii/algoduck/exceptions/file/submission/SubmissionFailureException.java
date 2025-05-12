package com.koreii.algoduck.exceptions.file.submission;

public class SubmissionFailureException extends RuntimeException {
  public SubmissionFailureException() {
  }

  public SubmissionFailureException(String message) {
    super(message);
  }

  public SubmissionFailureException(String message, Throwable cause) {
    super(message, cause);
  }

  public SubmissionFailureException(Throwable cause) {
    super(cause);
  }
}

package com.koreii.algoduck.exceptions.file.s3;

import com.koreii.algoduck.exceptions.file.FileDeletionFailException;

public class AmazonS3FileDeletionFailException extends FileDeletionFailException {
  public AmazonS3FileDeletionFailException() {
  }

  public AmazonS3FileDeletionFailException(String message) {
    super(message);
  }

  public AmazonS3FileDeletionFailException(String message, Throwable cause) {
    super(message, cause);
  }

  public AmazonS3FileDeletionFailException(Throwable cause) {
    super(cause);
  }
}

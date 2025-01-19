package com.koreii.algoduck.exceptions.file.s3;

import com.koreii.algoduck.exceptions.file.FileUploadFailException;

public class AmazonS3FileUploadFailException extends FileUploadFailException {
  public AmazonS3FileUploadFailException() {
  }

  public AmazonS3FileUploadFailException(String message) {
    super(message);
  }

  public AmazonS3FileUploadFailException(String message, Throwable cause) {
    super(message, cause);
  }

  public AmazonS3FileUploadFailException(Throwable cause) {
    super(cause);
  }
}

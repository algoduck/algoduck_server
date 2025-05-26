package com.koreii.algoduck.exceptions.file.s3;

import com.koreii.algoduck.exceptions.file.FileDownloadFailException;

public class AmazonS3FileDownloadFailException extends FileDownloadFailException {
  public AmazonS3FileDownloadFailException() {
  }

  public AmazonS3FileDownloadFailException(String message) {
    super(message);
  }

  public AmazonS3FileDownloadFailException(String message, Throwable cause) {
    super(message, cause);
  }

  public AmazonS3FileDownloadFailException(Throwable cause) {
    super(cause);
  }
}

package com.koreii.algoduck.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.koreii.algoduck.exceptions.file.FileUploadFailException;
import com.koreii.algoduck.exceptions.file.s3.AmazonS3FileUploadFailException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileStorageServiceAmazonS3Impl implements FileStorageService {
  private final AmazonS3 amazonS3;

  @Override
  public String uploadFile(String repositoryName, String folderPath, MultipartFile file) {
    log.info("uploadFile");

    if (file == null || file.isEmpty()) {
      return null;
    }

    try {
      return uploadS3File(repositoryName, folderPath, file);
    } catch (FileUploadFailException e) {
      log.warn("Failed to upload file to Folder: {}", folderPath, e);
      log.warn("기존 이미지 사용");
      return null;
    }
  }

  @Override
  public void deleteFile(String repositoryName, String fileUrl) {
    if (fileUrl != null) {
      try {
        log.info("bucketName = {}", repositoryName);
        log.info("fileUrl = {}", fileUrl);
        deleteS3File(repositoryName, fileUrl);
      } catch (Exception e) {
        log.error("Failed to delete file from {}", fileUrl, e);
      }
    }
  }

  private String uploadS3File(String bucketName, String filePath, MultipartFile file) {
    String fileName = generateUniqueFileName(file.getOriginalFilename());
    String fullPath = filePath + "/" + fileName;

    try {
      ObjectMetadata objectMetadata = new ObjectMetadata();
      objectMetadata.setContentLength(file.getSize());
      objectMetadata.setContentType(file.getContentType());

      log.info("fullPath(key) = {}", fullPath);
      PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fullPath, file.getInputStream(), objectMetadata);
      amazonS3.putObject(putObjectRequest);

      return amazonS3.getUrl(bucketName, fullPath).toString();
    } catch (IOException e) {
      throw new AmazonS3FileUploadFailException("Failed to upload file to S3", e);
    }
  }

  private void deleteS3File(String bucketName, String s3FilePath) {
    log.info("bucketName = {}", bucketName);
    log.info("s3FilePath = {}", s3FilePath);

    String key = getKey(bucketName, s3FilePath);
    log.info("key = {}", key);
    amazonS3.deleteObject(new DeleteObjectRequest(bucketName, key));
  }

  private String generateUniqueFileName(String originalFileName) {
    int extensionIndex = originalFileName.lastIndexOf('.');
    String fileName = originalFileName.substring(0, extensionIndex);
    String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
    return fileName + UUID.randomUUID() + extension;
  }

  private String getKey(String bucketName, String s3FilePath) {
    String prefix = "https://" + bucketName + ".s3.ap-northeast-2.amazonaws.com/";
    return s3FilePath.substring(prefix.length());
  }
}

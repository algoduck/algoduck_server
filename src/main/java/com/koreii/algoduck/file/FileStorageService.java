package com.koreii.algoduck.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
  String uploadFile(String bucketName, String filePath, MultipartFile file);

  void deleteFile(String bucketName, String filePath);
}

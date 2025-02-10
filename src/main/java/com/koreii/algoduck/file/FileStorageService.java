package com.koreii.algoduck.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
  String uploadFile(String repositoryName, String folderPath, MultipartFile file);

  void deleteFile(String repositoryName, String fileUrl);
}

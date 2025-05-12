package com.koreii.algoduck.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

public interface FileStorageService {
  CompletableFuture<String> uploadFile(String repositoryName, String folderPath, MultipartFile file);

  CompletableFuture<Void> deleteFile(String repositoryName, String fileUrl);
}

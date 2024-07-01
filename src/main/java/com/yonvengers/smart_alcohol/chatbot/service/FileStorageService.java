package com.yonvengers.smart_alcohol.chatbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file, String userId) throws IOException {
        // 파일명 생성
        String filename = userId + "_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
        // 파일 저장
        Files.write(Paths.get(uploadDir + java.io.File.separator + filename), file.getBytes());
        return filename;
    }
}

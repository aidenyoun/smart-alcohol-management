package com.yonvengers.smart_alcohol.chatbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

public String storeFile(MultipartFile file, String userId) throws IOException {
    // 현재 날짜 및 시간 형식 지정
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    String formattedDateTime = LocalDateTime.now().format(formatter);

    // 원본 파일 이름 가져오기
    String originalFilename = file.getOriginalFilename();
    if (originalFilename == null || originalFilename.trim().isEmpty()) {
        originalFilename = "default_filename.mp4"; // 기본 파일 이름 설정
    }

    // 파일명 생성
    String filename = userId + "_" + formattedDateTime + "_" + originalFilename;

    // 파일 저장 경로
    Path filePath = Paths.get(uploadDir + java.io.File.separator + filename);
    Files.write(filePath, file.getBytes());

    return filePath.toString();
}
}

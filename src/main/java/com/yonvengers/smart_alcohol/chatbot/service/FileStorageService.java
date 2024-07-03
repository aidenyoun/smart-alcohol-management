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

        // 파일명 생성
        String filename = userId + "_" + formattedDateTime + "_" + file.getOriginalFilename();

        // 파일 저장 경로
        Path filePath = Paths.get(uploadDir + java.io.File.separator + filename);
        Files.write(filePath, file.getBytes());

        return filePath.toString();
    }
}

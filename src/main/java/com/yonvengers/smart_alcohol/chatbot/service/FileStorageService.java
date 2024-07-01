package com.yonvengers.smart_alcohol.chatbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private static final Set<String> allowedExtensions = new HashSet<>();

    static {
        allowedExtensions.add(".m4a");
        allowedExtensions.add(".mp3");
        allowedExtensions.add(".wav");
        allowedExtensions.add(".aac");
        allowedExtensions.add(".3gp");
    }

    public void saveFile(String userId, MultipartFile file) throws IOException {
        Path path = Paths.get(uploadDir).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null || !hasAllowedExtension(originalFileName)) {
            throw new IOException("Invalid file extension");
        }

        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String uniqueFileName = userId + "_" + timestamp + fileExtension;

        Path filePath = path.resolve(uniqueFileName);
        Files.copy(file.getInputStream(), filePath);
    }

    private boolean hasAllowedExtension(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        return allowedExtensions.contains(fileExtension.toLowerCase());
    }
}

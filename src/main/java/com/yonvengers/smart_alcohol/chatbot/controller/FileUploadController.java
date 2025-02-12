package com.yonvengers.smart_alcohol.chatbot.controller;

import com.yonvengers.smart_alcohol.chatbot.service.FileStorageService;
import com.yonvengers.smart_alcohol.chatbot.service.SpeechToTextService;
import com.yonvengers.smart_alcohol.chatbot.service.GptService;
import com.yonvengers.smart_alcohol.chatbot.service.TextToSpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/chatbot")
public class FileUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private SpeechToTextService speechToTextService;

    @Autowired
    private GptService gptService;

//    @Autowired
//    private TextToSpeechService textToSpeechService;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
         try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            String filePath = fileStorageService.storeFile(file, username);

            return ResponseEntity.ok("File uploaded successfully: " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}

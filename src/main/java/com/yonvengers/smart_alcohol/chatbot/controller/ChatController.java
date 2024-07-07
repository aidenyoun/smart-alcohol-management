package com.yonvengers.smart_alcohol.chatbot.controller;

import com.yonvengers.smart_alcohol.chatbot.service.FileStorageService;
import com.yonvengers.smart_alcohol.chatbot.service.GptService;
import com.yonvengers.smart_alcohol.chatbot.service.SpeechToTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/chatbot")
public class ChatController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private SpeechToTextService speechToTextService;

    @Autowired
    private GptService gptService;

    @PostMapping("/voice-upload")
    public ResponseEntity<Map<String, String>> handleVoiceUpload(@RequestParam("file") MultipartFile file) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            String filePath = fileStorageService.storeFile(file, username);

            String transcript = speechToTextService.convertSpeechToText(filePath);

            Map<String, String> gptResult = gptService.getGptResponse(transcript);

            Map<String, String> response = new HashMap<>();
            response.put("inputText", gptResult.get("inputText"));
            response.put("gptResponse", gptResult.get("gptResponse"));

            return ResponseEntity.ok(response);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}

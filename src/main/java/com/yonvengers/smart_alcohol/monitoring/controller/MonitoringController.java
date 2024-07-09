package com.yonvengers.smart_alcohol.monitoring.controller;
import com.yonvengers.smart_alcohol.monitoring.service.ReceiptAnalysisService;
import net.sourceforge.tess4j.TesseractException;

import com.yonvengers.smart_alcohol.monitoring.service.ReceiptAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/monitoring")
public class MonitoringController {

    @Autowired
    private ReceiptAnalysisService receiptAnalysisService;

    @PostMapping("/upload-receipt")
    public ResponseEntity<Map<String, String>> handleReceiptUpload(@RequestParam("file") MultipartFile file) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            // OCR을 사용하여 텍스트 추출 및 분석
            String analysisResult = receiptAnalysisService.analyzeReceipt(file);

            Map<String, String> response = new HashMap<>();
            response.put("analysis", analysisResult);

            return ResponseEntity.ok(response);

        } catch (IOException | TesseractException e) {
            e.printStackTrace();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "File upload failed");
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}
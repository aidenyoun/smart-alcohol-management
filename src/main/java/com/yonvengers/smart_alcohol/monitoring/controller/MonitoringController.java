package com.yonvengers.smart_alcohol.monitoring.controller;

import com.yonvengers.smart_alcohol.monitoring.service.ReceiptAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Map<String, String>> uploadReceipt(@RequestParam("file") MultipartFile file) {
        try {
            Map<String, String> analysisResult = receiptAnalysisService.analyzeReceipt(file);
            return ResponseEntity.ok(analysisResult);
        } catch (IOException e) {
            e.printStackTrace();
            Map<String, String> response = new HashMap<>();
            response.put("error", "Error processing the image for text extraction.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

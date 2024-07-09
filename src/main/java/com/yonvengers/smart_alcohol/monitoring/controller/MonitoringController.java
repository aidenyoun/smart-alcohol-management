package com.yonvengers.smart_alcohol.monitoring.controller;

import com.yonvengers.smart_alcohol.monitoring.service.ReceiptAnalysisService;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/monitoring")
public class MonitoringController {

    @Autowired
    private ReceiptAnalysisService receiptAnalysisService;

    @PostMapping("/upload-receipt")
    public ResponseEntity<Map<String, String>> uploadReceipt(@RequestParam("file") MultipartFile file) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            Map<String, String> analysisResult = receiptAnalysisService.analyzeReceipt(file);

            return ResponseEntity.ok(analysisResult);
        } catch (IOException | TesseractException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}

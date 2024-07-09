package com.yonvengers.smart_alcohol.monitoring.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReceiptAnalysisService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${tesseract.datapath}")
    private String tessDataPath;

    @Value("${tesseract.librarypath}")
    private String tessLibraryPath;

    public Map<String, String> analyzeReceipt(MultipartFile file) throws IOException, TesseractException {
        // Convert MultipartFile to BufferedImage
        InputStream inputStream = file.getInputStream();
        BufferedImage bufferedImage = ImageIO.read(inputStream);

        // Set environment variables for Tesseract
        System.setProperty("jna.library.path", tessLibraryPath);
        System.setProperty("TESSDATA_PREFIX", tessDataPath);

        // Extract text from image using Tesseract OCR
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(tessDataPath); // Set the path to tessdata directory
        tesseract.setLanguage("kor"); // Set language to Korean

        String extractedText = tesseract.doOCR(bufferedImage);

        // Trim the text to reduce token count
        String trimmedText = extractedText.length() > 1000 ? extractedText.substring(0, 1000) : extractedText;

        String url = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        Map<String, Object> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", "Please provide a response Analyze the following purchase list from a receipt and in Korean.");

        Map<String, Object> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", trimmedText);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-4o");
        body.put("messages", List.of(systemMessage, userMessage));
        body.put("max_tokens", 400);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map<String, Object>> response = restTemplate.postForEntity(url, entity, (Class<Map<String, Object>>) (Class<?>) Map.class);

        // Convert all values to String
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, Object> entry : response.getBody().entrySet()) {
            result.put(entry.getKey(), entry.getValue() != null ? entry.getValue().toString() : null);
        }

        return result;
    }
}

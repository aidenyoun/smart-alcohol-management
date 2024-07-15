package com.yonvengers.smart_alcohol.calendar.controller;

import com.yonvengers.smart_alcohol.calendar.model.DrinkRecord;
import com.yonvengers.smart_alcohol.calendar.model.DrinkRecordRequest;
import com.yonvengers.smart_alcohol.calendar.service.DrinkRecordService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.sql.Date;

@RestController
@RequestMapping("/api/drink-records")
public class DrinkRecordController {

    @Autowired
    private DrinkRecordService drinkRecordService;

    @Value("${jwt.secret}")
    private String secretKey;

    @PostMapping("/record")
    public ResponseEntity<DrinkRecord> recordDrink(@RequestBody DrinkRecordRequest request, HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("Authorization").substring(7); // Assuming "Bearer <token>"
        String username = getUsernameFromToken(token);

        DrinkRecord savedRecord = drinkRecordService.recordDrink(username, request);
        return ResponseEntity.ok(savedRecord);
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<DrinkRecord>> getDrinkRecordsByDate(HttpServletRequest httpRequest, @RequestParam("date") Date date) {
        String token = httpRequest.getHeader("Authorization").substring(7); // Assuming "Bearer <token>"
        String username = getUsernameFromToken(token);

        List<DrinkRecord> records = drinkRecordService.getDrinkRecordsByDate(username, date);
        return ResponseEntity.ok(records);
    }

    private String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody();

        return claims.getSubject(); // 토큰의 subject에서 사용자 이름 추출
    }
}

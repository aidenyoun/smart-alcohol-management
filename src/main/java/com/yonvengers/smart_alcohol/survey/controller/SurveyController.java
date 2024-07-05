package com.yonvengers.smart_alcohol.survey.controller;

import com.yonvengers.smart_alcohol.login.model.User;
import com.yonvengers.smart_alcohol.login.repository.UserRepository;
import com.yonvengers.smart_alcohol.survey.model.SurveyResponse;
import com.yonvengers.smart_alcohol.survey.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/submit")
    public ResponseEntity<String> submitSurvey(@RequestBody SurveyResponse surveyResponse) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return ResponseEntity.badRequest().body("User not found.");
        }

        Long userId = user.getId();
        Date responseDate = Date.valueOf(LocalDate.now());

        if (surveyService.hasAlreadySubmitted(userId, responseDate)) {
            return ResponseEntity.badRequest().body("User has already submitted the survey for today.");
        }

        surveyResponse.setUserId(userId);
        surveyResponse.setResponseDate(responseDate);

        int totalScore = calculateTotalScore(surveyResponse);
        surveyResponse.setTotalScore(totalScore);

        surveyService.saveSurveyResponse(surveyResponse);
        return ResponseEntity.ok("Survey submitted successfully.");
    }

    @GetMapping("/inquiry")
    public ResponseEntity<?> getSurveyResponses() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return ResponseEntity.badRequest().body("User not found.");
        }

        Long userId = user.getId();
        List<SurveyResponse> responses = surveyService.getSurveyResponsesByUserId(userId);

        if (responses.isEmpty()) {
            return ResponseEntity.ok("No survey responses found for the user.");
        }

        return ResponseEntity.ok(responses);
    }


    private int calculateTotalScore(SurveyResponse surveyResponse) {
        return surveyResponse.getQuestion1() +
               surveyResponse.getQuestion2() +
               surveyResponse.getQuestion3() +
               surveyResponse.getQuestion4() +
               surveyResponse.getQuestion5() +
               surveyResponse.getQuestion6() +
               surveyResponse.getQuestion7() +
               surveyResponse.getQuestion8() +
               surveyResponse.getQuestion9() +
               surveyResponse.getQuestion10();
    }
}

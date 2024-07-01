package com.yonvengers.smart_alcohol.survey.service;

import com.yonvengers.smart_alcohol.survey.model.SurveyResponse;
import com.yonvengers.smart_alcohol.survey.repository.SurveyResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class SurveyService {

    @Autowired
    private SurveyResponseRepository surveyResponseRepository;

    public boolean hasAlreadySubmitted(Long userId, Date responseDate) {
        Optional<SurveyResponse> existingResponse = surveyResponseRepository.findByUserIdAndResponseDate(userId, responseDate);
        return existingResponse.isPresent();
    }

    public SurveyResponse saveSurveyResponse(SurveyResponse surveyResponse) {
        return surveyResponseRepository.save(surveyResponse);
    }
}

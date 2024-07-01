package com.yonvengers.smart_alcohol.survey.repository;

import com.yonvengers.smart_alcohol.survey.model.SurveyResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.Optional;

public interface SurveyResponseRepository extends JpaRepository<SurveyResponse, Long> {
    Optional<SurveyResponse> findByUserIdAndResponseDate(Long userId, Date responseDate);
}

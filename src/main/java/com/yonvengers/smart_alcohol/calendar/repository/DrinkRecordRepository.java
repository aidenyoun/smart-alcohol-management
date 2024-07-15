package com.yonvengers.smart_alcohol.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yonvengers.smart_alcohol.calendar.model.DrinkRecord;

import java.sql.Date;
import java.util.List;

public interface DrinkRecordRepository extends JpaRepository<DrinkRecord, Long> {
    List<DrinkRecord> findByUsernameAndDrinkDate(String username, Date drinkDate);
}
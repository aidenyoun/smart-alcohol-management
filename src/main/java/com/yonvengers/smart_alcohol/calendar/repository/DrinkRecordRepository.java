package com.yonvengers.smart_alcohol.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yonvengers.smart_alcohol.calendar.model.DrinkRecord;

public interface DrinkRecordRepository extends JpaRepository<DrinkRecord, Long> {
}

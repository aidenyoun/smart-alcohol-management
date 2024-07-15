package com.yonvengers.smart_alcohol.calendar.service;

import com.yonvengers.smart_alcohol.calendar.model.DrinkRecord;
import com.yonvengers.smart_alcohol.calendar.model.DrinkRecordRequest;
import com.yonvengers.smart_alcohol.calendar.repository.DrinkRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.sql.Date;

@Service
public class DrinkRecordService {

    @Autowired
    private DrinkRecordRepository drinkRecordRepository;

    @Transactional // Add this annotation to manage the transaction
    public DrinkRecord recordDrink(String username, DrinkRecordRequest request) {
        // Delete existing record if it exists
        drinkRecordRepository.deleteByUsernameAndDrinkDate(username, request.getDrinkDate());

        DrinkRecord drinkRecord = new DrinkRecord();
        drinkRecord.setUsername(username);
        drinkRecord.setDrinkDate(request.getDrinkDate());
        drinkRecord.setSoju(request.getSoju());
        drinkRecord.setBeer(request.getBeer());
        drinkRecord.setMakgeolli(request.getMakgeolli());
        drinkRecord.setWine(request.getWine());
        drinkRecord.setWhiskey(request.getWhiskey());
        drinkRecord.setCocktail(request.getCocktail());

        return drinkRecordRepository.save(drinkRecord);
    }

    public List<DrinkRecord> getDrinkRecordsByDate(String username, Date date) {
        return drinkRecordRepository.findByUsernameAndDrinkDate(username, date);
    }
}

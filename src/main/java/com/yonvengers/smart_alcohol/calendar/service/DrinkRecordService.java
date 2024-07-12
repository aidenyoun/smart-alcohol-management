package com.yonvengers.smart_alcohol.calendar.service;

import com.yonvengers.smart_alcohol.calendar.model.DrinkRecord;
import com.yonvengers.smart_alcohol.calendar.model.DrinkRecordRequest;
import com.yonvengers.smart_alcohol.calendar.repository.DrinkRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrinkRecordService {

    @Autowired
    private DrinkRecordRepository drinkRecordRepository;

    public DrinkRecord recordDrink(String username, DrinkRecordRequest request) {
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
}

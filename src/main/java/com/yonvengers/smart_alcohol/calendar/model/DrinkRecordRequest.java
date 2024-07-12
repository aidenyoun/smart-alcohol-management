package com.yonvengers.smart_alcohol.calendar.model;

import java.sql.Date;

public class DrinkRecordRequest {
    private Date drinkDate;
    private double soju;
    private double beer;
    private double makgeolli;
    private double wine;
    private double whiskey;
    private double cocktail;

    // Getters and setters

    public Date getDrinkDate() {
        return drinkDate;
    }

    public void setDrinkDate(Date drinkDate) {
        this.drinkDate = drinkDate;
    }

    public double getSoju() {
        return soju;
    }

    public void setSoju(double soju) {
        this.soju = soju;
    }

    public double getBeer() {
        return beer;
    }

    public void setBeer(double beer) {
        this.beer = beer;
    }

    public double getMakgeolli() {
        return makgeolli;
    }

    public void setMakgeolli(double makgeolli) {
        this.makgeolli = makgeolli;
    }

    public double getWine() {
        return wine;
    }

    public void setWine(double wine) {
        this.wine = wine;
    }

    public double getWhiskey() {
        return whiskey;
    }

    public void setWhiskey(double whiskey) {
        this.whiskey = whiskey;
    }

    public double getCocktail() {
        return cocktail;
    }

    public void setCocktail(double cocktail) {
        this.cocktail = cocktail;
    }
}

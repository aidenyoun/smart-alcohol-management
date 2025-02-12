package com.yonvengers.smart_alcohol.register.model;

public class UserProfileRequest {
    private Integer height;
    private String gender;
    private Integer weight;
    private Integer birthYear;
    private Integer monthlyDrinkGoal;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getMonthlyDrinkGoal() {
        return monthlyDrinkGoal;
    }

    public void setMonthlyDrinkGoal(Integer monthlyDrinkGoal) {
        this.monthlyDrinkGoal = monthlyDrinkGoal;
    }
}

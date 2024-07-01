package com.yonvengers.smart_alcohol.register.model;

public class UserProfileRequest {

    private String username;
    private Integer height;
    private String gender;
    private Integer weight;
    private Integer birthYear;
    private Float monthlyDrinkGoal;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public Float getMonthlyDrinkGoal() {
        return monthlyDrinkGoal;
    }

    public void setMonthlyDrinkGoal(Float monthlyDrinkGoal) {
        this.monthlyDrinkGoal = monthlyDrinkGoal;
    }
}

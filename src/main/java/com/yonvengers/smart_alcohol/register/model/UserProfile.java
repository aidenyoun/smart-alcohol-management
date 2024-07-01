package com.yonvengers.smart_alcohol.register.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @Column(name = "user_id")
    private Long userId;

    private Integer height;

    @Column(length = 10)
    private String gender;

    private Integer weight;

    private Integer birthYear;

    private Double monthlyDrinkGoal;

    // Getters and Setters
    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Double getMonthlyDrinkGoal() {
        return monthlyDrinkGoal;
    }

    public void setMonthlyDrinkGoal(Double monthlyDrinkGoal) {
        this.monthlyDrinkGoal = monthlyDrinkGoal;
    }
}

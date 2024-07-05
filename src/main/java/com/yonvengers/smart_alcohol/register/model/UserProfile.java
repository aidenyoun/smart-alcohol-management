package com.yonvengers.smart_alcohol.register.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "gender")
    private String gender;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "monthly_drink_goal")
    private Integer monthlyDrinkGoal;

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

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getMonthlyDrinkGoal() {
        return monthlyDrinkGoal;
    }

    public void setMonthlyDrinkGoal(Integer monthlyDrinkGoal) {
        this.monthlyDrinkGoal = monthlyDrinkGoal;
    }
}

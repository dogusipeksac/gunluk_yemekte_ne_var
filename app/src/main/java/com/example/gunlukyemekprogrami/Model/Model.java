package com.example.gunlukyemekprogrami.Model;

import java.util.Date;

public class Model {

    private String foodCategory;
    private String title;
    private String calorie;
    private String itemStartDate;


    public Model(String foodCategory, String title, String calorie, String itemStartDate) {
        this.foodCategory = foodCategory;
        this.title = title;
        this.calorie = calorie;
        this.itemStartDate = itemStartDate;
    }

    public Model() {
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getItemStartDate() {
        return itemStartDate;
    }

    public void setItemStartDate(String  itemStartDate) {
        this.itemStartDate = itemStartDate;
    }
}

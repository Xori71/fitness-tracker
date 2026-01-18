package com.hua.app.data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.hua.app.basicelements.Activity;
import com.hua.app.data.calories.Formula;

/**
 * DataHolder is the main way we pass information around. Instead of relying on bloated constructors that would need constant "refreshing"
 * (by recreating the respective object again and again and re-passing the required variables), we send this singular object to whoever 
 * needs it, facilitating live updating and retrieval of the data inside thanks to each object holding the same reference to this one.
 */

public class DataHolder {
    private int age;
    private double weight;
    private String sex;
    private Set<File> fileHistory;
    private ArrayList<Activity> activityList;
    private Formula formula;
    private double calorieTarget;
    private Map<String, Double> dailyBurnedCalories;
    
    public DataHolder() {
        age = 0;
        weight = 0;
        sex = null;
        fileHistory = new HashSet<>();
        activityList = new ArrayList<>();
        dailyBurnedCalories = new HashMap<>();
        formula = null;
        calorieTarget = -1;
    }
    
    public void setFormula(Formula formula) {
        this.formula = formula;
    }
    
    public Formula getFormula() {
        return formula;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Set<File> getFileHistory() {
        return fileHistory;
    }

    public void setFileHistory(Set<File> fileList) {
        this.fileHistory = fileList;
    }
    
    public void setCalorieTarget(double calorieTarget) {
        this.calorieTarget = calorieTarget;
    }
    
    public double getCalorieTarget() {
        return calorieTarget;
    }
    
    public void recordDateAndCalories(String date, double calories) {
        dailyBurnedCalories.merge(date, calories, Double::sum);
    }
    
    public Map<String, Double> getDailyBurnedCalories() {
        return dailyBurnedCalories;
    }
    
    public ArrayList<Activity> getActivityList() {
        return activityList;
    }

    public void clearAllData() {
        age = 0;
        weight = 0.0;
        sex = null;
        activityList.clear();
        fileHistory.clear();
    }
}
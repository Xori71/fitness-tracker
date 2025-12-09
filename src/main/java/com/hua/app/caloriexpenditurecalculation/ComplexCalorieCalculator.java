package com.hua.app.caloriexpenditurecalculation;

import com.hua.app.activityelements.Activity;

public class ComplexCalorieCalculator implements CalorieCalculationFormula{
    private double weight;
    private int age;
    private String sex;
    
    public ComplexCalorieCalculator(int age, String sex, double weight) {
        this.age = age;
        this.sex = sex;
        this.weight = weight;
    }
    
    @Override
    public double calculate(Activity activity) {
        if (sex.equals("m")) {
            return -55.0969 + (0.6309 * activity.getAverageHeartRate()) + (0.1966 * weight) + (0.2017 * age) * activity.getDurationInMinutes() / 4.184;
        } else {
            return -20.4022 + (0.4472 * activity.getAverageHeartRate()) + (0.1263 * weight) + (0.074 * age) * activity.getDurationInMinutes() / 4.184;
        }
    }
}
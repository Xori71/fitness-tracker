package com.hua.app.utilities.calories;

import com.hua.app.activityelements.Activity;

public class SimpleCalorieCalc implements CalorieCalcFormula {
    private double weight;
    
    public SimpleCalorieCalc(double weight) {
        this.weight = weight;
    }
    
    @Override
    public double calculate(Activity activity) {
        try {
            return 0.0175 * MetFactory.createMetValue(activity.getAverageSpeed(), activity.getActivityType()) * weight * (activity.getDuration() / 60.0);
        } catch (IllegalArgumentException e) {
            return -1;
        }
    }
}
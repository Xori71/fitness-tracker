package com.hua.app.utilities.calories;

import com.hua.app.activityelements.Activity;
import com.hua.app.activityelements.CustomActivity;

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
    
    @Override
    public double calculate(CustomActivity customActivity) {
        try {
            return 0.0175 * MetFactory.createMetValue(Double.parseDouble(customActivity.getAverageSpeed()), customActivity.getType()) * weight * (customActivity.getDurationInSeconds() / 60.0);
        } catch (IllegalArgumentException e) {
            return -1;
        }
    }
}
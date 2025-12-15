package com.hua.app.utilities.calories;

import com.hua.app.activityelements.Activity;

public class SimpleCalorieCalculator implements CalorieCalculationFormula {
    private double weight;
    
    public SimpleCalorieCalculator(double weight) {
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
package com.hua.app.utilities.calories;

import com.hua.app.activityelements.Activity;

public class SimpleCalorieCalculator implements CalorieCalculationFormula {
    private double weight;
    
    public SimpleCalorieCalculator(double weight) {
        this.weight = weight;
    }
    
    @Override
    public double calculate(Activity activity) {
        return MetFactory.createMetValue(activity.getAverageSpeed(), activity.getActivityType()) * weight * activity.getDuration() / 60.0;
    }
}
package com.hua.app.data.calories;

import com.hua.app.basicelements.Activity;

public class SimpleFormula implements Formula {
    private double weight;
    
    public SimpleFormula(double weight) {
        this.weight = weight;
    }
    
    @Override
    public double calculate(Activity activity) {
        try {
            return 0.0175 * MetFactory.createMetValue(activity.getAverageSpeed(), activity.getType()) * weight * (activity.getDurationInSeconds() / 60.0);
        } catch (IllegalArgumentException e) {
            return 0;
        }
    }
}
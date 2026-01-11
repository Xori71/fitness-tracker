package com.hua.app.utilities.calories;

import com.hua.app.activityelements.Activity;
import com.hua.app.activityelements.CustomActivity;
import com.hua.app.utilities.userinterface.data.DataHolder;

public class ComplexCalorieCalc implements CalorieCalcFormula{
    private DataHolder data;
    
    public ComplexCalorieCalc(DataHolder data) {
        this.data = data;
    }
    
    @Override
    public double calculate(Activity activity) {
        if (data.getSex().equals("m")) {
            return (-55.0969 + (0.6309 * activity.getAverageHeartRate()) + (0.1966 * data.getWeight()) + (0.2017 * data.getAge())) * (activity.getDuration() / 60.0) / 4.184;
        } else {
            return (-20.4022 + (0.4472 * activity.getAverageHeartRate()) + (0.1263 * data.getWeight()) + (0.074 * data.getAge())) * (activity.getDuration() / 60.0) / 4.184;
        }
    }
    
    @Override
    public double calculate(CustomActivity customActivity) {
        if (data.getSex().equals("m")) {
            return (-55.0969 + (0.6309 * Double.parseDouble(customActivity.getAverageHeartRate())) + (0.1966 * data.getWeight()) + (0.2017 * data.getAge())) * (customActivity.getDurationInSeconds() / 60.0) / 4.184;
        } else {
            return (-20.4022 + (0.4472 * Double.parseDouble(customActivity.getAverageHeartRate())) + (0.1263 * data.getWeight()) + (0.074 * data.getAge())) * (customActivity.getDurationInSeconds() / 60.0) / 4.184;
        }
    }
}
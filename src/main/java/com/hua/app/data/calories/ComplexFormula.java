package com.hua.app.data.calories;

import com.hua.app.basicelements.Activity;
import com.hua.app.data.DataHolder;

public class ComplexFormula implements Formula{
    private DataHolder data;
    
    public ComplexFormula(DataHolder data) {
        this.data = data;
    }
    
    @Override
    public double calculate(Activity activity) {
        if (data.getSex().equals("m")) {
            return (-55.0969 + (0.6309 * activity.getAverageHeartRate()) + (0.1966 * data.getWeight()) + (0.2017 * data.getAge())) * (activity.getDurationInSeconds() / 60.0) / 4.184;
        } else {
            return (-20.4022 + (0.4472 * activity.getAverageHeartRate()) + (0.1263 * data.getWeight()) + (0.074 * data.getAge())) * (activity.getDurationInSeconds() / 60.0) / 4.184;
        }
    }
}
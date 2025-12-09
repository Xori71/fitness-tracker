package com.hua.app.activityelements;

import java.util.ArrayList;

import com.hua.app.caloriexpenditurecalculation.CalorieCalculationManager;

public class Activity {
    private String activityType;
    ArrayList<Lap> lapList;
    
    public Activity(String activityType) {
        this.activityType = activityType;
        lapList = new ArrayList<Lap>();
    }
    
    public String getActivityType() {
        return activityType;
    }
    
    public double getDurationInMinutes() {
        int secondsSum = 0;
        for (Lap lap : lapList) {
            secondsSum += lap.getDuration();
        }
        return secondsSum / 60.0;
    }
    
    /* TODO */
    /* public String getDurationInHumanReadableForm(); */
    
    public double getDistance() {
        return lapList.get(lapList.size() - 1).getDistance();
    }
    
    public double getAverageSpeed() {
        double duration = getDurationInMinutes();
        if (duration == 0) {
            return 0.0;
        }
        return (getDistance() / 1000) / (getDurationInMinutes() / 60);
    }
    
    public double getAverageHeartRate() {
        double averageHeartRateSum = 0.0;
        for (Lap lap : lapList) {
            averageHeartRateSum += lap.getAverageHeartRate();
        }
        return averageHeartRateSum / lapList.size();
    }
    
    public double getCaloricExpenditure(CalorieCalculationManager manager) {
        // Use the caller object to get the methods the formulas need
        return manager.calculate(this);
    }
}
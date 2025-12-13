package com.hua.app.activityelements;

import java.util.ArrayList;

import com.hua.app.caloriexpenditurecalculation.CalorieCalculationManager;

public class Activity {
    private String activityType;
    ArrayList<ActivityComponent> subcomponentList;
    
    public Activity(String activityType) {
        this.activityType = activityType;
        subcomponentList = new ArrayList<ActivityComponent>();
    }
    
    public String getActivityType() {
        return activityType;
    }
    
    public double getDuration() {
        int secondsSum = 0;
        for (ActivityComponent subcomponent : subcomponentList) {
            secondsSum += subcomponent.getDuration();
        }
        return secondsSum;
    }
    
    public double getDistance() {
        return subcomponentList.get(subcomponentList.size() - 1).getDistance();
    }
    
    public double getAverageSpeed() {
        double durationInMinutes = getDuration() / 60;
        if (durationInMinutes == 0) {
            return 0.0;
        }
        return (getDistance() / 1000) / (durationInMinutes / 60);
    }
    
    public double getAverageHeartRate() {
        double averageHeartRateSum = 0.0;
        for (ActivityComponent subcomponent : subcomponentList) {
            averageHeartRateSum += subcomponent.getAverageHeartRate();
        }
        return averageHeartRateSum / subcomponentList.size();
    }
    
    public double getCaloricExpenditure(CalorieCalculationManager manager) {
        return manager.calculate(this);
    }
}
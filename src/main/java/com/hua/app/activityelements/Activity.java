package com.hua.app.activityelements;

import java.util.ArrayList;

import com.hua.app.utilities.calories.CalorieCalculationManager;

public class Activity {
    ArrayList<Lap> lapList;
    private String activityType;
    
    public Activity(String activityType) {
        this.activityType = activityType;
        lapList = new ArrayList<Lap>();
    }
    
    public void addLap(Lap lap) {
        lapList.addLast(lap);
    }
    
    public String getActivityType(){
        return activityType;
    }
    
    public double getAverageHeartRate() {
        int heartRateSum = 0;
        int count = 0;
        for (Lap lap : lapList) {
            heartRateSum += lap.getHeartRateSum();
            count += lap.getHeartRateCount();
        }
        
        if (count == 0) {
            return 0;
        }
        return heartRateSum / count;
    }
    
    public int getMaxHeartRate() {
        int maxHeartRate = 0;
        for (Lap lap : lapList) {
            if (maxHeartRate < lap.getMaxHeartRate()) {
                maxHeartRate = lap.getMaxHeartRate();
            }
        }
        
        return maxHeartRate;
    }
    
    public double getDuration() {
        return lapList.get(lapList.size() - 1).getDuration();
    }
    
    public String getFormattedDuration() {
        return lapList.get(lapList.size() - 1).getFormattedDuration();
    }
    
    public double getDistance() {
        return lapList.get(lapList.size() - 1).getDistance();
    }
    
    public double getAverageSpeed() {
        double seconds = getDuration();
        if (seconds == 0) {
            return 0;
        }
        return (getDistance() / 1000) / (seconds / 3600);
    }
    
    public double getCaloricExpenditure(CalorieCalculationManager manager) {
        return manager.calculate(this);
    }
    
    public int[] getMhrZoneDuration(int[] mhrThreshold) {
        int[] duration = new int[5];
        for (Lap lap : lapList) {
            lap.getMhrZoneDuration(mhrThreshold, duration);
        }
        
        return duration;
    }
}
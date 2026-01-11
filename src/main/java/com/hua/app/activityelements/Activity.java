package com.hua.app.activityelements;

import java.util.ArrayList;

import com.hua.app.utilities.calories.CalorieCalcManager;

public class Activity {
    private ArrayList<Lap> lapList;
    private CalorieCalcManager manager;
    private String activityType;
    
    public Activity(String activityType) {
        this.activityType = activityType;
        lapList = new ArrayList<Lap>();
        manager = null;
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
    
    public int getDuration() {
        int firstPoint = lapList.get(0).getTrackpoint(0);
        int lastPoint = lapList.get(lapList.size() - 1).getTrackpoint(-1);
        
        return lastPoint - firstPoint;
    }
    
    public String getFormattedDuration() {
        int duration = getDuration();
        int hours = duration / 3600;
        int minutes = (duration % 3600) / 60;
        int seconds = (duration % 3600) % 60;
        
        return Integer.toString(hours) + ":" + Integer.toString(minutes) + ":" + Integer.toString(seconds);
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
    
    public double getCaloriesBurned() {
        return manager.calculate(this);
    }
    
    public void setManager(CalorieCalcManager manager) {
        this.manager = manager;
    }
}
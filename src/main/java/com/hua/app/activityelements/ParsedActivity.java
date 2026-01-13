package com.hua.app.activityelements;

import java.util.ArrayList;

import com.hua.app.utilities.calories.CalorieCalcManager;

public class ParsedActivity implements Activity {
    private String type;
    private String date;
    private String duration;
    private int durationInSeconds;
    private double distance;
    private double averageSpeed;
    private double averageHeartRate;
    private int maxHeartRate;
    private double calories;
    private CalorieCalcManager manager;
    private ArrayList<Lap> lapList;
    
    public ParsedActivity(String type, String date) {
        this.type = type;
        this.date = date;
        duration = null;
        durationInSeconds = 0;
        distance = 0.0;
        averageSpeed = 0.0;
        averageHeartRate = 0.0;
        maxHeartRate = 0;
        calories = 0;
        manager = null;
        lapList = new ArrayList<Lap>();
    }
    
    public void addLap(Lap lap) {
        lapList.addLast(lap);
    }
    
    public String getType(){
        return type;
    }
    
    public String getDate() {
        return date;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public int getDurationInSeconds() {
        return durationInSeconds;
    }
    
    public double getDistance() {
        return distance;
    }
    
    public double getAverageSpeed() {
        return averageSpeed;
    }
    
    public double getAverageHeartRate() {
        return averageHeartRate;
    }
    
    public int getMaxHeartRate() {
        return maxHeartRate;
    }
    
    public double getCalories() {
        return calories;
    }
    
    public void calculateData() {
        durationInSeconds = calculateDurationInSeconds();
        duration = calculateDuration();
        distance = calculateDistance();
        averageSpeed = calculateAverageSpeed();
        averageHeartRate = calculateAverageHeartRate();
        maxHeartRate = calculateMaxHeartRate();
        calories = calculateCalories();
    }
    
    public void setManager(CalorieCalcManager manager) {
        this.manager = manager;
    }
    
    private double calculateAverageHeartRate() {
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
    
    private int calculateMaxHeartRate() {
        int maxHeartRate = 0;
        for (Lap lap : lapList) {
            if (maxHeartRate < lap.getMaxHeartRate()) {
                maxHeartRate = lap.getMaxHeartRate();
            }
        }
        
        return maxHeartRate;
    }
    
    private int calculateDurationInSeconds() {
        int firstPoint = lapList.get(0).getTrackpoint(0);
        int lastPoint = lapList.get(lapList.size() - 1).getTrackpoint(-1);
        
        return lastPoint - firstPoint;
    }
    
    private String calculateDuration() {
        int duration = getDurationInSeconds();
        int hours = duration / 3600;
        int minutes = (duration % 3600) / 60;
        int seconds = (duration % 3600) % 60;
        
        return Integer.toString(hours) + ":" + Integer.toString(minutes) + ":" + Integer.toString(seconds);
    }
    
    private double calculateDistance() {
        return lapList.get(lapList.size() - 1).getDistance();
    }
    
    private double calculateAverageSpeed() {
        double seconds = getDurationInSeconds();
        if (seconds == 0) {
            return 0;
        }
        return (getDistance() / 1000) / (seconds / 3600);
    }
    
    private double calculateCalories() {
        if (manager != null) {
            return manager.calculate(this);
        }
        return 0.0;
    }
}
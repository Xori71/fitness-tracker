package com.hua.app.basicelements;

import com.hua.app.data.calories.CalorieCalcManager;

/* Class for creating objects using custom values from the user */

public class CustomActivity implements Activity {
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
    
    public CustomActivity() {
        type = null;
        duration = null;
        durationInSeconds = 0;
        distance = 0.0;
        averageSpeed = 0.0;
        averageHeartRate = 0.0;
        maxHeartRate = 0;
        calories = 0.0;
        manager = null;
    }
    
    public void setType(String type) {
		this.type = type;
	}
	
	public void setDate(String date) {
	    this.date = date;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void setAverageSpeed(double averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	public void setAverageHeartRate(double averageHeartRate) {
		this.averageHeartRate = averageHeartRate;
	}

	public void setMaxHeartRate(int maxHeartRate) {
		this.maxHeartRate = maxHeartRate;
	}
	
	public void setManager(CalorieCalcManager manager) {
	    this.manager = manager;
	}

	public String getType() {
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
	    calories = calculateCalories();
	}
	
	private int calculateDurationInSeconds() {
        String tokens[] = duration.split(":");
        int seconds = 0;
        seconds += Integer.parseInt(tokens[0]) * 3600;
        seconds += Integer.parseInt(tokens[1]) * 60;
        seconds += Integer.parseInt(tokens[2]);
        return seconds;
	}
	
	private double calculateCalories() {
        if (manager != null) {
            return manager.calculate(this);
        }
        return 0.0;
    }
}
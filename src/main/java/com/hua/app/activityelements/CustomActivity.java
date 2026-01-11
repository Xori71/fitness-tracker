package com.hua.app.activityelements;

import com.hua.app.utilities.calories.CalorieCalcManager;
import com.hua.app.utilities.userinterface.data.DataHolder;

public class CustomActivity {
    private String type;
    private String duration;
    private String distance;
    private String averageSpeed;
    private String averageHeartRate;
    private String maxHeartRate;
    private Double caloriesBurned;
    private CalorieCalcManager manager;
    
    public CustomActivity(DataHolder data) {
        type = "";
        duration = "00:00:00";
        distance = "0";
        averageSpeed = "0";
        averageHeartRate = "0";
        maxHeartRate = "0";
        caloriesBurned = 0.0;
        manager = null;
    }
    
    public void setType(String type) {
		this.type = type;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public void setAverageSpeed(String averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	public void setAverageHeartRate(String averageHeartRate) {
		this.averageHeartRate = averageHeartRate;
	}

	public void setMaxHeartRate(String maxHeartRate) {
		this.maxHeartRate = maxHeartRate;
	}
	
	public void setManager(CalorieCalcManager manager) {
	    this.manager = manager;
	}

	public String getType() {
		return type;
	}

	public String getDuration() {
		return duration;
	}
	
	public int getDurationInSeconds() {
	    String tokens[] = duration.split(":");
		int seconds = 0;
		seconds += Integer.parseInt(tokens[0]) * 3600;
		seconds += Integer.parseInt(tokens[1]) * 60;
		seconds += Integer.parseInt(tokens[2]);
		return seconds;
	}

	public String getDistance() {
		return distance;
	}

	public String getAverageSpeed() {
		return averageSpeed;
	}

	public String getAverageHeartRate() {
		return averageHeartRate;
	}

	public String getMaxHeartRate() {
		return maxHeartRate;
	}
	
	private double calculateCaloriesBurned() {
	    return manager.calculate(this);
	}

	public double getCaloriesBurned() {
	    caloriesBurned = calculateCaloriesBurned();
		return caloriesBurned;
	}
}
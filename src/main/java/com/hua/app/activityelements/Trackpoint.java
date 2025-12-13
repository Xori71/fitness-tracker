package com.hua.app.activityelements;

public class Trackpoint extends ActivityComponent {
    private String timestamp;
    @SuppressWarnings("unused")
    private double latitude, longtitude, altitude, distance;
    private int heartRate;
    
    public Trackpoint(double latitude, double longtitude, double distance, int heartRate, String timestamp) {
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.distance = distance;
        this.heartRate = heartRate;
        this.timestamp = timestamp;
    }
    
    @Override
    public double getDistance() {
        return distance;
    }
    
    @Override
    public double getAverageHeartRate() {
        return heartRate;
    }
    
    @Override
    public double getMaxHeartRate() {
		return heartRate;
	}
    
    @Override
    public int getDuration() {
        String filter = "[:]";
        String[] splitTime = timestamp.split(filter);
        return Integer.parseInt(splitTime[0]) * 3600 + Integer.parseInt(splitTime[1]) * 60 + Integer.parseInt(splitTime[2]);
    }
}
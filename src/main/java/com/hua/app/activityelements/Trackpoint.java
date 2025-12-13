package com.hua.app.activityelements;

public class Trackpoint {
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

    public int getHeartRate() {
        return heartRate;
    }
	
	public String getTimestamp() {
	    return timestamp;
	}
	
	public double getDistance() {
        return distance;
    }
}
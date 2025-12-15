package com.hua.app.activityelements;

public class Trackpoint {
    private String timestamp;
    @SuppressWarnings("unused")
    private double latitude, longtitude, altitude, distance;
    private int heartRate;
    
    public Trackpoint(double latitude, double longtitude, double altitude, double distance, int heartRate, String timestamp) {
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.altitude = altitude;
        this.distance = distance;
        this.heartRate = heartRate;
        this.timestamp = timestamp;
    }

    public int getHeartRate() {
        return heartRate;
    }
	
	public int getTimestampInSeconds() {
        String[] timeSplit = timestamp.split(":");
        return Integer.parseInt(timeSplit[0]) * 3600 + Integer.parseInt(timeSplit[1]) * 60 + Integer.parseInt(timeSplit[2]);
	}
	
	public double getDistance() {
        return distance;
    }
}
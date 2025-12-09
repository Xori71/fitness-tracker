package com.hua.app.activityelements;

public class Trackpoint {
    private String timestamp;
    @SuppressWarnings("unused")
    private double latitude, longtitude, altitude, distance;
    private int heartRate;
    
    public Trackpoint(String timestamp, double latitude, double longtitude, double distance, int heartRate) {
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.distance = distance;
        this.heartRate = heartRate;
    }
    
    public int getHeartRate() {
        return heartRate;
    }
    
    public double getDistance() {
        return distance;
    }
    
    public String getDuration() {
        return timestamp;
    }
}
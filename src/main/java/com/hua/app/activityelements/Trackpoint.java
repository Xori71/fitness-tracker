package com.hua.app.activityelements;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Trackpoint {
    private LocalDateTime timestamp;
    @SuppressWarnings("unused")
    private double latitude, longtitude, altitude, distance;
    private int heartRate;
    
    public Trackpoint(double latitude, double longtitude, double altitude, double distance, int heartRate, Instant timestamp) {
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.altitude = altitude;
        this.distance = distance;
        this.heartRate = heartRate;
        this.timestamp = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
    }

    public int getHeartRate() {
        return heartRate;
    }
	
	public int getTimestampInSeconds() {
        return timestamp.toLocalTime().toSecondOfDay();
	}
	
	public double getDistance() {
        return distance;
    }
}
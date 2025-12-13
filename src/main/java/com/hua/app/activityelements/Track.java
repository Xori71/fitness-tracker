package com.hua.app.activityelements;

import java.util.ArrayList;

public class Track {
    ArrayList<Trackpoint> trackpointList;
    
    public Track() {
        trackpointList = new ArrayList<Trackpoint>();
    }
    
    public void addTrackpoint(Trackpoint trackpoint) {
        trackpointList.addLast(trackpoint);
    }
    
    public double getHeartRateSum() {
        int heartRateSum = 0;
        for (Trackpoint trackpoint : trackpointList) {
            heartRateSum += trackpoint.getHeartRate();
        }
        return heartRateSum;
    }
    
    public int getHeartRateCount() {
        return trackpointList.size();
    }
    
    public int getMaxHeartRate() {
        int maxHeartRate = 0;
        for (Trackpoint trackpoint : trackpointList) {
            if (maxHeartRate < trackpoint.getHeartRate()) {
                maxHeartRate = trackpoint.getHeartRate();
            }
        }
        
        return maxHeartRate;
    }
    
    public String getFormattedDuration() {
        return trackpointList.get(trackpointList.size() - 1).getTimestamp();
    }
    
    // Use the last trackpoint to find the total distance
    public double getDistance() {
        return trackpointList.get(trackpointList.size() - 1).getDistance();
    }
}
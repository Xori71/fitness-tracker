package com.hua.app.activityelements;

import java.util.ArrayList;

public class Track {
    private ArrayList<Trackpoint> trackpointList;
    
    public Track() {
        trackpointList = new ArrayList<Trackpoint>(); 
    }
    
    // Use the last trackpoint to find the total distance
    public double getDistance() {
        return trackpointList.get(trackpointList.size() - 1).getDistance();
    }
    
    public double getAverageHeartRate() {
        double heartRateSum = 0.0;
        for (Trackpoint trackpoint : trackpointList) {
            heartRateSum += trackpoint.getHeartRate();
        }
        return heartRateSum / trackpointList.size();
    }
}
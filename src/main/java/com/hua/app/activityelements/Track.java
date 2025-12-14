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
    
    public int getDuration() {
         return trackpointList.get(trackpointList.size() - 1).getTimestampInSeconds();
    }
    
    public String getFormattedDuration() {
        return trackpointList.get(trackpointList.size() - 1).getTimestamp();
    }
    
    // Use the last trackpoint to find the total distance
    public double getDistance() {
        return trackpointList.get(trackpointList.size() - 1).getDistance();
    }
    
    public void getMhrZoneDuration(int[] mhrThreshold, int[] duration) {
        for (int i = 0; i < trackpointList.size() - 1; i++) {
            Trackpoint current = trackpointList.get(i);
            Trackpoint next = trackpointList.get(i + 1);
            int currentTime = current.getTimestampInSeconds();
            int nextTime = next.getTimestampInSeconds();
            
            if (current.getHeartRate() == 0) {
                continue;
            }
            
            int timeDifference = nextTime - currentTime;
            for (int j = duration.length - 1; j >= 0; j--) {
                if (current.getHeartRate() >= mhrThreshold[j]) {
                    duration[j] += timeDifference;
                    break;
                }
            }
        }
    }
}
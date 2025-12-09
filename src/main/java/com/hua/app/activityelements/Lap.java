package com.hua.app.activityelements;

import java.util.ArrayList;

public class Lap {
    // Duration is in seconds
    private int duration;
    private ArrayList<Track> trackList;
    
    public Lap() {
        trackList = new ArrayList<Track>();
    }
    
    public int getDuration() {
        return duration;
    }
    
    public double getDistance() {
        return trackList.get(trackList.size() - 1).getDistance();
    }
    
    public double getAverageHeartRate() {
        double averageHeartRateSum = 0.0;
        for (Track track : trackList) {
            averageHeartRateSum += track.getAverageHeartRate();
        }
        return averageHeartRateSum / trackList.size();
    }
}
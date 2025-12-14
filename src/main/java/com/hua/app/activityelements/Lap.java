package com.hua.app.activityelements;

import java.util.ArrayList;

public class Lap {
    ArrayList<Track> trackList;
    
    public Lap() {
        trackList = new ArrayList<Track>();
    }
    
    public void addTrack(Track track) {
        trackList.addLast(track);
    }
    
    public double getHeartRateSum() {
        int heartRateSum = 0;
        for (Track track : trackList) {
            heartRateSum += track.getHeartRateSum();
        }
        return heartRateSum;
    }
    
    public int getHeartRateCount() {
        int count = 0;
        for (Track track : trackList) {
            count += track.getHeartRateCount();
        }
        return count;
    }
    
    public int getMaxHeartRate() {
        int maxHeartRate = 0;
        for (Track track : trackList) {
            if (maxHeartRate < track.getMaxHeartRate()) {
                maxHeartRate = track.getMaxHeartRate();
            }
        }
        
        return maxHeartRate;
    }
    
    public int getDuration() {
         return trackList.get(trackList.size() - 1).getDuration();
    }
    public String getFormattedDuration() {
        return trackList.get(trackList.size() - 1).getFormattedDuration();
    }
    
    public double getDistance() {
        return trackList.get(trackList.size() - 1).getDistance();
    }
    
    public void getMhrZoneDuration(int[] mhrThreshold, int[] duration) {
        for (Track track : trackList) {
            track.getMhrZoneDuration(mhrThreshold, duration);
        }
    }
}
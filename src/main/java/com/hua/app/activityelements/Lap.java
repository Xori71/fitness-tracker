package com.hua.app.activityelements;

public class Lap extends ActivityComponent {
    private int duration;
    
    public Lap(int duration) {
        this.duration = duration;
    }
    
    public int getDuration() {
        return duration;
    }
    
    public double getDistance() {
        return subcomponentList.get(subcomponentList.size() - 1).getDistance();
    }
}
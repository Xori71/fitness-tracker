package com.hua.app.activityelements;

import java.util.ArrayList;

public abstract class ActivityComponent {
    ArrayList<ActivityComponent> subcomponentList = null;
    
    public void addSubcomponent(ActivityComponent subcomponent) {
        if (subcomponentList == null) {
            subcomponentList = new ArrayList<ActivityComponent>();
        }
        subcomponentList.addLast(subcomponent);
    }
    
    public double getAverageHeartRate() {
        double averageHeartRateSum = 0.0;
        for (ActivityComponent subcomponent : subcomponentList) {
            averageHeartRateSum += subcomponent.getAverageHeartRate();
        }
        return averageHeartRateSum / subcomponentList.size();
    }
    
    public double getMaxHeartRate() {
        double maxAverageHeartRate = 0;
        for (ActivityComponent subcomponent : subcomponentList) {
            double averageHeartRate = subcomponent.getAverageHeartRate();
            if (maxAverageHeartRate < averageHeartRate) {
                maxAverageHeartRate = averageHeartRate;
            }
        }
        return maxAverageHeartRate;
    }
    
    public int getDuration() {
        int secondsSum = 0;
        for (ActivityComponent subcomponent : subcomponentList) {
            secondsSum += subcomponent.getDuration();
        }
        return secondsSum;
    }
    
    public abstract double getDistance();
}
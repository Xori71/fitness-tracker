package com.hua.app.activityelements;

import com.hua.app.utilities.calories.CalorieCalcManager;

public interface Activity {
    String getType();
    String getDate();
    String getDuration();
    int getDurationInSeconds();
    double getDistance();
    double getAverageSpeed();
    double getAverageHeartRate();
    int getMaxHeartRate();
    double getCalories();
    void calculateData();
    void setManager(CalorieCalcManager manager);
}
package com.hua.app.basicelements;

import com.hua.app.data.calories.CalorieCalcManager;

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
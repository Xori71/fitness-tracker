package com.hua.app.basicelements;

import com.hua.app.data.calories.CalorieCalcManager;

/* Activity interface for generalization purposes */

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
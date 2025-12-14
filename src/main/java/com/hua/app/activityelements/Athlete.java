package com.hua.app.activityelements;

import java.util.ArrayList;

public class Athlete {
    private ArrayList<Activity> activityList;
    
    public Athlete() {
        activityList = new ArrayList<Activity>();
    }
    
    public ArrayList<Activity> getActivities() {
        return activityList;
    }
    
    public void addActivity(Activity activity) {
        activityList.addLast(activity);
    }
}
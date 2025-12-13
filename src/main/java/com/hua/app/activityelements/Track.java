package com.hua.app.activityelements;

public class Track extends ActivityComponent {
    // Use the last trackpoint to find the total distance
    @Override
    public double getDistance() {
        return subcomponentList.get(subcomponentList.size() - 1).getDistance();
    }
}
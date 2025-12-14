package com.hua.app;
import com.hua.app.activityelements.Activity;
import com.hua.app.utilities.xmlparser.XmlParser;

import java.util.ArrayList;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ArrayList<Activity> activityArray = new ArrayList<Activity>();
        activityArray = XmlParser.TcxParse("walking_activity_1.tcx", activityArray);
        for (Activity activity : activityArray){
            System.out.println("Activity: " + activity.getActivityType());
            System.out.println("Total Time: " + activity.getFormattedDuration());
            System.out.println("Total Distance:" + activity.getDistance());
            System.out.println("Avg Speed:" + activity.getAverageSpeed());
            System.out.println("Avg Heart Rate:" + activity.getAverageHeartRate());
        }
        System.out.println("8=>");
    }


}

package com.hua.app;

import com.hua.app.activityelements.Activity;
import com.hua.app.utilities.xmlparser.XmlParser;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        ArrayList<Activity> activityArray = new ArrayList<Activity>();
        activityArray = XmlParser.TcxParse("walking_activity_1.tcx", activityArray);
        for (Activity activity : activityArray){
            System.out.println("Activity: " + activity.getActivityType());
            System.out.println("Total Time: " + activity.getFormattedDuration());
            System.out.printf("Total Distance: %.2fm\n", activity.getDistance());
            System.out.printf("Avg Speed: %.2f km/h\n", activity.getAverageSpeed());
            System.out.println("Avg Heart Rate: " + activity.getAverageHeartRate());
        }
    }
}

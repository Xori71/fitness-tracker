package com.hua.app;

import com.hua.app.activityelements.Activity;
import com.hua.app.utilities.argsparser.MainArgsParser;
import com.hua.app.utilities.calories.CalculatorFactory;
import com.hua.app.utilities.calories.CalorieCalculationManager;
import com.hua.app.utilities.heartratezones.ZoneThresholdCreator;
import com.hua.app.utilities.xmlparser.XmlParser;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        double weight = 0;
        int age = 0;
        String sex = null;
        ArrayList<String> totalFiles = new ArrayList<String>();
        
        MainArgsParser.parseArgs(args, weight, age, sex, totalFiles);
        
        ArrayList<Activity> activityArray = new ArrayList<Activity>();
        for (String file : totalFiles) {
            activityArray = XmlParser.TcxParse(file, activityArray);
        }
        
        for (Activity activity : activityArray) {
            System.out.println("* Activity: " + activity.getActivityType());
            System.out.println("* Total Time: " + activity.getFormattedDuration());
            System.out.printf("* Total Distance: %.2fm\n", activity.getDistance());
            System.out.printf("* Avg Speed: %.2f km/h\n", activity.getAverageSpeed());
            System.out.println("* Avg Heart Rate: " + activity.getAverageHeartRate());
            System.out.println("* Max Heart Rate: " + activity.getMaxHeartRate());
            
            CalorieCalculationManager manager = new CalorieCalculationManager();
            manager.setStrategy(CalculatorFactory.createCalculator(weight, age, sex));
            if (manager.formulaExists()) {
                double value = manager.calculate(activity);
                if (value != -1) {
                    System.out.printf("* Calories Expended: %.2f kcal\n", value);
                }
            }
            
            if (age != 0) {
                int[] mhrThresholds = ZoneThresholdCreator.createThresholds(age);
                int[] duration = activity.getMhrZoneDuration(mhrThresholds);
                System.out.println("* HR Zones:");
                for (int i = 0; i < duration.length; i++) {
                    System.out.println("    Time spent in Heart Zone " + (i + 1) + ": " + duration[i] + "s");
                }
            }
            
            System.out.println("--------------------");
        }
    }
}

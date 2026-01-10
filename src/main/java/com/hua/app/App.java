package com.hua.app;

import com.hua.app.activityelements.Activity;
import com.hua.app.utilities.calories.CalcFactory;
import com.hua.app.utilities.calories.CalorieCalcManager;
import com.hua.app.utilities.xmlparser.XmlParser;
import java.util.ArrayList;

public class App {
    static double weight = 0;
    public static int age = 0;
    static String sex = null;
    static ArrayList<String> totalFiles = new ArrayList<String>();
    
    public static void main(String[] args) {
        parseArgs(args);
        
        ArrayList<Activity> activityArray = new ArrayList<Activity>();
        for (String file : totalFiles) {
            activityArray = XmlParser.TcxParse(file, activityArray);
        }
        
        for (Activity activity : activityArray) {
            /* activityArray may be partially or entirely filled with null values */
            if (activity != null) {
                System.out.println("* Activity: " + activity.getActivityType());
                System.out.println("* Total Time: " + activity.getFormattedDuration());
                System.out.printf("* Total Distance: %.2fm\n", activity.getDistance());
                System.out.printf("* Avg Speed: %.2f km/h\n", activity.getAverageSpeed());
                System.out.println("* Avg Heart Rate: " + activity.getAverageHeartRate());
                System.out.println("* Max Heart Rate: " + activity.getMaxHeartRate());
                
                int[] duration = activity.getMhrZoneDuration();
                boolean hasHeartZones = false;
                if (duration != null) {
                    hasHeartZones = true;
                    System.out.println("* Heart Rate Zones:");
                    for (int i = 0; i < 5; i++) {
                        System.out.println("    Zone" + (i + 1) + ": " + duration[i] + "s");
                    }
                }
                
                CalorieCalcManager manager = new CalorieCalcManager();
                manager.setStrategy(CalcFactory.createCalculator(weight, age, sex));
                if (manager.formulaExists()) {
                    double value = manager.calculate(activity);
                    if (value != -1) {
                        System.out.printf("* Calories Expended: %.2f kcal\n", value);
                    }
                }
                
                System.out.println("----------------------------------------");
            }
        }
    }
    
    /* Parser for main's args array */
    private static void parseArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String currentArg = args[i];
            String argValue = null;
            
            if (currentArg.equals("-w")) {
                if (i + 1 >= args.length) {
                    break;
                }
                
                argValue = args[i + 1];
                try {
                    double value = Double.parseDouble(argValue);
                    weight = value;
                    if (weight < 0) {
                        throw new IllegalArgumentException();
                    }
                    i++;
                    continue;
                } catch (NumberFormatException e) {
                    System.err.println("ERROR: Expected numerical value after '-w'");
                    System.exit(1);
                } catch (IllegalArgumentException e) {
                    System.err.println("ERROR: Expected positive or zero value after '-w'");
                    System.exit(1);
                }
            }
            
            if (currentArg.equals("-a")) {
                if (i + 1 >= args.length) {
                    break;
                }
                
                argValue = args[i + 1];
                try {
                    int value = Integer.parseInt(argValue);
                    age = value;
                    if (age < 0) {
                        throw new IllegalArgumentException();
                    }
                    i++;
                    continue;
                } catch (NumberFormatException e) {
                    System.err.println("ERROR: Expected numerical value after '-a'");
                    System.exit(1);
                } catch (IllegalArgumentException e) {
                    System.err.println("ERROR: Expected positive or zero value  after '-a'");
                    System.exit(1);
                }
            }
            
            if (currentArg.equals("-s")) {
                if (i + 1 >= args.length) {
                    break;
                }
                
                argValue = args[i + 1];
                try {
                    if (argValue.matches("^[mM]$")) {
                        sex = "Male";
                    } else if (argValue.matches("^[fF]$")) {
                        sex = "Female";
                    } else {
                        throw new IllegalArgumentException();
                    }
                    i++;
                    continue;
                } catch (IllegalArgumentException e) {
                    System.err.println("ERROR: Expected 'm' (male) or 'f' (female) after '-s'");
                    System.exit(1);
                }
            }
            
            if (currentArg.endsWith(".tcx")) {
                totalFiles.addLast(currentArg);
            }
        }
    }
}

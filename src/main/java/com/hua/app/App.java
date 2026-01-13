package com.hua.app;

import com.hua.app.activityelements.Activity;
import com.hua.app.utilities.calories.FormulaPicker;
import com.hua.app.utilities.calories.CalorieCalcManager;
import com.hua.app.utilities.userinterface.data.DataHolder;
import java.io.File;

public class App {
    public static void main(String[] args) {
        DataHolder data = new DataHolder();
        parseArgs(args, data);
        data.populateActivityList();
        
        FormulaPicker.chooseFormula(data);
        CalorieCalcManager manager = new CalorieCalcManager();
        manager.setFormula(data.getFormula());
        
        for (Activity activity : data.getActivityList()) {
            /* activityArray may be partially or entirely filled with null values */
            if (activity != null) {
                activity.setManager(manager);
                activity.calculateData();
                
                System.out.println("* Activity: " + activity.getType());
                System.out.println("* Date: " + activity.getDate());
                System.out.println("* Total Time: " + activity.getDuration());
                System.out.printf("* Total Distance: %.2fm\n", activity.getDistance());
                System.out.printf("* Avg Speed: %.2f km/h\n", activity.getAverageSpeed());
                System.out.println("* Avg Heart Rate: " + activity.getAverageHeartRate());
                System.out.println("* Max Heart Rate: " + activity.getMaxHeartRate());
                if (activity.getCalories() != 0) {
                    System.out.println("* Calories: " + activity.getCalories());
                }
                System.out.println("----------------------------------------");
            }
        }
    }
    
    /* Parser for main's args array */
    private static void parseArgs(String[] args, DataHolder data) {
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
                    data.setWeight(value);
                    if (data.getWeight() < 0) {
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
                    data.setAge(value);
                    if (data.getAge() < 0) {
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
                        data.setSex("Male");
                    } else if (argValue.matches("^[fF]$")) {
                        data.setSex("Female");
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
                File file = new File(currentArg);
                data.getFileList().add(file);
            }
        }
    }
}

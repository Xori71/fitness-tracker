package com.hua.app.caloriexpenditurecalculation;

// The role of this class is to give me a correct m value depending on the activity type and speed
public class MetFactory {
    public static double createMetValue(double averageSpeed, String activityType) {
        if (averageSpeed <= 0) {
            throw new IllegalArgumentException("Average speed cannot be equal to or less than zero.");
        }
        
        if (activityType.equals("Walking")) {
            if (averageSpeed <= 3.2) {
                return 2.8;
            } else if (averageSpeed <= 5.1) {
                return 3.5;
            } else if (averageSpeed <= 6.4) {
                return 5.0;
            } else {
                return 6.5;
            }
        } else if (activityType.equals("Running")) {
            if (averageSpeed <= 8.0) {
                return 7.65;
            } else if (averageSpeed <= 9.6) {
                return 9.9;
            } else {
                return 11.9;
            }
        } else if (activityType.equals("Cycling")) {
            if (averageSpeed < 16.0) {
                return 4.0;
            } else if (averageSpeed < 19.0) {
                return 7.0;
            } else {
                return 10.0;
            }
        } else if (activityType.equals("Swimming")) {
            if (averageSpeed <= 2.0) {
                return 5.3;
            } else if (averageSpeed <= 3.0) {
                return 7.0;
            } else {
                return 9.0;
            }
        } else {
            throw new IllegalArgumentException("Unsupported activity type.");
        }
    }
}
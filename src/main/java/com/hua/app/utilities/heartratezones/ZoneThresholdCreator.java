package com.hua.app.utilities.heartratezones;

public class ZoneThresholdCreator {
    private static final double[] ZONE_PERCENTAGES = {0.5, 0.6, 0.7, 0.8, 0.9};
    
    public static int[] createThresholds(int age) {
        if (age == 0) {
            return null;
        }
        
        int mhr = 220 - age;
        int[] mhrThresholds = new int[5];
        for (int i = 0; i < ZONE_PERCENTAGES.length; i++) {
            mhrThresholds[i] = (int) Math.round(mhr * ZONE_PERCENTAGES[i]);
        }
        
        return mhrThresholds;
    }
}
package com.hua.app.utilities.calories;

import com.hua.app.activityelements.Activity;

public class HeartZoneCalorieCalculator implements CalorieCalculationFormula {
    double[] ceff = {0.07, 0.10, 0.13, 0.16, 0.20};
    double weight;
    
    public HeartZoneCalorieCalculator(double weight) {
        this.weight = weight;
    }
    
   	@Override
	public double calculate(Activity activity) {
		int[] duration = activity.getMhrZoneDuration();
		double calories = 0;
		
		for (int i = 0; i < 5; i++) {
		    calories += (duration[i] / 60) * ceff[i] * weight;
		}
		return calories;
	}
}
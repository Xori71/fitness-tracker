package com.hua.app.utilities.calories;

import com.hua.app.activityelements.Activity;
import com.hua.app.activityelements.CustomActivity;

public interface CalorieCalcFormula {
    public double calculate(Activity activity);
    public double calculate(CustomActivity customActivity);
}
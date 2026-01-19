package com.hua.app.data.calories;

import com.hua.app.basicelements.Activity;
import com.hua.app.data.DataHolder;

public class ComplexFormula implements Formula{
    private DataHolder data;
    
    public ComplexFormula(DataHolder data) {
        this.data = data;
    }
    
    @Override
    public double calculate(Activity activity) {
        double calories;
        if (data.getSex().equals("m")) {
            calories = (-55.0969 + (0.6309 * activity.getAverageHeartRate()) + (0.1966 * data.getWeight()) + (0.2017 * data.getAge())) * (activity.getDurationInSeconds() / 60.0) / 4.184;
        } else {
            calories = (-20.4022 + (0.4472 * activity.getAverageHeartRate()) + (0.1263 * data.getWeight()) + (0.074 * data.getAge())) * (activity.getDurationInSeconds() / 60.0) / 4.184;
        }
        
        /* If there is zero-value data in the above formula, the calculation may break
           down and result in a negative expenditure. To prevent this logical error, 
           I treat negative calorie results like it was impossible to calculate 
           the expenditure for the respective activity, based on the data the program
           was given, and return 0 */
        if (calories < 0) {
            return 0.0;
        } else {
            return calories;
        }
    }
}
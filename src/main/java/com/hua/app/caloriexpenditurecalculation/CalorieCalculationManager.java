package com.hua.app.caloriexpenditurecalculation;

import com.hua.app.activityelements.Activity;

public class CalorieCalculationManager {
    CalorieCalculationFormula formula;
    
    public void setStrategy(CalorieCalculationFormula formula) {
        this.formula = formula;
    }
    
    public double calculate(Activity activity) {
        if (formula == null) {
            return 0;
        }
        return formula.calculate(activity);
    }
}
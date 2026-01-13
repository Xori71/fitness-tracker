package com.hua.app.utilities.calories;

import com.hua.app.activityelements.Activity;

public class CalorieCalcManager {
    Formula formula = null;
    
    public void setFormula(Formula formula) {
        this.formula = formula;
    }
    
    public boolean formulaExists() {
        return formula != null;
    }
    
    public double calculate(Activity activity) {
        if (formula == null) {
            return 0.0;
        }
        return formula.calculate(activity);
    }
}
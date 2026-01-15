package com.hua.app.data.calories;

import com.hua.app.basicelements.Activity;

/**
 * This class is meant to handle all the calorie calculations. Activity objects receive the created
 * manager via a setter, and use it to produce a number representing burned calories. 
 */
 
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
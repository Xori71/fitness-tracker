package com.hua.app.utilities.calories;

import com.hua.app.utilities.userinterface.data.DataHolder;

public class CalcFactory {
    public static CalorieCalcFormula createCalculator(DataHolder data) {
        if (data.getWeight() == 0) {
            return null;
        }
        if (data.getAge() == 0 || data.getSex() == null) {
            return new SimpleCalorieCalc(data.getWeight());
        } else {
            return new ComplexCalorieCalc(data);
        }
    }
}
package com.hua.app.utilities.calories;

public class CalcFactory {
    public static CalorieCalcFormula createCalculator(double weight, int age, String sex) {
        if (weight == 0) {
            return null;
        }
        if (age == 0 || sex == null) {
            return new SimpleCalorieCalc(weight);
        } else {
            return new ComplexCalorieCalc(age, sex, weight);
        }
    }
}
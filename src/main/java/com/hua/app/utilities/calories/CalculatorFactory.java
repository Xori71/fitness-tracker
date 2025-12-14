package com.hua.app.utilities.calories;

public class CalculatorFactory {
    public static CalorieCalculationFormula createCalculator(double weight, int age, String sex) {
        if (weight == 0) {
            return null;
        }
        if (age == 0 || sex == null) {
            return new SimpleCalorieCalculator(weight);
        } else {
            return new ComplexCalorieCalculator(age, sex, weight);
        }
    }
}
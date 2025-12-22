package com.hua.app.utilities.calories;

public class CalculatorFactory {
    public static CalorieCalculationFormula createCalculator(double weight, int age, String sex, boolean hasHeartZones) {
        if (weight == 0) {
            return null;
        }
        if (hasHeartZones) {
            return new HeartZoneCalorieCalculator(weight);
        }
        if (age == 0 || sex == null) {
            return new SimpleCalorieCalculator(weight);
        } else {
            return new ComplexCalorieCalculator(age, sex, weight);
        }
    }
}
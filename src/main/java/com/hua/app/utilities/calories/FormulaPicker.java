package com.hua.app.utilities.calories;

import com.hua.app.utilities.userinterface.data.DataHolder;

public class FormulaPicker {
    public static void chooseFormula(DataHolder data) {
        if (data.getWeight() == 0) {
            return;
        }
        if (data.getAge() == 0 || data.getSex() == null) {
            data.setFormula(new SimpleFormula(data.getWeight()));
        } else {
            data.setFormula(new ComplexFormula(data));
        }
    }
}
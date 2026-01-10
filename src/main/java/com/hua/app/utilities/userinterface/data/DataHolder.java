package com.hua.app.utilities.userinterface.data;

import java.io.File;
import java.util.Set;

import com.hua.app.utilities.calories.CalorieCalcFormula;

public class DataHolder {
    private int age;
    private double weight;
    private String sex;
    private Set<File> fileList;
    private CalorieCalcFormula formula;
    
    public void setFormula(CalorieCalcFormula formula) {
        this.formula = formula;
    }
    
    public CalorieCalcFormula getFormula() {
        return formula;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Set<File> getFileList() {
        return fileList;
    }

    public void setFileList(Set<File> fileList) {
        this.fileList = fileList;
    }
}
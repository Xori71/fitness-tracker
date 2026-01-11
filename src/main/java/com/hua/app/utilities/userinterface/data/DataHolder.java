package com.hua.app.utilities.userinterface.data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.hua.app.activityelements.Activity;
import com.hua.app.activityelements.CustomActivity;
import com.hua.app.utilities.calories.CalorieCalcFormula;
import com.hua.app.utilities.xmlparser.XmlParser;

public class DataHolder {
    private int age;
    private double weight;
    private String sex;
    private Set<File> fileList;
    private ArrayList<Activity> activityList;
    private ArrayList<CustomActivity> customActivityList;
    private CalorieCalcFormula formula;
    
    public DataHolder() {
        age = 0;
        weight = 0;
        sex = null;
        fileList = new HashSet<>();
        activityList = new ArrayList<>();
        formula = null;
    }
    
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
    
    public void populateActivityList() {
        XmlParser parser = new XmlParser();
        parser.TcxParse(fileList, activityList);
    }
    
    public ArrayList<Activity> getActivityList() {
        return activityList;
    }
    
    public ArrayList<CustomActivity> getCustomActivityList() {
        return customActivityList;
    }
}
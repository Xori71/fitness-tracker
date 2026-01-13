package com.hua.app.utilities.userinterface.data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.hua.app.activityelements.Activity;
import com.hua.app.utilities.calories.Formula;
import com.hua.app.utilities.xmlparser.XmlParser;

/**
 * DataHolder is the main way we pass information around. Instead of relying on bloated constructors that would need constant "refreshing"
 * (by recreating the respective object again and again and re-passing the required variables), we send this singular object to whoever 
 * needs it, facilitating live updating and retrieval of the data inside thanks to each object holding the same reference to this one.
 */

public class DataHolder {
    private int age;
    private double weight;
    private String sex;
    private Set<File> fileList;
    private Set<File> fileListHistory;
    private ArrayList<Activity> activityList;
    private Formula formula;
    private double calorieTarget;
    
    public DataHolder() {
        age = 0;
        weight = 0;
        sex = null;
        fileList = new HashSet<>();
        activityList = new ArrayList<>();
        formula = null;
        calorieTarget = -1;
    }
    
    public void setFormula(Formula formula) {
        this.formula = formula;
    }
    
    public Formula getFormula() {
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
    
    public void setCalorieTarget(double calorieTarget) {
        this.calorieTarget = calorieTarget;
    }
    
    public double getCalorieTarget() {
        return calorieTarget;
    }
    
    public ArrayList<Activity> getActivityList() {
        return activityList;
    }
    
    public void clearActivityList() {
        activityList.clear();
    }
    
    public void clearFileList() {
        fileList.clear();
    }
    
    public void clearAllData() {
        age = 0;
        weight = 0.0;
        sex = null;
        clearActivityList();
        clearFileList();
        clearFileListHistory();
    }
    
    private void clearFileListHistory() {
        fileListHistory.clear();
    }
}
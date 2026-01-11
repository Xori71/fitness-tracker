package com.hua.app.utilities.userinterface.graphics;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.hua.app.activityelements.Activity;
import com.hua.app.activityelements.CustomActivity;
import com.hua.app.utilities.calories.CalorieCalcManager;
import com.hua.app.utilities.userinterface.data.DataHolder;

public class ResultsPanel {
    /* TODO: This class should create a calorie calulation manager and pass it on to every activity via setters */
    
    private JPanel baseResultsPanel;
    private DataHolder data;
    private CalorieCalcManager manager;
    
    public ResultsPanel(DataHolder data) {
        this.data = data;
        manager = new CalorieCalcManager();
        manager.setStrategy(data.getFormula());
        create();
    }
    
    private void create() {
        baseResultsPanel = new JPanel(true);
        baseResultsPanel.setLayout(new BoxLayout(baseResultsPanel, BoxLayout.Y_AXIS));
    }
    
    private JPanel createEntry(Activity activity) {
        JPanel entry = new JPanel(true);
        return entry;
    }
    
    private JPanel createEntry(CustomActivity customActivity) {
        JPanel entry = new JPanel(true);
        entry.setLayout(new BoxLayout(entry, BoxLayout.Y_AXIS));
        return entry;
    }
}
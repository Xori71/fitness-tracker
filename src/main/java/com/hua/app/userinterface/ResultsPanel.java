package com.hua.app.userinterface;

import java.awt.FlowLayout;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.hua.app.basicelements.Activity;
import com.hua.app.data.DataHolder;
import com.hua.app.data.calories.CalorieCalcManager;

/** 
 * The panel responsible for the display of the calculations, as well as various other secondary data (Possible diviation
 * from daily calorie target, summation of various activity data)
 */

public class ResultsPanel {
    private JPanel baseResultsPanel;
    private JTextArea resultsTextArea;
    private DataHolder data;
    private CalorieCalcManager manager;
    private Runnable switchToMenu;
    private int totalDuration;
    private double totalDistance;
    private double totalCalories;

    public ResultsPanel(DataHolder data, Runnable switchToMenu) {
        this.data = data;
        this.switchToMenu = switchToMenu;
        resultsTextArea = new JTextArea();
        totalDuration = 0;
        totalDistance = 0.0;
        totalCalories = 0.0;
    }
    
    public void create() {
        baseResultsPanel = new JPanel(true);
        baseResultsPanel.setLayout(new BoxLayout(baseResultsPanel, BoxLayout.Y_AXIS));
        
        resultsTextArea = new JTextArea();
        resultsTextArea.setOpaque(false);
        resultsTextArea.setEditable(false);
        resultsTextArea.setBorder(null);
        resultsTextArea.setLineWrap(true);
        resultsTextArea.setWrapStyleWord(true);

        JScrollPane sp = new JScrollPane(resultsTextArea);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        // Holds the three bottom buttons
        JPanel buttonArray = new JPanel(true);
        buttonArray.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JButton backToMenuButton = new JButton("Back to Menu");
        backToMenuButton.addActionListener(l -> {
            switchToMenu.run();
        });
        
        JButton totalStatsButton = new JButton("Display total stats");
        totalStatsButton.addActionListener(l -> {
            if (totalDuration == 0 && totalDistance == 0 && totalCalories == 0) {
                JOptionPane.showMessageDialog(baseResultsPanel, "No data");
            } else {
                JOptionPane.showMessageDialog(baseResultsPanel, createTotalInfoPanel());
            }
        });
        
        JButton calorieTargetButton = new JButton("Calorie target");
        calorieTargetButton.addActionListener(l -> {
            if (data.getDailyBurnedCalories().isEmpty()) {
                JOptionPane.showMessageDialog(baseResultsPanel, "No data");
            } else {
                JOptionPane.showMessageDialog(baseResultsPanel, createCalorieTargetResult());
            }
        });
        
        buttonArray.add(backToMenuButton);
        buttonArray.add(totalStatsButton);
        buttonArray.add(calorieTargetButton);

        baseResultsPanel.add(sp);
        baseResultsPanel.add(buttonArray);
    }

    public void updateDisplay() {
        if (data.getFormula() != null) {
            manager = new CalorieCalcManager();
            manager.setFormula(data.getFormula());
        }
        
        for (Activity activity : data.getActivityList()) {
            activity.setManager(manager);
            activity.calculateData();
            
            totalDuration += activity.getDurationInSeconds();
            totalDistance += activity.getDistance();
            totalCalories += activity.getCalories();
            
            resultsTextArea.append(String.format("* Activity: %s\n",activity.getType()));
            resultsTextArea.append(String.format("* Date: %s\n",activity.getDate()));
            resultsTextArea.append(String.format("* Duration: %s\n", activity.getDuration()));
            resultsTextArea.append(String.format("* Distance: %.2fm\n", activity.getDistance()));
            resultsTextArea.append(String.format("* Average Speed: %.2f km/h\n", activity.getAverageSpeed()));
            resultsTextArea.append(String.format("* Average Heart Rate: %.2f\n", activity.getAverageHeartRate()));
            resultsTextArea.append(String.format("* Max Heart Rate: %d\n", activity.getMaxHeartRate()));
            
            if (activity.getCalories() != 0) {
                resultsTextArea.append(String.format("* Calories Expended: %.2f kcal\n", activity.getCalories()));
                
                if (data.getCalorieTarget() != -1 && activity.getDate() != null) {
                    data.recordDateAndCalories(activity.getDate(), activity.getCalories());
                }
            }
            
            resultsTextArea.append("------------------------------------\n");
        }
    }

    public JPanel getPanel() {
        return baseResultsPanel;
    }
    
    private JPanel createTotalInfoPanel() {
        JPanel basePanel = new JPanel(true);
        basePanel.setLayout(new BoxLayout(basePanel, BoxLayout.Y_AXIS));
        
        if (totalDuration != 0) {
            int totalSeconds = totalDuration;
            int hours = totalSeconds / 3600;
            int minutes = totalSeconds % 3600 / 60;
            int seconds = totalSeconds % 60;
            String hoursStr = (hours < 10) ? String.format("%01d", hours) : String.format("%d", hours);
            String minutesStr = (minutes < 10) ? String.format("%01d", minutes) : String.format("%d", minutes);
            String secondsStr = (seconds < 10) ? String.format("%01d", seconds) : String.format("%d", seconds);
            basePanel.add(new JLabel("Total duration: " + hoursStr + ":" + minutesStr + ":" + secondsStr));
        }
        if (totalDistance != 0) {
            basePanel.add(new JLabel(String.format("Total distance: %.2fm", totalDistance)));
        }
        if (totalCalories != 0) {
            basePanel.add(new JLabel(String.format("Total calories: %.2f kcal", totalCalories)));
        }
        
        return basePanel;
    }
    
    private JScrollPane createCalorieTargetResult() {
        JTextArea textArea = new JTextArea();
        
        // Print all available dates and whether the calorie target
        // has been met or not, in a sorted manner.
        data.getDailyBurnedCalories().entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach(entry -> {
                if (entry.getValue() < data.getCalorieTarget()) {
                    textArea.append(entry.getKey() + ": Not met\n");
                } else {
                    textArea.append("At " + entry.getKey() + ": Met\n");
                }
        });
        
        JScrollPane scrollableTextArea = new JScrollPane(textArea);
        return scrollableTextArea;
    }
}
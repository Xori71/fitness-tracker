package com.hua.app.utilities.userinterface.graphics;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.hua.app.activityelements.CustomActivity;
import com.hua.app.utilities.userinterface.data.DataHolder;

/**
 * This class creates a popup window for the configuration of a custom activity. It has various fields with all of them requiring a value.
 */

public class CustomActivityWindow {
    JFrame popupWindow;
    DataHolder data;
    
    public CustomActivityWindow(DataHolder data) {
        this.data = data;
        create();
    }
    
    private void create() {
        CustomActivity customActivity = new CustomActivity();
        popupWindow = new JFrame();
        popupWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popupWindow.setLocationRelativeTo(null);
        
        JPanel basePanel = new JPanel(true);
        basePanel.setLayout(new BoxLayout(basePanel, BoxLayout.Y_AXIS));
        basePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        popupWindow.add(basePanel);
        
        JPanel topPanel = new JPanel(true);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(InputFieldFactory.addField(topPanel, "Activity type: ", "e.g. Hiking", ".*\\S.*", 20));
        topPanel.add(InputFieldFactory.addField(topPanel, "Date: ", "e.g. 2024-12-31", "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$", 20));
        topPanel.add(InputFieldFactory.addField(topPanel, "Duration: ", "e.g. 03:45:11", "^\\d+:[0-5]\\d:[0-5]\\d$", 20));
        topPanel.add(InputFieldFactory.addField(topPanel, "Total distance: ", "e.g. 4567.89", "^\\d+(\\.\\d+)?$", 20));
        topPanel.add(InputFieldFactory.addField(topPanel, "Average speed: ", "e.g. 12.56", "^\\d+(\\.\\d+)?$", 20));
        topPanel.add(InputFieldFactory.addField(topPanel, "Average heart rate: ", "e.g. 97", "^\\d+(\\.\\d+)?$", 20));
        topPanel.add(InputFieldFactory.addField(topPanel, "Max heart rate: ", "e.g. 123", "^\\d+$", 20));
        basePanel.add(topPanel);
        
        JPanel bottomPanel = new JPanel(true);
        bottomPanel.setLayout(new FlowLayout());
        JButton proceedButton = new JButton("Proceed");
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        proceedButton.addActionListener(l -> {
            int index = 0;
            for (Component subpanel : topPanel.getComponents()) {
                for (Component component : ((JPanel) subpanel).getComponents()) {
                    if (component instanceof JTextField) {
                        JTextField field = (JTextField) component;
                        if (field.getInputVerifier() != null && !field.getInputVerifier().verify(field)) {
                            JOptionPane.showMessageDialog(popupWindow, "Please ensure that all fields are valid");
                            return;
                        } else {
                            populateCustomActivity(customActivity, index, field);
                        }
                    }
                }
                index++;
            }
            data.getActivityList().addLast(customActivity);
            popupWindow.dispose();
        });
        
        bottomPanel.add(proceedButton);
        basePanel.add(bottomPanel);
        popupWindow.pack();
        popupWindow.setVisible(true);
    }
    
    private void populateCustomActivity(CustomActivity customActivity, int index, JTextField field) {
        switch (index) {
            case 0:
                customActivity.setType(field.getText());
                break;
            case 2:
                customActivity.setDuration(field.getText());
                break;
            case 3:
                customActivity.setDistance(Double.parseDouble(field.getText()));
                break;
            case 4:
                customActivity.setAverageSpeed(Double.parseDouble(field.getText()));
                break;
            case 5:
                customActivity.setAverageHeartRate(Double.parseDouble(field.getText()));
                break;
            case 6:
                customActivity.setMaxHeartRate(Integer.parseInt(field.getText()));
                break;
        }
    }
}
package com.hua.app.utilities.userinterface.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.hua.app.activityelements.CustomActivity;
import com.hua.app.utilities.userinterface.data.DataHolder;

public class CustomActivityWindow {
    JFrame popupWindow;
    DataHolder data;
    
    public CustomActivityWindow(DataHolder data) {
        this.data = data;
        create();
    }
    
    private void create() {
        CustomActivity customActivity = new CustomActivity(data);
        popupWindow = new JFrame();
        popupWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popupWindow.setLocationRelativeTo(null);
        
        JPanel basePanel = new JPanel(true);
        basePanel.setLayout(new BoxLayout(basePanel, BoxLayout.Y_AXIS));
        basePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        popupWindow.add(basePanel);
        
        JPanel topPanel = new JPanel(true);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(addField(topPanel, "Activity type: ", createInputVerifier(".*\\S.*")));
        topPanel.add(addField(topPanel, "Duration: ", createInputVerifier("^\\d+:[0-5]\\d:[0-5]\\d$")));
        topPanel.add(addField(topPanel, "Total distance: ", createInputVerifier("^\\d+(\\.\\d+)?$")));
        topPanel.add(addField(topPanel, "Average speed: ", createInputVerifier("^\\d+(\\.\\d+)?$")));
        topPanel.add(addField(topPanel, "Average heart rate: ", createInputVerifier("^\\d+(\\.\\d+)?$")));
        topPanel.add(addField(topPanel, "Max heart rate: ", createInputVerifier("^\\d+$")));
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
                        }
                        populateCustomActivity(customActivity, index, field);
                    }
                }
                index++;
            }
            data.getCustomActivityList().addLast(customActivity);
        });
        
        bottomPanel.add(proceedButton);
        basePanel.add(bottomPanel);
        popupWindow.pack();
        popupWindow.setVisible(true);
    }
    
    private JPanel addField(JPanel targetPanel, String labelText, InputVerifier inputVerifier) {
        JPanel subpanel = new JPanel(true);
        subpanel.setLayout(new FlowLayout());
        
        JTextField field = new JTextField(20);
        field.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        field.setInputVerifier(inputVerifier);
        subpanel.add(new JLabel(labelText));
        subpanel.add(field);
        return subpanel;
    }
    
    private InputVerifier createInputVerifier(String regex) {
        return new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                String text = ((JTextField) input).getText();
                return text.matches(regex);
            }
            
            @Override
            public boolean shouldYieldFocus(JComponent source, JComponent target) {
                if (!verify(source)) {
                    source.setBackground(Color.RED);
                } else {
                    source.setBackground(Color.WHITE);
                }
                
                return true;
            }
        };
    }
    
    private void populateCustomActivity(CustomActivity customActivity, int index, JTextField field) {
        switch (index) {
            case 0:
                customActivity.setType(field.getText());
                break;
            case 1:
                customActivity.setDuration(field.getText());
                break;
            case 2:
                customActivity.setDistance(field.getText());
                break;
            case 3:
                customActivity.setAverageSpeed(field.getText());
                break;
            case 4:
                customActivity.setAverageHeartRate(field.getText());
                break;
            case 5:
                customActivity.setMaxHeartRate(field.getText());
                break;
        }
    }
}
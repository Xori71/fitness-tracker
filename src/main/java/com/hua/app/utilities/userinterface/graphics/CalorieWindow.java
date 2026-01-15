package com.hua.app.utilities.userinterface.graphics;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.hua.app.utilities.calories.FormulaPicker;
import com.hua.app.utilities.userinterface.data.DataHolder;

/**
 * This class creates a popup window for the input of a desired formula/daily calorie target. Whether or not the window appears
 * depends on the amount of information the user has given the program, with the respective checks being handled by the caller class.
 */

public class CalorieWindow {
    private JFrame popupWindow;
    private JPanel menuPanel;
    private JPanel calorieTargetPanel;
    private Runnable switchToResults;
    private JComboBox<String> selection;
    private DataHolder data;
    
    public CalorieWindow(DataHolder data, Runnable switchToResults, JPanel menuPanel)  {
        this.data = data;
        this.switchToResults = switchToResults;
        this.menuPanel = menuPanel;
        
        createWindow();
    }
    
    public void displayWindow() {
        popupWindow.setVisible(true);
    }
    
    public void hideWindow() {
        popupWindow.setVisible(false);
    }
    
    public void refreshWindow() {
        JTextField textField = (JTextField) calorieTargetPanel.getComponent(1);
        textField.setText("");
        selection.setSelectedIndex(0);
    }
    
    private void createWindow() {
        popupWindow = new JFrame();
        popupWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        createPanel();
        popupWindow.pack();
        popupWindow.setLocationRelativeTo(menuPanel);
    }
    
    
    private void createPanel() {
        JPanel settingPanel = new JPanel();
        settingPanel.setLayout(new BoxLayout(settingPanel, BoxLayout.Y_AXIS));
        JLabel prompt = new JLabel("Select preferred formula for caloric expenditure calculation");
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        selection = new JComboBox<>();
        selection.addItem("Simple");
        selection.addItem("Advanced");
        selection.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        calorieTargetPanel = InputFieldFactory.addField(settingPanel, "Enter a daily calorie target (Optional): ", "", "^[1-9]\\d*(\\.\\d+)?$", 15);
        
        settingPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        settingPanel.add(prompt);
        settingPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        settingPanel.add(selection);
        settingPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        settingPanel.add(calorieTargetPanel);
        settingPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        settingPanel.add(createButton());
        
        popupWindow.add(settingPanel);
        popupWindow.pack();
    }
    
    private JButton createButton() {
        JButton proceedButton = new JButton("Proceed");
        proceedButton.addActionListener(l -> {
            if (isSelectionValid()) {
                JTextField calorieTargetField = (JTextField) calorieTargetPanel.getComponent(1);
                String calorieTargetText = calorieTargetField.getText();
                if (!calorieTargetText.equals("")) {
                    data.setCalorieTarget(Double.parseDouble(calorieTargetText));
                }
                FormulaPicker.chooseFormula(data);
                switchToResults.run();
                hideWindow();
            }
        });
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return proceedButton;
    }
    
    private boolean isSelectionValid() {
        if ((data.getAge() == 0 || data.getSex() == "-") && selection.getSelectedItem().equals("Advanced")) {
            JOptionPane.showMessageDialog(null, "Advanced caloric expenditure calculation cannot be done without an age AND sex value");
            return false;
        }
        
        JTextField calorieTargetField = (JTextField) calorieTargetPanel.getComponent(1);
        if (!calorieTargetField.getInputVerifier().verify(calorieTargetField)) {
            JOptionPane.showMessageDialog(null, "Invalid calorie target");
            return false;
        }
        
        return true;
    }
}
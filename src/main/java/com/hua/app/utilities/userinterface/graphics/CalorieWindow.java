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

import com.hua.app.utilities.calories.FormulaPicker;
import com.hua.app.utilities.userinterface.data.DataHolder;

public class CalorieWindow {
    private JFrame popupWindow;
    private Runnable switchCommand;
    private JComboBox<String> selection;
    private DataHolder data;
    
    public CalorieWindow(DataHolder data, Runnable switchCommand)  {
        this.data = data;
        this.switchCommand = switchCommand;
        create();
    }
    
    private void create() {
        popupWindow = new JFrame();
        popupWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //popupWindow.setMinimumSize(new Dimension(685, 285));
        popupWindow.setLocationRelativeTo(null);
        
        JPanel settingPanel = new JPanel();
        settingPanel.setLayout(new BoxLayout(settingPanel, BoxLayout.Y_AXIS));
        JLabel prompt = new JLabel("Select preferred formula for caloric expenditure calculation");
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        selection = new JComboBox<>();
        selection.addItem("Simple");
        selection.addItem("Advanced");
        selection.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton proceedButton = new JButton("Proceed");
        proceedButton.addActionListener(l -> {
            if (isSelectionValid() && switchCommand != null) {
                FormulaPicker.chooseFormula(data);
                switchCommand.run();
                popupWindow.dispose();
            }
        });
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        settingPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        settingPanel.add(prompt);
        settingPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        settingPanel.add(selection);
        settingPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        settingPanel.add(proceedButton);
        
        popupWindow.add(settingPanel);
        popupWindow.pack();
    }
    
    public boolean isSelectionValid() {
        if (data.getWeight() == 0.0) {
            JOptionPane.showMessageDialog(null, "Caloric expenditure will not be calculated because weight is 0");
        } else {
            if ((data.getAge() == 0 || data.getSex() == "-") && selection.getSelectedItem().equals("Advanced")) {
                JOptionPane.showMessageDialog(null, "Advanced caloric expenditure calculation cannot be done without an age AND sex value");
                return false;
            }
        }
        
        return true;
    }
    
    public void setVisibility(boolean value) {
        popupWindow.setVisible(value);
    }
}
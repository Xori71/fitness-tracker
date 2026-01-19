package com.hua.app.userinterface;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/* The class that creates the three input fields for age, weight, and sex. */

public class MiscellaneousInfoPicker {
    private JTextField ageField;
    private JComboBox<String> selector;
    private JTextField weightField;
    
    public JPanel createInfoPicker() {
        JPanel infoPanel = new JPanel(true);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        JPanel agePanel = InputFieldFactory.addField(infoPanel, "Enter your age (Optional): ", "", "^\\d*$", 15);
        JPanel weightPanel = InputFieldFactory.addField(infoPanel, "Enter your weight (Optional): ", "", "^\\d*(\\.\\d+)?$", 15);
        infoPanel.add(agePanel);
        infoPanel.add(weightPanel);
        infoPanel.add(setupSexInput());
        ageField = (JTextField) agePanel.getComponent(1);
        weightField = (JTextField) weightPanel.getComponent(1);
        
        return infoPanel;
    }
    
    public int getAgeInput() {
        if (!ageField.getText().equals("")) {
            return Integer.parseInt(ageField.getText());
        }
        return 0;
    }
    
    public double getWeightInput() {
        if (!ageField.getText().equals("")) {
            return Double.parseDouble(weightField.getText());
        }
        return 0.0;
    }
    
    public String getSexInput() {
        if (selector.getSelectedItem().equals("-")) {
            return null;
        } else {
            return (String) selector.getSelectedItem();
        }
    }
    
    public JTextField getAgeField() {
        return ageField;
    }
    
    public JTextField getWeightField() {
        return weightField;
    }
    
    public void refreshInfoPicker() {
        ageField.setText("");
        weightField.setText("");
        selector.setSelectedIndex(0);
    }
    
    private JComboBox<String> setupSexInput() {
        selector = new JComboBox<>();
        selector.addItem("-");
        selector.addItem("Male");
        selector.addItem("Female");
        return selector;
	}
}
package com.hua.app.utilities.userinterface.graphics;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
        return Integer.parseInt(ageField.getText());
    }
    
    public double getWeightInput() {
        return Double.parseDouble(weightField.getText());
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
    
    private JComboBox<String> setupSexInput() {
        selector = new JComboBox<>();
        selector.addItem("-");
        selector.addItem("Male");
        selector.addItem("Female");
        return selector;
	}
}
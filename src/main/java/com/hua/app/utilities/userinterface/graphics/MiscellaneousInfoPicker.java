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
        /* Base panel for all the info */
        JPanel infoPanel = new JPanel(true);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(InputFieldFactory.addField(infoPanel, "Enter your age (Optional): ", "e.g. 18", "^\\d$", 3));
        infoPanel.add(InputFieldFactory.addField(infoPanel, "Enter your weight (Optional): ", "e.g. 67.5", "^\\d(\\.\\d)?$", 5));
        infoPanel.add(setupSexInput());
        
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
    
    private JComboBox<String> setupSexInput() {
        selector = new JComboBox<>();
        selector.addItem("-");
        selector.addItem("Male");
        selector.addItem("Female");
        return selector;
	}
}
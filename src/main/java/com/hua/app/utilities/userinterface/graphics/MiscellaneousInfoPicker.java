package com.hua.app.utilities.userinterface.graphics;

import java.awt.Component;
import java.awt.FlowLayout;
import java.text.NumberFormat;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.NumberFormatter;

public class MiscellaneousInfoPicker {
    JPanel canvas;
    private JFormattedTextField ageField;
    private JComboBox<String> selector;
    private JFormattedTextField weightField;
    
    public MiscellaneousInfoPicker(JPanel canvas) {
        this.canvas = canvas;
    }
    
    public JPanel createInfoPicker() {
        /* Base panel for all the info */
        JPanel infoPanel = new JPanel(true);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(setupAgeInput());
        infoPanel.add(setupWeightInput());
        infoPanel.add(setupSexInput());
        
        return infoPanel;
    }
    
    public int getAgeInput() {
        return (int) ageField.getValue();
    }
    
    public double getWeightInput() {
        return (double) weightField.getValue();
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

	private JPanel setupWeightInput() {
	    JPanel weightPanel = new JPanel(new FlowLayout(), true);
        weightPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        NumberFormat weightFormat = NumberFormat.getNumberInstance();
        weightFormat.setMinimumFractionDigits(0);
        weightFormat.setMaximumFractionDigits(2);
        NumberFormatter weightFormatter = new NumberFormatter(weightFormat);
        weightFormatter.setValueClass(Double.class);
        weightFormatter.setMinimum(0.0);
        weightFormatter.setMaximum(600.0);
        weightFormatter.setAllowsInvalid(true);
        weightField = new JFormattedTextField(weightFormatter);
        weightField.setValue(0.0);
        weightField.setColumns(6);
        
        weightPanel.add(new JLabel("Enter your weight (optional): "));
        weightPanel.add(weightField);
        
        return weightPanel;
	}

	private JPanel setupAgeInput() {
        JPanel agePanel = new JPanel(new FlowLayout(), true);
        agePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        NumberFormat ageFormat = NumberFormat.getIntegerInstance();
        NumberFormatter ageFormatter = new NumberFormatter(ageFormat);
        ageFormatter.setValueClass(Integer.class);
        ageFormatter.setMinimum(0);
        ageFormatter.setMaximum(122);
        ageFormatter.setAllowsInvalid(true);
        ageField = new JFormattedTextField(ageFormatter);
        ageField.setValue(0);
        ageField.setColumns(3);
        ageField.setFocusLostBehavior(0);
        
        agePanel.add(new JLabel("Enter your age (optional): "));
        agePanel.add(ageField);
        
        return agePanel;
    }
}
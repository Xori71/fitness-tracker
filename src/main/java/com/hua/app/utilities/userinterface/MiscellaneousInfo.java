package com.hua.app.utilities.userinterface;

import java.awt.Component;
import java.awt.FlowLayout;
import java.text.NumberFormat;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.NumberFormatter;

public class MiscellaneousInfo {
    private static JFormattedTextField ageField;
    private static JComboBox<String> selector;
    private static JFormattedTextField weightField;
    
    public static JPanel instantiateInfoPicker(JPanel canvas) {
        /* Base panel for all the info */
        JPanel infoPanel = new JPanel(true);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(setupAgeInput());
        infoPanel.add(setupWeightInput());
        infoPanel.add(setupSexInput());
        
        return infoPanel;
    }
    
    public static int getAgeInput() {
        return (int) ageField.getValue();
    }
    
    public static double getWeightInput() {
        return (double) weightField.getValue();
    }
    
    public static String getSexInput() {
        return (String) selector.getSelectedItem();
    }
    
    private static JComboBox<String> setupSexInput() {
        selector = new JComboBox<>();
        selector.addItem("-");
        selector.addItem("Male");
        selector.addItem("Female");
        
        return selector;
	}

	private static Component setupWeightInput() {
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

	private static JPanel setupAgeInput() {
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
        
        agePanel.add(new JLabel("Enter your age (optional): "));
        agePanel.add(ageField);
        
        return agePanel;
    }
}
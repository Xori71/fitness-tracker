package com.hua.app.utilities.userinterface.graphics;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.hua.app.utilities.userinterface.data.DataHolder;

public class MenuPanel {
    private JPanel menuPanel;
    private MiscellaneousInfoPicker infoPicker;
	private Runnable switchToResults;
    private CalorieWindow calorieWindow;
    private DataHolder data;
    
    public MenuPanel(DataHolder data, Runnable switchToResults) {
        this.data = data;
        this.switchToResults = switchToResults;
        create();
    }
    
    public void create() {
        menuPanel = new JPanel(true);
        menuPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        FilePicker filePicker = new FilePicker(menuPanel, data);
        infoPicker = new MiscellaneousInfoPicker();
        
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        menuPanel.add(filePicker.createFilePicker(), c);
        c.gridx = 1;
        c.gridy = 0;
        menuPanel.add(infoPicker.createInfoPicker(), c);
        
        JButton customActivityButton = new JButton("Add custom activity");
        customActivityButton.addActionListener(l -> {
            SwingUtilities.invokeLater(() -> {
               @SuppressWarnings("unused")
			   CustomActivityWindow customActivityWindow = new CustomActivityWindow(data); 
            });
        });
        
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.NONE;
        menuPanel.add(customActivityButton, c);
        
        JButton proceedButton = new JButton("Proceed");
        proceedButton.addActionListener(l -> {
            if (isSelectionValid()) {
                data.setAge(infoPicker.getAgeInput());
                data.setWeight(infoPicker.getWeightInput());
                data.setSex(infoPicker.getSexInput());
                
                if (data.getWeight() != 0) {
                    SwingUtilities.invokeLater(() -> {
                        calorieWindow = new CalorieWindow(data, switchToResults, menuPanel);
                        calorieWindow.setVisibility(true);
                    });
                } else {
                    data.populateActivityList();
                    switchToResults.run();          
                }
            }
        });
        
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        menuPanel.add(proceedButton, c);
    }
    
    public boolean isSelectionValid() {
        JTextField ageField = infoPicker.getAgeField();
        JTextField weightField = infoPicker.getWeightField();
        
        if (ageField.getInputVerifier() != null && !ageField.getInputVerifier().verify(ageField)) {
            JOptionPane.showMessageDialog(null, "Invalid age value");
            return false;
        }
        if (weightField.getInputVerifier() != null && !weightField.getInputVerifier().verify(weightField)) {
            JOptionPane.showMessageDialog(null, "Invalid weight value");
            return false;
        }
        
        return true;
    }
    
    public JPanel getPanel() {
        return menuPanel;
    }
}
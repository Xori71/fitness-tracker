package com.hua.app.userinterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.hua.app.data.DataHolder;

/* The panel that represents the main menu. The file picker and miscellaneous info picker reside in it. */

public class MenuPanel {
    private JPanel menuPanel;
    private FilePicker filePicker;
    private MiscellaneousInfoPicker infoPicker;
	private Runnable switchToResults;
    private CalorieWindow calorieWindow;
    private CustomActivityWindow customActivityWindow;
    private DataHolder data;
    
    public MenuPanel(DataHolder data, Runnable switchToResults) {
        this.data = data;
        this.switchToResults = switchToResults;
        SwingUtilities.invokeLater(() -> {
            customActivityWindow = new CustomActivityWindow(data, menuPanel);
            customActivityWindow.hideWindow();
        });
        SwingUtilities.invokeLater(() -> {
            calorieWindow = new CalorieWindow(data, switchToResults, menuPanel);
            calorieWindow.hideWindow();
        });
    }
    
    public void create() {
        menuPanel = new JPanel(true);
        menuPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        filePicker = new FilePicker(data, menuPanel);
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
            customActivityWindow.refreshWindow();
            customActivityWindow.displayWindow();
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
                    calorieWindow.refreshWindow();
                    calorieWindow.displayWindow();
                } else {
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
            JOptionPane.showMessageDialog(menuPanel, "Invalid age value");
            return false;
        }
        if (weightField.getInputVerifier() != null && !weightField.getInputVerifier().verify(weightField)) {
            JOptionPane.showMessageDialog(menuPanel, "Invalid weight value");
            return false;
        }
        if (data.getActivityList().isEmpty()) {
            JOptionPane.showMessageDialog(menuPanel, "No files selected");
            return false;
        }
        
        return true;
    }
    
    public void refresh() {
        filePicker.refreshFilePicker();
        infoPicker.refreshInfoPicker();
    }
    
    public JPanel getPanel() {
        return menuPanel;
    }
}
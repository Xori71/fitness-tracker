package com.hua.app.utilities.userinterface.graphics;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.hua.app.utilities.userinterface.data.DataHolder;

public class MenuPanel {
    private JPanel menuPanel;
    @SuppressWarnings("unused")
	private Runnable switchCommand;
    private CalorieWindow calorieWindow;
    private DataHolder data;
    
    public MenuPanel(DataHolder data, Runnable switchCommand) {
        this.data = data;
        this.switchCommand = switchCommand;
        
        /* Create popup window to have it ready for use */
        SwingUtilities.invokeLater(() -> {
            calorieWindow = new CalorieWindow(data, switchCommand);
            calorieWindow.setVisibility(false);
        });
        
        create();
    }
    
    public void create() {
        menuPanel = new JPanel(true);
        menuPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        FilePicker filePicker = new FilePicker(menuPanel);
        MiscellaneousInfoPicker infoPicker = new MiscellaneousInfoPicker(menuPanel);
        
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        menuPanel.add(filePicker.createFilePicker(), c);
        c.gridx = 1;
        c.gridy = 0;
        menuPanel.add(infoPicker.createInfoPicker(), c);
        
        JButton proceedButton = new JButton("Proceed");
        proceedButton.addActionListener(l -> {
            data.setAge(infoPicker.getAgeInput());
            data.setWeight(infoPicker.getWeightInput());
            data.setSex(infoPicker.getSexInput());
            data.setFileList(filePicker.getFileList());
            
            calorieWindow.setVisibility(true);
        });
        
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        menuPanel.add(proceedButton, c);
    }
    
    public JPanel getPanel() {
        return menuPanel;
    }
}
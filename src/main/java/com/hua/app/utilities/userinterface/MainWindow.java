package com.hua.app.utilities.userinterface;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow {
    @SuppressWarnings("unused")
	private int age;
    @SuppressWarnings("unused")
	private double weight;
    @SuppressWarnings("unused")
	private String sex;
    @SuppressWarnings("unused")
	private Set<File> fileList;
    
    public MainWindow() {
        create();
    }
    
    private void create() {
        JFrame mainWindow = new JFrame("Fitness Tracker");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setMinimumSize(new Dimension(685, 285));
        mainWindow.setLocationRelativeTo(null);
        
        /* The panel hosting all the rest */
        JPanel canvas = new JPanel();
        canvas.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        mainWindow.add(canvas);
        
        FilePicker filePicker = new FilePicker(canvas);
        MiscellaneousInfoPicker infoPicker = new MiscellaneousInfoPicker(canvas);
        
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        canvas.add(filePicker.createFilePicker(), c);
        c.gridx = 1;
        c.gridy = 0;
        canvas.add(infoPicker.createInfoPicker(), c);
        
        JButton proceedButton = new JButton("Proceed");
        proceedButton.addActionListener(l -> {
            age = infoPicker.getAgeInput();
            weight = infoPicker.getWeightInput();
            sex = infoPicker.getSexInput();
            fileList = filePicker.getFileList();
            
            
        });
        
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        canvas.add(new JButton("Proceed"), c);
        
        mainWindow.pack();
        mainWindow.setVisible(true);
    }
}
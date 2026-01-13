package com.hua.app.utilities.userinterface.graphics;

import java.awt.Dimension;
import javax.swing.JFrame;

public class MainWindow {
    private JFrame mainWindow;
    
    public MainWindow() {
        create();
    }
    
    private void create() {
        mainWindow = new JFrame("Fitness Tracker");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setMinimumSize(new Dimension(685, 285));
        mainWindow.setLocationRelativeTo(null);
        
        BasePanel basePanel = new BasePanel();
        mainWindow.add(basePanel.getPanel());
        
        mainWindow.pack();
        mainWindow.setVisible(true);
    }
}
package com.hua.app.utilities.userinterface;

import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UserInterface {
    public static void createMainWindow() {
        JFrame mainWindow = new JFrame("Fitness Tracker");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(800, 600);
        // mainWindow.setMinimumSize();
        mainWindow.setLocationRelativeTo(null);
        /* Keep the window (the reference) in the EDT even after the method finishes */
        mainWindow.setVisible(true);
        
        /* The panel hosting all the rest */
        JPanel canvas = new JPanel();
        canvas.setLayout(new GridBagLayout());
        mainWindow.add(canvas);
        
        canvas.add(FilePicker.instantiateFilePicker(canvas));
        canvas.add(MiscellaneousInfo.instantiateInfoPicker(canvas));
    }
}
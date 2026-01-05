package com.hua.app.utilities.userinterface;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalorieSelectionWindow {
    private JFrame parentWindow;
    
    public CalorieSelectionWindow(JFrame parentWindow) {
        this.parentWindow = parentWindow;
        create();
    }
    
    public void create() {
        JFrame calorieWindow = new JFrame();
        calorieWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //mainWindow.setMinimumSize(new Dimension(685, 285));
        calorieWindow.setLocationRelativeTo(parentWindow);
        
        JPanel canvas = new JPanel();
        canvas.setLayout(new BoxLayout(canvas, BoxLayout.Y_AXIS));
        calorieWindow.add(canvas);
        calorieWindow.pack();
    }
}
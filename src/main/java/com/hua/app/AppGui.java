package com.hua.app;

import javax.swing.SwingUtilities;
import com.hua.app.utilities.userinterface.graphics.MainWindow;

public class AppGui { 
    public static void main(String args[]) {
        /* Ensure the GUI is created on the Event Dispatch Thread */
        SwingUtilities.invokeLater(() -> {
            @SuppressWarnings("unused")
			MainWindow mainWindow = new MainWindow();
        });
    }
}
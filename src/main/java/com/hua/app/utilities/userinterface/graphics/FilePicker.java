package com.hua.app.utilities.userinterface.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class FilePicker {
    private Set<File> fileList;
    private JPanel canvas;
    
    public FilePicker(JPanel canvas) {
        this.canvas = canvas;
        this.fileList = new HashSet<>();
    }
    
    public JScrollPane createFilePicker() {
        JPanel filePickerPanel = new JPanel(true);
        filePickerPanel.setLayout(new BoxLayout(filePickerPanel, BoxLayout.Y_AXIS));
        setupFilePicker(filePickerPanel);
                
        /* Make button column scrollable if it exceeds maximum height */
        JScrollPane scrollPanel = new JScrollPane(filePickerPanel);
        scrollPanel.getViewport().setBackground(Color.GRAY);
        scrollPanel.setPreferredSize(new Dimension(350, 150));
        scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        /* Remove ugly line border */
        //scrollPanel.setBorder(BorderFactory.createEmptyBorder());
        
        return scrollPanel;
    }
    
    public Set<File> getFileList() {
        return fileList;
    }
    
    private void setupFilePicker(JPanel filePickerPanel) {
        JButton button = new JButton("Choose a file");
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        /**
         * This variable is for checking whether a file-picking button has already been clicked at least
         * once. Since I allow selection editing, the recursive nature of the button creation means that,
         * without this state holder, each edit will create a new "Choose a file" button, even if another
         * already exists.
         * 
         * [Choose a file] -> [Chosen file] (Edit the selection) -> [New chosen file]
         *                    [Chose a file]                        [Choose a file]
         *                                                          [Choose a file]
         * 
         * AtomicBoolean might seem like overkill for this job, but since lambda expressions cannot modify variables
         * that are not 'final' (and I want a state holder in *each* button), the usage of a 'type' that creates a
         * new object with each iteration is convenient.
         */
        AtomicBoolean hasBeenClicked = new AtomicBoolean(false);
        
        button.addActionListener(l -> {
            /* The button being clicked */
            JButton source = (JButton) l.getSource();
            /* Hold the previous file in case of editing */
            File previousSelection = (File) source.getClientProperty("SelectedFile");
            
            JFileChooser filePicker = new JFileChooser();
            if (filePicker.showOpenDialog(canvas) == JFileChooser.APPROVE_OPTION) {
                File selection = filePicker.getSelectedFile();
                String filename = selection.getName();
                int dotIndex = filename.lastIndexOf('.');
                String extension = (dotIndex > 0) ? filename.substring(dotIndex + 1) : "";
                if (extension.equals("tcx")) {
                    if (!fileList.contains(selection)) {
                        if (hasBeenClicked.get() == true && previousSelection != null) {
                            /* Remove old file after editing selection */
                            fileList.remove(previousSelection);
                        }
                        fileList.add(selection);
                        /* Save new selection into button */
                        source.putClientProperty("SelectedFile", selection);
                        button.setText(filename);
                        if (hasBeenClicked.get() == false) {
                            addNewFileButton(filePickerPanel);
                            hasBeenClicked.set(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "You have already selected this file");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "This file format is unsupported");
                }
            }
        });
        
        /* Add space between buttons */
        filePickerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        filePickerPanel.add(button);
    }

	private void addNewFileButton(JPanel filePickerPanel) {
	    setupFilePicker(filePickerPanel);
		/* For dynamic updating */
		filePickerPanel.revalidate();
		filePickerPanel.repaint();
	}
}
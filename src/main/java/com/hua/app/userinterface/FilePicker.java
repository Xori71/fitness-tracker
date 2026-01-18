package com.hua.app.userinterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.hua.app.data.DataHolder;
import com.hua.app.data.XmlParser;

public class FilePicker {
    private JPanel parentPanel;
    private DataHolder data;
    private JPanel baseFilePickerPanel;
    private JScrollPane scrollableFilePicker;
    private XmlParser fileParser;
    
    public FilePicker(DataHolder data, JPanel parentPanel) {
        this.parentPanel = parentPanel;
        this.data = data;
        try {
            fileParser = new XmlParser();
        } catch (ParserConfigurationException e) {
            JOptionPane.showMessageDialog(parentPanel, "Parser could not be initialized. Exiting...", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
    public JScrollPane createFilePicker() {
        createFilePickerPanel();
        makeScrollableFilePicker();
        createFileButton();
        return scrollableFilePicker;
    }
    
    public void refreshFilePicker() {
        baseFilePickerPanel.removeAll();
        createFileButton();
        baseFilePickerPanel.revalidate();
		baseFilePickerPanel.repaint();
    }
    
    private void createFilePickerPanel() {
        baseFilePickerPanel = new JPanel(true);
        baseFilePickerPanel.setLayout(new BoxLayout(baseFilePickerPanel, BoxLayout.Y_AXIS));
    }
    
    /* Make button column scrollable if it exceeds maximum height */
    private void makeScrollableFilePicker() {
        scrollableFilePicker = new JScrollPane(baseFilePickerPanel);
        scrollableFilePicker.getViewport().setBackground(Color.GRAY);
        scrollableFilePicker.setPreferredSize(new Dimension(350, 150));
        scrollableFilePicker.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    }
    
    private void createFileButton() {
        JButton button = new JButton("Choose a file");
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        button.addActionListener(l -> {
            /* The button being clicked */
            JButton source = (JButton) l.getSource();
            /* Hold the previous file in case of editing */
            File previousSelection = (File) source.getClientProperty("SelectedFile");
            
            JFileChooser filePicker = new JFileChooser();
            if (filePicker.showOpenDialog(parentPanel) == JFileChooser.APPROVE_OPTION) {
                File selection = filePicker.getSelectedFile();
                String filename = selection.getName();
                int dotIndex = filename.lastIndexOf('.');
                String extension = (dotIndex > 0) ? filename.substring(dotIndex + 1) : "";
                if (extension.equals("tcx")) {
                    try {
                        fileParser.parseFile(selection, data.getActivityList());
                        
                        if (!data.getFileHistory().contains(selection)) {
                            /* Check if button has been clicked before */
                            if (previousSelection == null) {
                                 addNewFileButton();
                            } else {
                                /* Remove old file after editing selection */
                                data.getFileHistory().remove(previousSelection);
                            }
                            data.getFileHistory().add(selection);
                            /* Save new selection into button */
                            source.putClientProperty("SelectedFile", selection);
                            button.setText(filename);
                        } else {
                            JOptionPane.showMessageDialog(null, "You have already selected this file");
                        }
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(parentPanel, "Invalid file structure");
                    } catch (SAXException e) {
                        JOptionPane.showMessageDialog(parentPanel, "File is empty");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "This file format is unsupported");
                }
            }
        });
        
        baseFilePickerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        baseFilePickerPanel.add(button);
    }

	private void addNewFileButton() {
	    createFileButton();
		/* For dynamic updating */
		baseFilePickerPanel.revalidate();
		baseFilePickerPanel.repaint();
	}
}
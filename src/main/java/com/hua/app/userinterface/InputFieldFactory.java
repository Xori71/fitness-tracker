package com.hua.app.userinterface;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.hua.app.data.InputVerifierFactory;

public class InputFieldFactory {
    public static JPanel addField(JPanel targetPanel, String labelText, String placeholderText, String regex, int columns) {
        JPanel subpanel = new JPanel(true);
        subpanel.setLayout(new FlowLayout());
        
        JTextField field = new CustomTextField(placeholderText, columns);
        field.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        field.setInputVerifier(InputVerifierFactory.createInputVerifier(regex));
        subpanel.add(new JLabel(labelText));
        subpanel.add(field);
        return subpanel;
    }
}
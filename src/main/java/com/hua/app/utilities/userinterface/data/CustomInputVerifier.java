package com.hua.app.utilities.userinterface.data;

import java.awt.Color;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class CustomInputVerifier extends InputVerifier {
    String regex;
    
    public CustomInputVerifier(String regex) {
        this.regex = regex;
    }
    
    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();
        return text.matches(regex);
    }
    
    @Override
    public boolean shouldYieldFocus(JComponent source, JComponent target) {
        if (!verify(source)) {
            source.setBackground(Color.RED);
        } else {
            source.setBackground(Color.WHITE);
        }
        
        return true;
    }
}
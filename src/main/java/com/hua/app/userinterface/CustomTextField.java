package com.hua.app.userinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;

/**
 * I extend the JTextField class to add placeholder text to guide the user towards valid inputs for each field.
 * (Yes, some AI was used, for explanations and info gathering. Goddamn if I were gonna search through forums for this.
 * Don't hold it against me :3)
 */
 
public class CustomTextField extends JTextField {
    private String placeholderText;
    
    public CustomTextField(String placeholderText, int columns) {
        super(columns);
        this.placeholderText = placeholderText;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        /* Only draw the placeholder when the field is empty */
        if (getText().isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setColor(Color.GRAY);
            g2.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            int x = getInsets().left;
            /* Shamelessly copied from Gemini */
            int y = (getHeight() - g2.getFontMetrics().getHeight()) / 2 + g2.getFontMetrics().getAscent();
            g2.drawString(placeholderText, x, y);
            g2.dispose();
        }
    }   
}
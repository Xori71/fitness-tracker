package com.hua.app.userinterface;

import java.awt.CardLayout;
import javax.swing.JPanel;

import com.hua.app.data.DataHolder;

/**
 * This panel acts like a canvas. It houses the sections that handle the inputting of
 * info and the following display of it (Menu and Results respectively). It's basically
 * an invisible layout manager.
 */
 
public class BasePanel {
    JPanel basePanel;
    CardLayout c;
    
    /**
     * The screen switch should be handled by this class, but the confirmation is given by 
     * a class that's a few "levels" down (The popup window for caloric expenditure formula
     * selection, for example). Below is a type of listener that I propagate down until it 
     * reaches the class that calls for the switching.
     */
     
    MenuPanel menuPanel;
    ResultsPanel resultsPanel;
    private DataHolder data;
    
    public BasePanel() {
        data = new DataHolder();
        c = new CardLayout();
        create();
    }
    
    private void create() {
        basePanel = new JPanel(true);
        basePanel.setLayout(c);
        
        menuPanel = new MenuPanel(data, this::switchToResults);
        menuPanel.create();
        resultsPanel = new ResultsPanel(data, this::switchToMenu);
        resultsPanel.create();
        
        
        basePanel.add(menuPanel.getPanel(), "MENU");
        basePanel.add(resultsPanel.getPanel(), "RESULTS");
        
        switchToMenu();
    }
    
    private void switchToMenu() {
        menuPanel.refresh();
        data.clearAllData();
        c.show(basePanel, "MENU");
    }
    
    private void switchToResults() {
        data.populateActivityList();
        resultsPanel.updateDisplay();   
        c.show(basePanel, "RESULTS");
    }
    public JPanel getPanel() {
        return basePanel;
    }
}
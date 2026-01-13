package com.hua.app.utilities.userinterface.graphics;

import com.hua.app.utilities.userinterface.data.DataHolder;
import java.awt.CardLayout;
import javax.swing.JPanel;

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
     
    Runnable switchToMenu;
    Runnable switchToResults;
    MenuPanel menuPanel;
    ResultsPanel resultsPanel;
    private DataHolder data;
    
    public BasePanel() {
        data = new DataHolder();
        c = new CardLayout();
        switchToMenu = () -> c.show(basePanel, "MENU");
        switchToResults = () -> {
            resultsPanel.updateDisplay();   
            c.show(basePanel, "RESULTS");
        };
        create();
    }
    
    private void create() {
        basePanel = new JPanel(true);
        basePanel.setLayout(c);
        
        menuPanel = new MenuPanel(data, switchToResults);
        basePanel.add(menuPanel.getPanel(), "MENU");
        resultsPanel = new ResultsPanel(data, switchToMenu);
        basePanel.add(resultsPanel.getPanel(), "RESULTS");
        switchToMenu.run();
    }
    
    public JPanel getPanel() {
        return basePanel;
    }
}
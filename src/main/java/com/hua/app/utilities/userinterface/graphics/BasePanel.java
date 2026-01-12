/**
 * This panel acts like a canvas. It houses the sections that handle the inputting of
 * info and the following display of it (Menu and Results respectively). It's basically
 * an invisible layout manager.
 */

package com.hua.app.utilities.userinterface.graphics;

import com.hua.app.utilities.userinterface.data.DataHolder;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class BasePanel {
    JPanel basePanel;
    CardLayout c;
    private DataHolder data;
    
    public BasePanel() {
        data = new DataHolder();
        c = new CardLayout();
        create();
    }
    
    private void create() {
        basePanel = new JPanel(true);
        basePanel.setLayout(c);
        
        /**
         * The screen switch should be handled by this class, but the confirmation is given by 
         * a class that's a few "levels" down (The popup window for caloric expenditure formula
         * selection). Below is a type of listener that I propagate down until it reaches the
         * popup class, so it can be triggered once the popup gives the "okay".
         */
        Runnable switchCommand = () -> c.show(basePanel, "RESULTS");
        Runnable switchBackCommand = () -> c.show(basePanel, "MENU");
        
        MenuPanel menuPanel = new MenuPanel(data, switchCommand);
        basePanel.add(menuPanel.getPanel(), "MENU");
        ResultsPanel resultsPanel = new ResultsPanel(data, switchBackCommand);
        basePanel.add(resultsPanel.getInstance(), "RESULTS");
        
        /* TODO */
    }
    
    public JPanel getPanel() {
        return basePanel;
    }
}
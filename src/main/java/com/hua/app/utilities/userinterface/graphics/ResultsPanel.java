package com.hua.app.utilities.userinterface.graphics;

import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.hua.app.activityelements.Activity;
import com.hua.app.utilities.calories.CalorieCalcManager;
import com.hua.app.utilities.userinterface.data.DataHolder;

/** 
 * The panel responsible for the display of the calculations, as well as various other secondary data (Possible diviation
 * from daily calorie target, summation of various activity data)
 */

public class ResultsPanel {
    private JPanel baseResultsPanel;
    private DataHolder data;
    private CalorieCalcManager manager;
    JTextArea textArea;
    private Runnable switchToMenu;

    public ResultsPanel(DataHolder data, Runnable switchToMenu) {
        this.data = data;
        this.switchToMenu = switchToMenu;
        if (data.getFormula() != null) {
            manager = new CalorieCalcManager();
            manager.setFormula(data.getFormula());
        }
        create();
    }
    
    private void create() {
        baseResultsPanel = new JPanel(true);
        baseResultsPanel.setLayout(new BoxLayout(baseResultsPanel, BoxLayout.Y_AXIS));

        //baseResultsPanel.dothis();
        textArea = new JTextArea();
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setBorder(null);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane sp = new JScrollPane(textArea);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton proceedButton = new JButton("Proceed");
        proceedButton.setBounds(200,100,50,50);
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //proceedButton.setHorizontalTextPosition(JButton.CENTER);

        proceedButton.addActionListener(l -> {
            switchToMenu.run();

        });

       
        //proceedButton.setHorizontalTextPosition(JButton.CENTER);

        baseResultsPanel.add(sp);
        //baseResultsPanel.add(textArea);
        baseResultsPanel.add(proceedButton);
    }

    public void updateDisplay() {
        StringBuilder sb = new StringBuilder();
        //double value = 0;
        if (data.getActivityList() != null){
            for (Activity activity : data.getActivityList()) {
                activity.setManager(manager);
                activity.calculateData();
                
                if (data.getCalorieTarget() != -1 && activity.getCalories() != 0.0) {
                    data.recordDateAndCalories(activity.getDate(), activity.getCalories());
                    // sb.append("* Calories Expended: %.2f kcal\n" + value);
                }
                
                String formattedEntry = String.format(
                "* Activity: %s\n* Total Time: %s\n* Total Distance: %.2fm\n* Avg Speed: %.2f km/h\n* Avg Heart Rate: %s\n* Max Heart Rate: %s\n* Calories Burned: %.2f" +
                                "\n", 
                activity.getType(),            
                activity.getDuration(), 
                activity.getDistance(), 
                activity.getAverageSpeed(),
                activity.getAverageHeartRate(),
                activity.getMaxHeartRate(),
                activity.getCalories()
                );
                
                sb.append(formattedEntry);
                sb.append("--------------------------\n");
            }
        }
        textArea.setText(sb.toString());
        textArea.setCaretPosition(0);
        
    }

    public JPanel getPanel() {
        return baseResultsPanel;
    }
}
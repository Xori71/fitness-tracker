package com.hua.app.utilities.userinterface.graphics;

import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.hua.app.activityelements.Activity;
import com.hua.app.activityelements.CustomActivity;
import com.hua.app.utilities.calories.CalorieCalcManager;
import com.hua.app.utilities.userinterface.data.DataHolder;
public class ResultsPanel {
    /* TODO: This class should create a calorie calulation manager and pass it on to every activity via setters */
    
    private JPanel baseResultsPanel;
    private DataHolder data;
    private CalorieCalcManager manager;
    JTextArea textArea;
    private Runnable switchBackCommand;

    public ResultsPanel(DataHolder data, Runnable switchBackCommand) {
        this.data = data;
        this.switchBackCommand = switchBackCommand;
        manager = new CalorieCalcManager();
        manager.setStrategy(data.getFormula());
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
            switchBackCommand.run();

        });

       
        //proceedButton.setHorizontalTextPosition(JButton.CENTER);

        baseResultsPanel.add(sp);
        //baseResultsPanel.add(textArea);
        baseResultsPanel.add(proceedButton);
    }

    public void updateDisplay(){
        manager.setStrategy(data.getFormula());
        StringBuilder sb = new StringBuilder();
        double value = 0;
            if (data.getActivityList() != null){
                for (Activity activity : data.getActivityList()) {
                    
                    if (manager.formulaExists()) {
                        value = manager.calculate(activity);
                        if (value != -1) {
                           // sb.append("* Calories Expended: %.2f kcal\n" + value);
                        }
                    }
                    
                    String formattedEntry = String.format(
                    "* Activity: %s\n* Total Time: %s\n* Total Distance: %.2fm\n* Avg Speed: %.2f km/h\n* Avg Heart Rate: %s\n* Max Heart Rate: %s\n* Calories Burned: %.2f" +
                                    "\n", 
                    activity.getActivityType(),            
                    activity.getFormattedDuration(), 
                    activity.getDistance(), 
                    activity.getAverageSpeed(),
                    activity.getAverageHeartRate(),
                    activity.getMaxHeartRate(),
                    value
                    );
        
                    sb.append(formattedEntry);
                    sb.append("--------------------------\n");
                }
            }
            if (data.getCustomActivityList() != null){
                for (CustomActivity customActivity : data.getCustomActivityList()) {
                    String formattedEntry = String.format(
                    "* Activity: %s\n* Total Time: %s\n* Total Distance: %s\n* Avg Speed: %s km/h\n* Avg Heart Rate: %s\n* Max Heart Rate: %s\n" + //
                                    "\n", 
                    customActivity.getType(),            // Assuming this returns a String
                    customActivity.getDuration(), // Assuming this returns a String
                    customActivity.getDistance(), // Use parseDouble if getDistance() returns a String
                    customActivity.getAverageSpeed(),
                    customActivity.getAverageHeartRate(),
                    customActivity.getMaxHeartRate()
                    );
                    sb.append(formattedEntry);
                    sb.append("--------------------------\n");
                }
            }
        textArea.setText(sb.toString());
        textArea.setCaretPosition(0);
        
    }

    private JPanel createEntry(Activity activity) {
        JPanel entry = new JPanel(true);
        return entry;
    }
    
    private JPanel createEntry(CustomActivity customActivity) {
        JPanel entry = new JPanel(true);
        entry.setLayout(new BoxLayout(entry, BoxLayout.Y_AXIS));
        return entry;
    }

    public JPanel getInstance() {
        return baseResultsPanel;
    }
}
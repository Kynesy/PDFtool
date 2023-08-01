package view.listener;

import view.ExtractPanel;
import view.MainFrame;
import view.MergePanel;
import view.ToolSelectionPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener implements ActionListener {
    public enum ActionCMD {SHOW_EXTRACT, SHOW_MERGE, SHOW_HOME}
    private MainFrame mainFrame;

    public MyListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ActionCMD cmd = ActionCMD.valueOf(e.getActionCommand());
        System.out.println("Command: " + cmd);
        switch (cmd) {

            case SHOW_MERGE -> {
                mainFrame.setPanel(new MergePanel(this));
                break;
            }
            case SHOW_EXTRACT -> {
                mainFrame.setPanel(new ExtractPanel(this));
                break;
            }
            case SHOW_HOME -> {
                mainFrame.setPanel(new ToolSelectionPanel(this));
                break;
            }
        }
        mainFrame.invalidate();
        mainFrame.validate();
        mainFrame.repaint();
    }
}

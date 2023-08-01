package view;

import view.listener.MyListener;

import javax.swing.*;
import java.awt.*;

import static view.listener.MyListener.ActionCMD;


public class ToolSelectionPanel extends JPanel {
    public ToolSelectionPanel(MyListener listener) {
        this.setLayout(new GridLayout(2, 1));
        JButton button1 = new JButton("Extract PDF pages");
        button1.addActionListener(listener);
        button1.setActionCommand("" + ActionCMD.SHOW_EXTRACT);
        this.add(button1);

        JButton button2 = new JButton("Merge PDF");
        button2.addActionListener(listener);
        button2.setActionCommand("" + ActionCMD.SHOW_MERGE);
        this.add(button2);
    }
}

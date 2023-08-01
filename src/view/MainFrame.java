package view;

import view.listener.MyListener;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    JPanel actualPanel = null;

    public MainFrame() throws HeadlessException {
        // Set the title of the frame
        setTitle("My Frame");

        // Set the size of the frame
        setSize(400, 300);

        // Set the default close operation of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel selectionPanel = new ToolSelectionPanel(new MyListener(this));
        this.add(selectionPanel);
        actualPanel = selectionPanel;
        // Show the frame
        setVisible(true);
    }

    public void setPanel(JPanel newPanel){
        this.remove(actualPanel);
        actualPanel=newPanel;
        this.add(newPanel);

        this.invalidate();
        this.validate();
        this.repaint();
    }
}

package view;

import business.I_pdfChooser;
import business.I_pdfModifier;
import business.pdfChooser;
import business.pdfModifier_Apache;
import view.listener.MyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MergePanel extends JPanel {
    private File file1;
    private File file2;
    private JTextArea file1Path;
    private JTextArea file2Path;

    public MergePanel(MyListener myListener) {
        //file1
        //merge
        //home
        this.setLayout(new GridLayout(3, 1));

        //1111111111111111111
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 2));
        JButton fileSelection1Btn = new JButton("Select first file");
        fileSelection1Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                I_pdfChooser pdfChooser = new pdfChooser();
                file1 = pdfChooser.chooseFile();
                file1Path.setText(file1.getName());
            }
        });

        JButton fileSelection2Btn = new JButton("Select second file");
        fileSelection2Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                I_pdfChooser pdfChooser = new pdfChooser();
                file2 = pdfChooser.chooseFile();
                file2Path.setText(file2.getName());
            }
        });

        file1Path = new JTextArea("Nessun file selezionato");
        file2Path = new JTextArea("Nessun file selezionato");
        file1Path.setEnabled(false);
        file2Path.setEnabled(false);

        panel1.add(file1Path);
        panel1.add(fileSelection1Btn);
        panel1.add(file2Path);
        panel1.add(fileSelection2Btn);

        JPanel panel2 = new JPanel();
        JButton mergeBtn = new JButton("Merge!");
        mergeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "A file named \"output.pdf\" will be created in the Downloads directory.");
                I_pdfModifier pdfModifier = new pdfModifier_Apache();
                pdfModifier.mergePDFs(file1, file2);

                file1 = null;
                file2 = null;
                file1Path.setText("Nessun file selezionato");
                file2Path.setText("Nessun file selezionato");
            }
        });

        panel2.add(mergeBtn);

        //33333333333333333333333
        JPanel panel3 = new JPanel();
        JButton homeBtn = new JButton("Home");
        homeBtn.addActionListener(myListener);
        homeBtn.setActionCommand("" + MyListener.ActionCMD.SHOW_HOME);
        panel3.add(homeBtn);

        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
    }
}

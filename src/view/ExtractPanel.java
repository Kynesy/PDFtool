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

public class ExtractPanel extends JPanel {
    private File file;
    private JTextArea filePath;
    private JTextArea startPage;
    private JTextArea endPage;

    public ExtractPanel(MyListener myListener) {
        //selezione file
        //pagine
        //invio
        //home
        this.setLayout(new GridLayout(4, 1));

        //1111111111111111111
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 2));
        JButton fileSelectionBtn = new JButton("Select a PDF");
        fileSelectionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                I_pdfChooser pdfChooser = new pdfChooser();
                file = pdfChooser.chooseFile();
                filePath.setText(file.getName());
            }
        });

        filePath = new JTextArea("Nessun file selezionato");
        filePath.setEnabled(false);
        panel1.add(fileSelectionBtn);
        panel1.add(filePath);

        //2222222222222222222222222
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 4));

        startPage = new JTextArea();
        JLabel fromLbl = new JLabel("From page:");

        JLabel toLbl = new JLabel("To page:");
        endPage = new JTextArea();

        panel2.add(fromLbl);
        panel2.add(startPage);
        panel2.add(toLbl);
        panel2.add(endPage);

        //333333333333333333
        JPanel panel3 = new JPanel();
        JButton extractBtn = new JButton("Extract!");
        extractBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "A file named \"output.pdf\" will be created in the Downloads directory.");
                I_pdfModifier pdfModifier = new pdfModifier_Apache();
                pdfModifier.extractPages(file, Integer.parseInt(startPage.getText()), Integer.parseInt(endPage.getText()));

                file = null;
                filePath.setText("Nessun file selezionato.");
                startPage.setText("0");
                endPage.setText("0");
            }
        });
        panel3.add(extractBtn);

        //44444
        JPanel panel4 = new JPanel();
        JButton homeBtn = new JButton("Home");
        homeBtn.addActionListener(myListener);
        homeBtn.setActionCommand(""+ MyListener.ActionCMD.SHOW_HOME);
        panel4.add(homeBtn);

        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
    }
}

package business;

import business.I_pdfChooser;

import javax.swing.*;
import java.io.File;

public class pdfChooser implements I_pdfChooser {
    @Override
    public File chooseFile() {
        String startingPath = System.getProperty("user.home") + "\\Desktop";
        JFileChooser fileChooser = new JFileChooser(startingPath);

        int returnValue = fileChooser.showOpenDialog(new JFrame());


        File selectedFile = null;

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }

        return selectedFile;
    }
}

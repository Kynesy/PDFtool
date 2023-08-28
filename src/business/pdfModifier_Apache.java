package business;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class pdfModifier_Apache implements I_pdfModifier{
    private final String pathToSave = System.getProperty("user.home") + "/Downloads/";

    private int mergeList(List<File> fileList){
        PDFMergerUtility PDFmerger = new PDFMergerUtility();
        PDFmerger.setDestinationFileName(pathToSave + "output.pdf");
        try {
            for(File tmpFile : fileList){
                PDFmerger.addSource(tmpFile);
            }
            PDFmerger.mergeDocuments(null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Documents merged");

        return 0;
    }

    @Override
    public void extractPages(File file, int firstPage, int lastPage) {
        try{
            PDDocument document = PDDocument.load(file);

            //Instantiating Splitter class
            Splitter splitter = new Splitter();
            splitter.setStartPage(firstPage);
            splitter.setEndPage(lastPage);
            //splitting the pages of a PDF document
            List<PDDocument> Pages = splitter.split(document);

            //Creating an iterator
            Iterator<PDDocument> iterator = Pages.listIterator();

            //Saving each page as an individual document
            List<File> filesToMerge = new ArrayList<>();
            int i = 1;
            while(iterator.hasNext()) {
                PDDocument pd = iterator.next();
                String tmpFilename = pathToSave + "sample"+ i++ +".pdf";
                pd.save(tmpFilename);
                pd.close();
                filesToMerge.add(new File(tmpFilename));
            }
            System.out.println("Multiple PDF’s created");
            document.close();
            mergeList(filesToMerge);

            for(File tmpFile : filesToMerge){
                tmpFile.delete();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int mergePDFs(File file1, File file2) {
        List<File> fileList = new ArrayList<>();
        fileList.add(file1);
        fileList.add(file2);

        mergeList(fileList);

        return 0;
    }
}

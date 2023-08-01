package business;

import java.io.File;

public interface I_pdfModifier {
    void extractPages(File file, int firstPage, int lastPage);
    int mergePDFs(File file1, File file2);
}

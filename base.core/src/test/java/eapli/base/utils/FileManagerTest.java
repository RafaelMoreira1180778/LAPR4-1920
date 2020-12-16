package eapli.base.utils;

import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FileManagerTest {

    private final FileManager fm = new FileManager();
    private final ClassLoader cl = getClass().getClassLoader();

    private String getFilePathFromResources(String fName, String methodNAME) {
        String fPath = null;
        File f;
        try {
            f = Paths.get(cl.getResource(fName).toURI()).toFile();
            fPath = f.getAbsolutePath();
        } catch (URISyntaxException e) {
            System.out.println("Test file not found for method " + methodNAME);
        }
        return fPath;
    }

    @Test
    public void ensureImportFileFailsForNullPath() {
        assertNull(fm.importFile(null));
    }

    @Test
    public void ensureImportFileFailsForEmptyFile() {
        assertNull(fm.importFile(getFilePathFromResources("emptyFile.pdf", "ensureImportFileFailsForEmptyFile")));
    }

    @Test
    public void ensureImportFileImportsInformationCorrectly1() {
        String expected = "this is an example of a file to import";
        String actual = new String(fm.importFile(getFilePathFromResources("fileToImport.txt", "ensureImportFileImportsInformationCorrectly")), StandardCharsets.UTF_8);
        assertEquals("File wasn't loaded in the method ensureImportFileImportsInformationCorrectly1", expected, actual);
    }

    @Test
    public void ensureImportFileImportsInformationCorrectly2() {
        String expected = "this,is,an,exemple,of,another,file,to,import";
        String actual = new String(fm.importFile(getFilePathFromResources("fileToImport2.csv", "ensureImportFileImportsInformationCorrectly")), StandardCharsets.UTF_8);
        assertEquals("File wasn't loaded in the method ensureImportFileImportsInformationCorrectly2", expected, actual);
    }

}
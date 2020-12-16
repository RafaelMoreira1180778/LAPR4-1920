package eapli.base.app.scm;

import eapli.base.app.scm.application.FileImportHandler;
import eapli.base.app.scm.utils.Utils;

public class SCMFileImporter {

    public static void main(String[] args) {

        String inputPath = Utils.getPath("Input Directory:");
        String outPath = Utils.getPath("Output Directory");
        FileImportHandler f = new FileImportHandler(inputPath, outPath);
        f.run();

    }
}

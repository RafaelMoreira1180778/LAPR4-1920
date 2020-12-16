package eapli.base.app.scm.application;

import eapli.framework.validations.Preconditions;

import java.io.File;

public class FileImportHandler implements Runnable {

    private String directoryPath;
    private File folderWithMessages;
    private File[] filesToRead;
    private int numFilesToRead;
    private Thread[] threads;
    private String outPath;

    public FileImportHandler() {
    }

    public FileImportHandler(String directoryPath, String outPath) {
        Preconditions.nonEmpty(directoryPath, "Directory Path can't be null...");
        this.directoryPath = directoryPath;
        this.outPath = outPath;
    }

    @Override
    public void run() {
        this.folderWithMessages = new File(directoryPath);
        this.filesToRead = folderWithMessages.listFiles();
        this.numFilesToRead = filesToRead.length;
        if (numFilesToRead == 0) {
            System.out.println("No files to read");
            return;
        }
        this.threads = new Thread[numFilesToRead];
        readFiles();
        waitForThreadsToFinish();
        System.out.println("\nREAD ALL FILES\n");
    }

    private void readFiles() {
        for (int i = 0; i < this.numFilesToRead; i++) {
            Importer imp = new Importer();
            imp.setPath(this.filesToRead[i]);
            imp.setOutPath(this.outPath);
            threads[i] = new Thread(imp);
            threads[i].start();
        }
    }

    private void waitForThreadsToFinish() {
        for (int i = 0; i < this.numFilesToRead; i++) {
            try {
                threads[i].join();
                System.out.println("Thread[" + i + "] finished reading the file " + filesToRead[i].getName());
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}

package eapli.base.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public FileManager() {
    }

    public byte[] importFile(String fPath) {
        Path file;
        try {
            file = Paths.get(fPath);
        } catch (Exception e) {
            System.out.println("Path to file not correct or file doesn't exist");
            return null;
        }

        byte[] fData;
        try {
            fData = Files.readAllBytes(file);
        } catch (IOException e) {
            System.out.println("Error while reading the file... " + fPath);
            return null;
        }

        if (fData.length == 0) {
            System.out.println("File " + fPath + " is empty...");
            return null;
        }

        return fData;
    }

    public boolean exportFile(String fPath, byte[] info) {
        Path file = Paths.get(fPath);
        try {
            Files.write(file, info);
            return true;
        } catch (IOException e) {
            System.out.println("Export was unsuccessful: " + fPath + "\n" + e.getMessage());
            return false;
        }
    }
}

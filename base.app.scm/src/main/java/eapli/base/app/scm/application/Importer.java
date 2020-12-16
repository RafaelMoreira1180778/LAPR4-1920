package eapli.base.app.scm.application;

import eapli.base.app.scm.utils.Utils;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.messages.domain.Message;
import eapli.base.messages.repositories.MessagesRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class Importer implements Runnable {

    private File file;
    private MessagesRepository mr;
    private String outPath;

    @Override
    public void run() {

        mr = PersistenceContext.repositories().messagesManagement();

        for (String s : readFileLines()) {
            Message m = Utils.createMessages(s);
            if (m != null) mr.save(m);
        }

        moveProcessedFile();

        return;
    }

    public void setPath(File fileToImport) {
        this.file = fileToImport;
    }

    private List<String> readFileLines() {
        List<String> lines = new ArrayList<>();
        try {
            Path fPath = Paths.get(this.file.getPath());
            lines = Files.readAllLines(fPath);
        } catch (IOException e) {
            System.out.println("Couldn't read the lines of the file: " + this.file.getName());
        }
        return lines;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

    private void moveProcessedFile() {
        Path outPath = Paths.get(this.outPath);
        Path inPath = Paths.get(this.file.getPath());
        Path p = null;
        try {
            p = Files.move(inPath, outPath.resolve(this.file.getName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        boolean m = !Files.exists(p);
        if (!m) {
            System.out.println("Couldn't move the file: " + p);
            System.out.println("File " + p + " exists: " + m);
        }
    }
}

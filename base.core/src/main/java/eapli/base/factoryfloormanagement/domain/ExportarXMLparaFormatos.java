package eapli.base.factoryfloormanagement.domain;

import eapli.base.factoryfloormanagement.application.FileFormat;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileWriter;
import java.io.StringWriter;

public class ExportarXMLparaFormatos {

    /**
     * Info mostly taken from:
     * https://stackoverflow.com/questions/41895269/xml-to-html-using-xslt-input-and-output-strings-rather-than-files
     */
    public void generateOutFile(FileFormat f) {

        String format = f.toString().toLowerCase();

        String fOutName = String.format("ficheiros/ChaoDeFabrica.%s", format);
        String pathXSL = String.format("ficheiros/ChaoDeFabrica%s.xsl", format);

        String pathXML = "ficheiros/ChaoDeFabrica.xml";

        StringWriter sw = new StringWriter();
        try {
            FileWriter outputFileName = new FileWriter(fOutName);
            TransformerFactory tFactory = TransformerFactory.newInstance();

            Source xslDoc = new StreamSource(pathXSL);
            Source xmlDoc = new StreamSource(pathXML);
            Transformer trasform = tFactory.newTransformer(xslDoc);
            trasform.transform(xmlDoc, new StreamResult(sw));
            outputFileName.write(sw.toString());
            outputFileName.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

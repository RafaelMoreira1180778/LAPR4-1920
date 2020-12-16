package eapli.base.app.backoffice.console.presentation.chaofabrica;

import eapli.base.factoryfloormanagement.application.FileFormat;
import eapli.base.factoryfloormanagement.application.ExportarXMLparaFormatosController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ExportarXMLToFormatUI extends AbstractUI {

    private final ExportarXMLparaFormatosController controller = new ExportarXMLparaFormatosController();

    @Override
    protected boolean doShow() {

        List<FileFormat> formats = new ArrayList<>(Arrays.asList(FileFormat.values()));

        final SelectWidget<FileFormat> selector = new SelectWidget<>("Selecionar o tipo de formato para o qual quer exportar o XML:", formats);
        selector.show();
        final FileFormat ff = selector.selectedElement();

        if (ff == null) {
            return true;
        }

        this.controller.export(ff);
        return true;
    }

    @Override
    public String headline() {
        return "Generate new HTML";
    }
}

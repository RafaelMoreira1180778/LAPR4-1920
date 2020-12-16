package eapli.base.app.backoffice.console.presentation.ordemproducao;

import eapli.base.productionordermanagement.application.ImportarOrdensProducaoController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

public class ImportarOrdensProducaoUI extends AbstractUI {

    private final ImportarOrdensProducaoController controller = new ImportarOrdensProducaoController();

    @Override
    protected boolean doShow() {

        final String fileName = Console.readLine("Nome do ficheiro: ");

        try {
            this.controller.importCSV(fileName);
        } catch (Exception e) {

        }

        return true;
    }

    @Override
    public String headline() {
        return "Importar Ordens Producao";
    }
}

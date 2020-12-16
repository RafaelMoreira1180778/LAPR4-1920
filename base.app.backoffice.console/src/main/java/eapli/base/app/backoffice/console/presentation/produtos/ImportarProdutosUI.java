package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.base.productmanagement.application.ImportarProdutosController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

public class ImportarProdutosUI extends AbstractUI {

    private final ImportarProdutosController controller = new ImportarProdutosController();

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
        return "Importar Produtos";
    }
}

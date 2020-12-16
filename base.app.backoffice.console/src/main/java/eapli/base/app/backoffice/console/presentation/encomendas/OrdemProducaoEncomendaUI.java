package eapli.base.app.backoffice.console.presentation.encomendas;

import eapli.base.ordersmanagement.application.OrdemProducaoEncomendaController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

public class OrdemProducaoEncomendaUI extends AbstractUI {

    private final OrdemProducaoEncomendaController controller = new OrdemProducaoEncomendaController();

    @Override
    protected boolean doShow() {

        final String idEncomenda = Console.readLine("Introduza o ID da Encomenda que pretende consultar: ");

        try {
            controller.ordensProdEncomenda(idEncomenda);
        } catch (Exception e) {

        }

        return true;
    }

    @Override
    public String headline() {
        return "Consultar Produtos sem Ficha de Producao";
    }
}

package eapli.base.app.backoffice.console.presentation.ordemproducao;

import eapli.base.productionordermanagement.application.OrdemProducaoConsultaController;
import eapli.base.productionordermanagement.domain.Estado;
import eapli.base.productionordermanagement.domain.OrdemProducao;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.ArrayList;
import java.util.List;

public class OrdemProducaoConsultaUI extends AbstractUI {

    private final OrdemProducaoConsultaController controller = new OrdemProducaoConsultaController();

    private final String ESTADOS = "Filtrar por estados de ordem de producao";

    @Override
    protected boolean doShow() {

        List<String> optionsList = new ArrayList();
        optionsList.add(ESTADOS);

        final SelectWidget<String> selector = new SelectWidget<>("Selecione a opcao:", optionsList);
        selector.show();
        String selectedOption = selector.selectedElement();

        if (selectedOption.equals(ESTADOS)) {

            Iterable<Estado> ntList = controller.listAllEstados();
            final SelectWidget<Estado> selectorNT = new SelectWidget<>("Selecionar o estado da ordem de producao", ntList, new EstadoTypePrinter());
            selectorNT.show();
            Estado es = selectorNT.selectedElement();

            listDetails(this.controller.listOrdensEstado(es));
        }

        return true;
    }

    @Override
    public String headline() {
        return "Consultar Ordens de Producao num determinado estado";
    }

    private void listDetails(Iterable<OrdemProducao> l) {
        final SelectWidget<OrdemProducao> selectorDetails = new SelectWidget<>("Ordens de producao:", l);
        selectorDetails.show();
    }
}

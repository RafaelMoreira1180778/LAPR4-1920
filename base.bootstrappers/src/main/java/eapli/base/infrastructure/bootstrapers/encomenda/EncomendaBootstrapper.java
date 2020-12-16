package eapli.base.infrastructure.bootstrapers.encomenda;

import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.ordersmanagement.application.AdicionarEncomendaController;
import eapli.base.ordersmanagement.domain.ContentorOrdensProducao;
import eapli.base.productionordermanagement.domain.ContentorIdEncomendas;
import eapli.base.productionordermanagement.domain.OrdemProducao;
import eapli.base.productmanagement.domain.Quantidades;
import eapli.framework.actions.Action;

import java.util.ArrayList;
import java.util.Date;

public class EncomendaBootstrapper extends BaseBootstrapper implements Action {

    AdicionarEncomendaController controller = new AdicionarEncomendaController();
    ArrayList<String> ordens = new ArrayList<>();

    @Override
    public boolean execute() {
        adicionarEncomenda("E001", new ContentorOrdensProducao(ordens));
        adicionarEncomenda("E002", new ContentorOrdensProducao(ordens));
        adicionarEncomenda("E003", new ContentorOrdensProducao(ordens));
        return true;
    }

    private void adicionarEncomenda(String id, ContentorOrdensProducao op) {
        ordens.add("OP1");
        ordens.add("OP2");
        ordens.add("OP3");

        controller.adicionarEncomenda(id, op);
    }
}

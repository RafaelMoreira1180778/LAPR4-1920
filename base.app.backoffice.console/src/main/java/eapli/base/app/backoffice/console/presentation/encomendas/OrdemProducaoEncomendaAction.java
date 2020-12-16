package eapli.base.app.backoffice.console.presentation.encomendas;

import eapli.framework.actions.Action;

public class OrdemProducaoEncomendaAction implements Action {

    @Override
    public boolean execute() {
        return new OrdemProducaoEncomendaUI().show();
    }
}

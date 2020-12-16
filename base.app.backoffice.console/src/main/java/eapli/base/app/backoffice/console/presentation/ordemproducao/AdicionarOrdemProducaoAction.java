package eapli.base.app.backoffice.console.presentation.ordemproducao;

import eapli.framework.actions.Action;

public class AdicionarOrdemProducaoAction implements Action {

    @Override
    public boolean execute() {
        return new AdicionarOrdemProducaoUI().show();
    }
}

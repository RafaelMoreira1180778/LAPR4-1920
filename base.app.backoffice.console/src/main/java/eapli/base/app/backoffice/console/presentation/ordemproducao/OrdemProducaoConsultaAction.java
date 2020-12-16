package eapli.base.app.backoffice.console.presentation.ordemproducao;

import eapli.framework.actions.Action;

public class OrdemProducaoConsultaAction implements Action {
    @Override
    public boolean execute() {
        return new OrdemProducaoConsultaUI().show();
    }
}
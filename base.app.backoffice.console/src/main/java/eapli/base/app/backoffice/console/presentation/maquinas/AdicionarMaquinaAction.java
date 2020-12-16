package eapli.base.app.backoffice.console.presentation.maquinas;

import eapli.framework.actions.Action;

public class AdicionarMaquinaAction implements Action {
    @Override
    public boolean execute() {
        return new AdicionarMaquinaUI().show();
    }
}

package eapli.base.app.backoffice.console.presentation.materiaprima;

import eapli.framework.actions.Action;

public class AdicionarMateriaPrimaAction implements Action {
    @Override
    public boolean execute() {
        return new AdicionarMateriaPrimaUI().show();
    }
}

package eapli.base.app.backoffice.console.presentation.categoria;

import eapli.framework.actions.Action;

public class AdicionarCategoriaMateriaPrimaAction implements Action {

    @Override
    public boolean execute() {
        return new AdicionarCategoriaMateriaPrimaUI().show();
    }

}

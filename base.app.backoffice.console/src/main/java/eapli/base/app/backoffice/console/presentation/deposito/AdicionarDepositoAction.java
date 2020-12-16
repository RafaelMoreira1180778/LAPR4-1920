package eapli.base.app.backoffice.console.presentation.deposito;

import eapli.framework.actions.Action;

public class AdicionarDepositoAction implements Action {
    @Override
    public boolean execute() {
        return new AdicionarDepositoUI().show();
    }
}

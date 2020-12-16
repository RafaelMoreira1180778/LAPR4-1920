package eapli.base.app.backoffice.console.presentation.notificacoes;

import eapli.framework.actions.Action;

public class ListErrorNotificationArchAction implements Action {
    @Override
    public boolean execute() {
        return new ListErrorNotificationArchUI().show();
    }
}

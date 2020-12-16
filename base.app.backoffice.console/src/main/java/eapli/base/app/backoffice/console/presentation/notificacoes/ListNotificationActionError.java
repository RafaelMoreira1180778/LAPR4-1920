package eapli.base.app.backoffice.console.presentation.notificacoes;

import eapli.framework.actions.Action;

/**
 * @author Paulo Moreira 1180778
 */
public class ListNotificationActionError implements Action {
    @Override
    public boolean execute() {
        return new ListNotificationErrorUI().show();
    }
}

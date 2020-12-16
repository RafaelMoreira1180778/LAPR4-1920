package eapli.base.app.backoffice.console.presentation.chaofabrica;

import eapli.framework.actions.Action;

public class ExportarChaoFabricaXMLAction implements Action {

    @Override
    public boolean execute() {
        return new ExportarChaoFabricaXMLUI().show();
    }
}

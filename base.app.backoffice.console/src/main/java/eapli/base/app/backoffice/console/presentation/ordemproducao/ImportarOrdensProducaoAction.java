package eapli.base.app.backoffice.console.presentation.ordemproducao;

import eapli.base.app.backoffice.console.presentation.produtos.ImportarProdutosUI;
import eapli.framework.actions.Action;

public class ImportarOrdensProducaoAction implements Action {

    @Override
    public boolean execute() {
        return new ImportarOrdensProducaoUI().show();
    }
}

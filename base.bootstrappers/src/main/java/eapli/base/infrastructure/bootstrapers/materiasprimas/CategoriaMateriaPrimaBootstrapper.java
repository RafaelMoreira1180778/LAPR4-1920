package eapli.base.infrastructure.bootstrapers.materiasprimas;

import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.rawmaterialmanagement.application.AdicionarCategoriaMateriaPrimaController;
import eapli.framework.actions.Action;

public class CategoriaMateriaPrimaBootstrapper extends BaseBootstrapper implements Action {

    @Override
    public boolean execute() {
        adicionarCategoriaMateriaPrima("C1");
        adicionarCategoriaMateriaPrima("C2");
        adicionarCategoriaMateriaPrima("C3");
        return true;
    }

    private void adicionarCategoriaMateriaPrima(String idCat) {
        AdicionarCategoriaMateriaPrimaController cmp = new AdicionarCategoriaMateriaPrimaController();
        cmp.adicionarCategoriaMateriaPrima(idCat);
    }
}

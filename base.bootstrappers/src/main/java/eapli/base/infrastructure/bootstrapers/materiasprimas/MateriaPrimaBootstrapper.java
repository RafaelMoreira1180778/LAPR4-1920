package eapli.base.infrastructure.bootstrapers.materiasprimas;

import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.rawmaterialmanagement.application.AdicionarMateriaPrimaController;
import eapli.base.utils.Description;
import eapli.framework.actions.Action;

public class MateriaPrimaBootstrapper extends BaseBootstrapper implements Action {

    @Override
    public boolean execute() {
        adicionarMateriaPrima("MP1", new Description("DESC1"));
        adicionarMateriaPrima("MP2", new Description("DESC2"));
        adicionarMateriaPrima("MP3", new Description("DESC3"));
        return true;
    }

    private void adicionarMateriaPrima(String idP, Description descriptionP) {
        AdicionarMateriaPrimaController ampc = new AdicionarMateriaPrimaController();
        ampc.adicionarMateriaPrima(idP, descriptionP);
    }
}

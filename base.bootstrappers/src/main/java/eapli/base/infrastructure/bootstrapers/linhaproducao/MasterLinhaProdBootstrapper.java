package eapli.base.infrastructure.bootstrapers.linhaproducao;

import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.machinemanagement.application.AdicionarMaquinaController;
import eapli.base.machinemanagement.domain.Maquina;
import eapli.base.productionlinemanagement.domain.LinhaProducao;
import eapli.framework.actions.Action;

import java.util.LinkedList;

public class MasterLinhaProdBootstrapper extends BaseBootstrapper implements Action {
    @Override
    public boolean execute() {
        return associarMaLP();
    }

    private boolean associarMaLP() {
        AdicionarMaquinaController mController = new AdicionarMaquinaController();
        LinkedList<Maquina> lpl = new LinkedList<>();
        mController.listarMaquinas().iterator().forEachRemaining(lpl::add);

        for (LinhaProducao lp : mController.listarLinhasProd()) {
            mController.associarMaquinaLinhaProducao(lp, lpl.getFirst());
            lpl.removeFirst();
            System.out.println("Size of LM " + lp.identity() + " = " + lp.getListaMaquinas().size());
            if (lpl.size() == 0) {
                break;
            }
        }

        return true;
    }
}

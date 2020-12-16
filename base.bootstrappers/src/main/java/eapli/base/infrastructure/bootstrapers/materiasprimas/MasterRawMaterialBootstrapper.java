package eapli.base.infrastructure.bootstrapers.materiasprimas;

import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.rawmaterialmanagement.application.AdicionarMateriaPrimaController;
import eapli.base.rawmaterialmanagement.domain.CategoriaMateriaPrima;
import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.framework.actions.Action;

import java.util.LinkedList;

public class MasterRawMaterialBootstrapper extends BaseBootstrapper implements Action {
    @Override
    public boolean execute() {
        return associarMPaCAT();
    }

    private boolean associarMPaCAT() {
        AdicionarMateriaPrimaController mpcontroller = new AdicionarMateriaPrimaController();
        LinkedList<RawMaterial> mpl = new LinkedList<>();
        mpcontroller.listarTodasMP().iterator().forEachRemaining(mpl::add);

        for (CategoriaMateriaPrima cmp : mpcontroller.listarCategorias()) {
            mpcontroller.adicionarMPaCAT(cmp, mpl.getFirst());
            mpl.removeFirst();
            System.out.println("Size of CMP " + cmp.identity() + " = " + cmp.getListaMateriasPrimas().size());
            if (mpl.size() == 0) {
                break;
            }
        }

        return true;
    }
}

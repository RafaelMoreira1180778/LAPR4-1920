package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.framework.visitor.Visitor;

class MateriasPrimasPrinter implements Visitor<RawMaterial> {

    @Override
    public void visit(RawMaterial visitee) {
        System.out.println(visitee.identity());
    }
}

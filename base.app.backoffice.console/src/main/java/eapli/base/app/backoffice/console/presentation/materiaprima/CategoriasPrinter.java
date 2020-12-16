package eapli.base.app.backoffice.console.presentation.materiaprima;

import eapli.base.rawmaterialmanagement.domain.CategoriaMateriaPrima;
import eapli.framework.visitor.Visitor;

public class CategoriasPrinter implements Visitor<CategoriaMateriaPrima> {

    @Override
    public void visit(CategoriaMateriaPrima visitee) {
        System.out.println(visitee.identity());
    }
}

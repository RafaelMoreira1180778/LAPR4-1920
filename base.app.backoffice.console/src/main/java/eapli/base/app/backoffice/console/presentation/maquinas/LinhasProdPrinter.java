package eapli.base.app.backoffice.console.presentation.maquinas;

import eapli.base.productionlinemanagement.domain.LinhaProducao;
import eapli.framework.visitor.Visitor;

public class LinhasProdPrinter implements Visitor<LinhaProducao> {

    @Override
    public void visit(LinhaProducao visitee) {
        System.out.printf(visitee.identity());
    }
}

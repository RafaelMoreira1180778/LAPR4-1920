package eapli.base.app.backoffice.console.presentation.ordemproducao;

import eapli.base.productionordermanagement.domain.Estado;
import eapli.framework.visitor.Visitor;

public class EstadoTypePrinter implements Visitor<Estado> {
    @Override
    public void visit(Estado visitee) {
        System.out.println(visitee.toString());
    }
}

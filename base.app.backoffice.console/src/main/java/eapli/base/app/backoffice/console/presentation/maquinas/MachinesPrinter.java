package eapli.base.app.backoffice.console.presentation.maquinas;

import eapli.base.machinemanagement.domain.Maquina;
import eapli.framework.visitor.Visitor;

public class MachinesPrinter implements Visitor<Maquina> {

    @Override
    public void visit(Maquina visitee) {
        System.out.println(visitee.identity());
    }
}

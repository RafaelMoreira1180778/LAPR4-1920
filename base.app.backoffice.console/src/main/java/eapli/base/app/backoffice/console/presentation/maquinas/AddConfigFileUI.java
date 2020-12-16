package eapli.base.app.backoffice.console.presentation.maquinas;

import eapli.base.machinemanagement.application.AddConfigFileController;
import eapli.base.machinemanagement.domain.Maquina;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

public class AddConfigFileUI extends AbstractUI {

    private final AddConfigFileController cfc = new AddConfigFileController();

    @Override
    protected boolean doShow() {

        Iterable<Maquina> lplist = cfc.listAllMachines();
        final SelectWidget<Maquina> selector = new SelectWidget<>("Selecionar máquina a que pretende associar ficheiro de configuração:", lplist, new MachinesPrinter());
        selector.show();
        final Maquina m = selector.selectedElement();

        final String fPath = Console.readLine("Path do ficheiro de configuracao pretendido:");

        if (this.cfc.addConfigFileToMachine(fPath, m)) {
            System.out.println("Adicionou o ficheiro " + fPath + " com sucesso a maquina com ID: " + m.identity());
            return true;
        } else {
            System.out.println("Nao foi possivel adicionar o ficheiro a maquina com ID: " + m.identity());
            return false;
        }

    }

    @Override
    public String headline() {
        return "Add Config File";
    }
}

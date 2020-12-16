package eapli.base.app.backoffice.console.presentation.deposito;

import eapli.base.depositsmanagement.application.AdicionarDepositosController;
import eapli.base.depositsmanagement.domain.Descricao_Deposito;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

public class AdicionarDepositoUI extends AbstractUI {

    private final AdicionarDepositosController controller = new AdicionarDepositosController();

    @Override
    protected boolean doShow() {

        final String id_Deposito = Console.readLine("ID do Depósito: ");
        final String descricao = Console.readLine("Descricao do Depósito: ");

        try {
            this.controller.adicionarDepositos(id_Deposito, new Descricao_Deposito(descricao));
            System.out.println("O Depósito de ID " + id_Deposito + " foi adicionado");
        } catch (Exception e) {
        }
        return true;
    }

    @Override
    public String headline() {
        return "Adicionar Deposito";
    }
}

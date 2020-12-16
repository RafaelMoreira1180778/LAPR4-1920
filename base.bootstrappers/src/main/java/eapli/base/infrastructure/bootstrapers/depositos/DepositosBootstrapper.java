package eapli.base.infrastructure.bootstrapers.depositos;

import eapli.base.depositsmanagement.application.AdicionarDepositosController;
import eapli.base.depositsmanagement.domain.Descricao_Deposito;
import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.framework.actions.Action;

public class DepositosBootstrapper extends BaseBootstrapper implements Action {

    @Override
    public boolean execute() {
        adicionarDepositos("D1", new Descricao_Deposito("desc1"));
        adicionarDepositos("D2", new Descricao_Deposito("desc1"));
        adicionarDepositos("D3", new Descricao_Deposito("desc1"));
        return true;
    }

    private void adicionarDepositos(String idD, Descricao_Deposito descricao) {
        AdicionarDepositosController adc = new AdicionarDepositosController();
        adc.adicionarDepositos(idD, descricao);
    }
}

package eapli.base.infrastructure.bootstrapers.linhaproducao;

import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.productionlinemanagement.application.AdicionarLinhaProducaoController;
import eapli.base.productionlinemanagement.domain.EstadoProcessamento;
import eapli.framework.actions.Action;

import java.util.Date;

public class LinhaProducaoBootstrapper extends BaseBootstrapper implements Action {

    @Override
    public boolean execute() {
        adicionarLinhaProducao("LP1", EstadoProcessamento.DESATIVO, new Date(2020 - 1900, 4 - 1, 5, 18, 20));
        adicionarLinhaProducao("LP2", EstadoProcessamento.DESATIVO, new Date(2019 - 1900, 5 - 1, 10, 19, 30));
        adicionarLinhaProducao("LP3", EstadoProcessamento.DESATIVO, new Date(2018 - 1900, 6 - 1, 15, 20, 40));
        return true;
    }

    private void adicionarLinhaProducao(String idLP, EstadoProcessamento ep, Date uo) {
        AdicionarLinhaProducaoController alpc = new AdicionarLinhaProducaoController();
        alpc.adicionarLinhaProducao(idLP, ep, uo);
    }

}

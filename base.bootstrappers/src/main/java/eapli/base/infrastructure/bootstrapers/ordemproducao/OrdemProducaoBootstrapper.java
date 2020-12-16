package eapli.base.infrastructure.bootstrapers.ordemproducao;

import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.productionordermanagement.application.AdicionarOrdemProducaoController;
import eapli.base.productionordermanagement.domain.ContentorIdEncomendas;
import eapli.base.productmanagement.domain.Quantidades;
import eapli.framework.actions.Action;

import java.util.Date;

public class OrdemProducaoBootstrapper extends BaseBootstrapper implements Action {

    @Override
    public boolean execute() {
        adicionarOrdemProducao("OP1", new Date(2020, 2, 18), new Date(2020, 2, 20), "PD1", 1200, new Quantidades("UN"), new ContentorIdEncomendas());
        adicionarOrdemProducao("OP2", new Date(2020, 2, 18), new Date(2020, 2, 20), "PD2", 6000, new Quantidades("UN"), new ContentorIdEncomendas());
        adicionarOrdemProducao("OP3", new Date(2020, 2, 18), new Date(2020, 2, 20), "PD3", 500, new Quantidades("UN"), new ContentorIdEncomendas());
        return true;
    }

    private void adicionarOrdemProducao(String id, Date dataEmissao, Date previsaoExecucao, String idProduto, int quant, Quantidades uni, ContentorIdEncomendas idEnc) {
        AdicionarOrdemProducaoController controller = new AdicionarOrdemProducaoController();
        controller.adicionarOrdemProducao(id, dataEmissao, previsaoExecucao, idProduto, quant, uni, idEnc);
    }
}

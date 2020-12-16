package eapli.base.productionordermanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productionordermanagement.domain.ContentorIdEncomendas;
import eapli.base.productionordermanagement.domain.OrdemProducao;
import eapli.base.productionordermanagement.repositories.OrdemProducaoRepository;
import eapli.base.productmanagement.domain.Quantidades;
import eapli.framework.application.UseCaseController;

import java.util.Date;

@UseCaseController
public class AdicionarOrdemProducaoController {

    final OrdemProducaoRepository opRepo = PersistenceContext.repositories().ordemProducaoManagement();

    public void adicionarOrdemProducao (String id, Date dataEmissao, Date previsaoExecucao, String idProduto, int quant, Quantidades uni, ContentorIdEncomendas idEnc) {
        final OrdemProducao op = new OrdemProducao(id, dataEmissao, previsaoExecucao, idProduto, quant, uni, idEnc);
        this.opRepo.save(op);
    }
}

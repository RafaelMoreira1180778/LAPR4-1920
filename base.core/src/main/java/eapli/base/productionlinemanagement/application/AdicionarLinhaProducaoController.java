package eapli.base.productionlinemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productionlinemanagement.domain.EstadoProcessamento;
import eapli.base.productionlinemanagement.domain.LinhaProducao;
import eapli.base.productionlinemanagement.repositories.LinhaProducaoRepository;
import eapli.framework.application.UseCaseController;

import java.util.Date;

@UseCaseController
public class AdicionarLinhaProducaoController {

    private final LinhaProducaoRepository linhaProducaoRepository = PersistenceContext.repositories().linhaProducaoManagement();

    public void adicionarLinhaProducao(String idLP, EstadoProcessamento est, Date uo) {
        final LinhaProducao lp = new LinhaProducao(idLP, est, uo);
        this.linhaProducaoRepository.save(lp);
    }
}

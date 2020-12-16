package eapli.base.ordersmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.domain.ContentorOrdensProducao;
import eapli.base.ordersmanagement.domain.Encomenda;
import eapli.base.ordersmanagement.repositories.EncomendaRepository;
import eapli.framework.application.UseCaseController;

@UseCaseController
public class AdicionarEncomendaController {

    final EncomendaRepository encRepo = PersistenceContext.repositories().encomendaManagement();

    public void adicionarEncomenda(String id, ContentorOrdensProducao op) {
        final Encomenda enc = new Encomenda(id, op);
        encRepo.save(enc);
    }
}

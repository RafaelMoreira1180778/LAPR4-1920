package eapli.base.ordersmanagement.repositories;

import eapli.base.ordersmanagement.domain.ContentorOrdensProducao;
import eapli.base.ordersmanagement.domain.Encomenda;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface EncomendaRepository extends DomainRepository<String, Encomenda> {

    ContentorOrdensProducao listOrdensProducao(String idEncomenda);

}

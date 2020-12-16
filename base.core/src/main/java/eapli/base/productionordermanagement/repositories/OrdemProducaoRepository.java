package eapli.base.productionordermanagement.repositories;

import eapli.base.productionordermanagement.domain.Estado;
import eapli.base.productionordermanagement.domain.OrdemProducao;
import eapli.framework.domain.repositories.DomainRepository;

import java.io.IOException;
import java.util.List;

public interface OrdemProducaoRepository extends DomainRepository<String, OrdemProducao> {

    void loadOrdensProducao(String fileName) throws IOException;

    List<OrdemProducao> listByEstado(Estado estado);
}

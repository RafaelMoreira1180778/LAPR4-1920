package eapli.base.productmanagement.repositories;

import eapli.base.productmanagement.domain.FichaProducao;
import eapli.framework.domain.repositories.DomainRepository;

public interface FichaProducaoRepository extends DomainRepository<String, FichaProducao> {

    FichaProducao findByIDf(String idf);
}

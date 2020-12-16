package eapli.base.productionlinemanagement.repositories;

import eapli.base.productionlinemanagement.domain.EstadoProcessamento;
import eapli.base.productionlinemanagement.domain.LinhaProducao;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Date;

public interface LinhaProducaoRepository extends DomainRepository<String, LinhaProducao> {

    LinhaProducao findByID(String id_lp);

    EstadoProcessamento currentStatus(String id_lp);

    Date ultimaOcorrencia(String idLP);

}

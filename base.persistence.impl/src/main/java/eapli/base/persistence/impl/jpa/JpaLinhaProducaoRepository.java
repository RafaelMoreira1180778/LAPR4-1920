package eapli.base.persistence.impl.jpa;

import eapli.base.productionlinemanagement.domain.EstadoProcessamento;
import eapli.base.productionlinemanagement.domain.LinhaProducao;
import eapli.base.productionlinemanagement.repositories.LinhaProducaoRepository;

import javax.persistence.TypedQuery;
import java.util.Date;

public class JpaLinhaProducaoRepository extends BasepaRepositoryBase<LinhaProducao, String, String> implements LinhaProducaoRepository {

    public JpaLinhaProducaoRepository(String puname) {
        super(puname, "code");
    }

    @Override
    public LinhaProducao findByID(String id_lp) {
        TypedQuery<LinhaProducao> query = createQuery("SELECT lp FROM LinhaProducao lp WHERE lp.id_LinhaProducao = :id_lp", LinhaProducao.class);
        return query.setParameter("id_lp", id_lp).getSingleResult();
    }

    @Override
    public EstadoProcessamento currentStatus(String id_lp) {
        TypedQuery<EstadoProcessamento> query = createQuery("SELECT lp.estadoProcessamento FROM LinhaProducao lp WHERE lp.id_LinhaProducao = :id_lp", EstadoProcessamento.class);
        return query.setParameter("id_lp", id_lp).getSingleResult();
    }

    @Override
    public Date ultimaOcorrencia(String id_lp) {
        TypedQuery<Date> query = createQuery("SELECT lp.ultimaOcorrencia FROM LinhaProducao lp WHERE lp.id_LinhaProducao = :id_lp", Date.class);
        System.out.println("passamos a query");
        return query.setParameter("id_lp", id_lp).getSingleResult();
    }

}

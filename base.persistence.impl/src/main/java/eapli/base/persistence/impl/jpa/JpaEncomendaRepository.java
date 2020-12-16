package eapli.base.persistence.impl.jpa;

import eapli.base.ordersmanagement.domain.ContentorOrdensProducao;
import eapli.base.ordersmanagement.domain.Encomenda;
import eapli.base.ordersmanagement.repositories.EncomendaRepository;

import javax.persistence.TypedQuery;

public class JpaEncomendaRepository extends BasepaRepositoryBase<Encomenda, String, String> implements EncomendaRepository {

    public JpaEncomendaRepository(String puname) {
        super(puname, "code");
    }

    @Override
    public ContentorOrdensProducao listOrdensProducao(String idEncomenda) {
        TypedQuery<ContentorOrdensProducao> query = createQuery("SELECT e.ordens FROM encomenda e WHERE e.idencomenda = :idEncomenda", ContentorOrdensProducao.class);
        return query.setParameter("idEncomenda", idEncomenda).getSingleResult();
    }
}

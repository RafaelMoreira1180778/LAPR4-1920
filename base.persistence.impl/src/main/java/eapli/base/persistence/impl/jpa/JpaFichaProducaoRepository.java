package eapli.base.persistence.impl.jpa;

import eapli.base.productmanagement.domain.FichaProducao;
import eapli.base.productmanagement.repositories.FichaProducaoRepository;


public class JpaFichaProducaoRepository extends BasepaRepositoryBase<FichaProducao, String, String> implements FichaProducaoRepository {

    public JpaFichaProducaoRepository(String puname) {
        super(puname, "code");
    }

    @Override
    public FichaProducao findByIDf(String idf) {
        return null;
    }
}

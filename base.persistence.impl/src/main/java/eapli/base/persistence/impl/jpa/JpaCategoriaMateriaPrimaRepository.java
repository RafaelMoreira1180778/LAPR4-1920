package eapli.base.persistence.impl.jpa;


import eapli.base.Application;
import eapli.base.rawmaterialmanagement.domain.CategoriaMateriaPrima;
import eapli.base.rawmaterialmanagement.repositories.CategoriaMateriaPrimaRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Optional;

public class JpaCategoriaMateriaPrimaRepository extends JpaAutoTxRepository<CategoriaMateriaPrima, String, String> implements CategoriaMateriaPrimaRepository {

    public JpaCategoriaMateriaPrimaRepository(TransactionalContext autoTx) {
        super(autoTx, "id_CategoriaMateriaPrima");
    }

    public JpaCategoriaMateriaPrimaRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id_CategoriaMateriaPrima");
    }

    @Override
    public Optional<CategoriaMateriaPrima> findByID(String id_cmp) {
        return matchOne("e.id_CategoriaMateriaPrima=id_cmp");
    }
}
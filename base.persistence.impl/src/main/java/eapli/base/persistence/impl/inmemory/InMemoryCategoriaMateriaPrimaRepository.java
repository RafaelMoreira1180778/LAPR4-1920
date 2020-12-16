package eapli.base.persistence.impl.inmemory;

import eapli.base.rawmaterialmanagement.domain.CategoriaMateriaPrima;
import eapli.base.rawmaterialmanagement.repositories.CategoriaMateriaPrimaRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import java.util.Optional;


public class InMemoryCategoriaMateriaPrimaRepository extends InMemoryDomainRepository<String, CategoriaMateriaPrima> implements CategoriaMateriaPrimaRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<CategoriaMateriaPrima> findByID(String id_cmp) {
        return matchOne(e -> e.getId().equals(id_cmp));
    }
}
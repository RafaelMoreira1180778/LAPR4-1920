package eapli.base.persistence.impl.inmemory;

import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.base.rawmaterialmanagement.repositories.MateriaPrimaRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryRawMaterialRepository extends InMemoryDomainRepository<String, RawMaterial>
        implements MateriaPrimaRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<RawMaterial> findByID(String id_mp) {
        return matchOne(e -> e.getId().equals(id_mp));
    }
}

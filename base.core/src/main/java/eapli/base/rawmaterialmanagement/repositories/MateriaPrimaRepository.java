package eapli.base.rawmaterialmanagement.repositories;

import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface MateriaPrimaRepository extends DomainRepository<String, RawMaterial> {

    Optional<RawMaterial> findByID(String id_mp);
}

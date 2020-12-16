package eapli.base.rawmaterialmanagement.repositories;

import eapli.base.rawmaterialmanagement.domain.CategoriaMateriaPrima;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface CategoriaMateriaPrimaRepository extends DomainRepository<String, CategoriaMateriaPrima> {

    Optional<CategoriaMateriaPrima> findByID(String id_cmp);
}
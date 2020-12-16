package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.base.rawmaterialmanagement.repositories.MateriaPrimaRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Optional;

public class JpaMateriaPrimaRepository extends JpaAutoTxRepository<RawMaterial, String, String> implements MateriaPrimaRepository {

    public JpaMateriaPrimaRepository(TransactionalContext autoTx) {
        super(autoTx, "id_MateriaPrima");
    }

    public JpaMateriaPrimaRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id_MateriaPrima");
    }

    @Override
    public Optional<RawMaterial> findByID(String id_mp) {
        return matchOne("e.id_MateriaPrima=id_mp");
    }

}

package eapli.base.persistence.impl.inmemory;

import eapli.base.machinemanagement.domain.Maquina;
import eapli.base.machinemanagement.repositories.MachineRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryMachineRepository extends InMemoryDomainRepository<String, Maquina>
        implements MachineRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<Maquina> findByID(String id_m) {
        return matchOne(e -> e.getId().equals(id_m));
    }

    @Override
    public void setMachineIP(String IP, String machineID) {
    }
}

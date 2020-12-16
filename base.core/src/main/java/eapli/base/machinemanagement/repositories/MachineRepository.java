package eapli.base.machinemanagement.repositories;

import eapli.base.machinemanagement.domain.Maquina;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface MachineRepository extends DomainRepository<String, Maquina> {
    Optional<Maquina> findByID(String id_m);

    void setMachineIP(String IP, String machineID);
}

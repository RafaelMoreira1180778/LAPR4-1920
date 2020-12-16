package eapli.base.depositsmanagement.repositories;

import eapli.base.depositsmanagement.domain.Depositos;
import eapli.base.machinemanagement.domain.Maquina;
import eapli.framework.domain.repositories.DomainRepository;

public interface DepositosRepository extends DomainRepository<String, Depositos> {
    Depositos findByID(String id_d);
}

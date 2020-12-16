package eapli.base.persistence.impl.jpa;


import eapli.base.depositsmanagement.domain.Depositos;
import eapli.base.depositsmanagement.repositories.DepositosRepository;

public class JpaDepositosRepository extends BasepaRepositoryBase<Depositos, String, String> implements DepositosRepository {

    public JpaDepositosRepository(String puname) {
        super(puname, "code");
    }

    @Override
    public Depositos findByID(String id_d) {
        return null;
    }
}

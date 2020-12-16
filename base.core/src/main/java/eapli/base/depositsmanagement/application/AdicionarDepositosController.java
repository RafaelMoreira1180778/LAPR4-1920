package eapli.base.depositsmanagement.application;

import eapli.base.depositsmanagement.domain.Depositos;
import eapli.base.depositsmanagement.domain.Descricao_Deposito;
import eapli.base.depositsmanagement.repositories.DepositosRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;

@UseCaseController
public class AdicionarDepositosController {

    private final DepositosRepository depositosRepository = PersistenceContext.repositories().depositoManagement();

    public void adicionarDepositos(String idD, Descricao_Deposito descricaoD) {
        final Depositos d = new Depositos(idD, descricaoD);
        this.depositosRepository.save(d);
    }

}

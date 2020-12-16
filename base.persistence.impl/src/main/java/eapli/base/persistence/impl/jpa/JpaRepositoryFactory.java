package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.depositsmanagement.repositories.DepositosRepository;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.machinemanagement.repositories.MachineRepository;
import eapli.base.messages.repositories.MessagesRepository;
import eapli.base.notifications.repositories.NotificationsRepository;
import eapli.base.ordersmanagement.repositories.EncomendaRepository;
import eapli.base.productionlinemanagement.repositories.LinhaProducaoRepository;
import eapli.base.productionordermanagement.repositories.OrdemProducaoRepository;
import eapli.base.productmanagement.repositories.FichaProducaoRepository;
import eapli.base.productmanagement.repositories.ProdutoRepository;
import eapli.base.rawmaterialmanagement.repositories.CategoriaMateriaPrimaRepository;
import eapli.base.rawmaterialmanagement.repositories.MateriaPrimaRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(final TransactionalContext autoTx) {
        return new JpaAutoTxUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }


    @Override
    public JpaClientUserRepository clientUsers(final TransactionalContext autoTx) {
        return new JpaClientUserRepository(autoTx);
    }

    @Override
    public JpaClientUserRepository clientUsers() {
        return new JpaClientUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public MachineRepository maquinaManagement(TransactionalContext autoTx) {
        return new JpaMachineRepository(autoTx);
    }

    @Override
    public MachineRepository maquinaManagement() {
        return new JpaMachineRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public MateriaPrimaRepository materiaPrimaManagement(TransactionalContext autoTx) {
        return new JpaMateriaPrimaRepository(autoTx);
    }

    @Override
    public MateriaPrimaRepository materiaPrimaManagement() {
        return new JpaMateriaPrimaRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public NotificationsRepository notificationManagement(TransactionalContext autoTx) {
        return new JpaNotificationsRepository(autoTx);
    }

    @Override
    public NotificationsRepository notificationManagement() {
        return new JpaNotificationsRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public MessagesRepository messagesManagement(TransactionalContext autoTx) {
        return new JpaMessagesRepository(autoTx);
    }

    @Override
    public MessagesRepository messagesManagement() {
        return new JpaMessagesRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ProdutoRepository productManagement() {
        return new JpaProductRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public OrdemProducaoRepository ordemProducaoManagement() {
        return new JpaOrdemProducaoRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public EncomendaRepository encomendaManagement() {
        return new JpaEncomendaRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public FichaProducaoRepository fichaproducaoManagement() {
        return new JpaFichaProducaoRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public CategoriaMateriaPrimaRepository catMPManagement(TransactionalContext autoTx) {
        return new JpaCategoriaMateriaPrimaRepository(autoTx);
    }

    @Override
    public CategoriaMateriaPrimaRepository catMPManagement() {
        return new JpaCategoriaMateriaPrimaRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public LinhaProducaoRepository linhaProducaoManagement(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public LinhaProducaoRepository linhaProducaoManagement() {
        return new JpaLinhaProducaoRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public DepositosRepository depositoManagement(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public DepositosRepository depositoManagement() {
        return new JpaDepositosRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        return JpaAutoTxRepository.buildTransactionalContext(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }


}



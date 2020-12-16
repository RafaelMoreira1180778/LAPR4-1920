package eapli.base.persistence.impl.inmemory;

import eapli.base.clientusermanagement.repositories.ClientUserRepository;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.depositsmanagement.repositories.DepositosRepository;
import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
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
import eapli.framework.infrastructure.authz.repositories.impl.InMemoryUserRepository;

/**
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

    static {
        // only needed because of the in memory persistence
        new BaseBootstrapper().execute();
    }

    @Override
    public UserRepository users(final TransactionalContext tx) {
        return new InMemoryUserRepository();
    }

    @Override
    public UserRepository users() {
        return users(null);
    }


    @Override
    public ClientUserRepository clientUsers(final TransactionalContext tx) {

        return new InMemoryClientUserRepository();
    }

    @Override
    public ClientUserRepository clientUsers() {
        return clientUsers(null);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return signupRequests(null);
    }

    @Override
    public MachineRepository maquinaManagement(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public MachineRepository maquinaManagement() {
        return null;
    }

    @Override
    public MateriaPrimaRepository materiaPrimaManagement(TransactionalContext autoTx) {
        return new InMemoryRawMaterialRepository();
    }

    @Override
    public MateriaPrimaRepository materiaPrimaManagement() {
        return null;
    }

    @Override
    public NotificationsRepository notificationManagement(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public NotificationsRepository notificationManagement() {
        return null;
    }

    @Override
    public ProdutoRepository productManagement() {
        return null;
    }

    @Override
    public OrdemProducaoRepository ordemProducaoManagement() {
        return null;
    }

    @Override
    public EncomendaRepository encomendaManagement() {
        return null;
    }

    @Override
    public FichaProducaoRepository fichaproducaoManagement() {
        return null;
    }

    @Override
    public CategoriaMateriaPrimaRepository catMPManagement(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public CategoriaMateriaPrimaRepository catMPManagement() {
        return null;
    }

    @Override
    public LinhaProducaoRepository linhaProducaoManagement(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public LinhaProducaoRepository linhaProducaoManagement() {
        return null;
    }

    @Override
    public DepositosRepository depositoManagement(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public DepositosRepository depositoManagement() {
        return null;
    }

    @Override
    public MessagesRepository messagesManagement(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public MessagesRepository messagesManagement() {
        return null;
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext tx) {
        return new InMemorySignupRequestRepository();
    }


    @Override
    public TransactionalContext newTransactionalContext() {
        // in memory does not support transactions...
        return null;
    }

}

/**
 *
 */
package eapli.base.infrastructure.persistence;

import eapli.base.clientusermanagement.repositories.ClientUserRepository;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.depositsmanagement.repositories.DepositosRepository;
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

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface RepositoryFactory {

    /**
     * factory method to create a transactional context to use in the repositories
     *
     * @return
     */
    TransactionalContext newTransactionalContext();

    /**
     *
     * @param autoTx the transactional context to enrol
     * @return
     */
    UserRepository users(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    UserRepository users();

    /**
     *
     * @param autoTx the transactional context to enroll
     * @return
     */
    ClientUserRepository clientUsers(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    ClientUserRepository clientUsers();

    /**
     *
     * @param autoTx the transactional context to enroll
     * @return
     */
    SignupRequestRepository signupRequests(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    SignupRequestRepository signupRequests();

    MachineRepository maquinaManagement(TransactionalContext autoTx);

    MachineRepository maquinaManagement();

    MateriaPrimaRepository materiaPrimaManagement(TransactionalContext autoTx);

    MateriaPrimaRepository materiaPrimaManagement();

    NotificationsRepository notificationManagement(TransactionalContext autoTx);

    NotificationsRepository notificationManagement();

    ProdutoRepository productManagement();

    OrdemProducaoRepository ordemProducaoManagement();

    EncomendaRepository encomendaManagement();

    FichaProducaoRepository fichaproducaoManagement();

    CategoriaMateriaPrimaRepository catMPManagement(TransactionalContext autoTx);

    CategoriaMateriaPrimaRepository catMPManagement();

    LinhaProducaoRepository linhaProducaoManagement(TransactionalContext autoTx);

    LinhaProducaoRepository linhaProducaoManagement();

    DepositosRepository depositoManagement(TransactionalContext autoTx);

    DepositosRepository depositoManagement();

    MessagesRepository messagesManagement(TransactionalContext autoTx);

    MessagesRepository messagesManagement();

}

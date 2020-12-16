package eapli.base.machinemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.machinemanagement.domain.Maquina;
import eapli.base.machinemanagement.repositories.MachineRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.utils.FileManager;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class AddConfigFileController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final MachineRepository mr = PersistenceContext.repositories().maquinaManagement();

    public AddConfigFileController() {
    }

    public boolean addConfigFileToMachine(String fPath, Maquina m) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_CHAO_FABRICA);
        FileManager f = new FileManager();

        byte[] fConfig;
        byte[] temp = f.importFile(fPath);
        if (temp == null) {
            return false;
        } else {
            fConfig = temp;
        }

        m.setConfig(fConfig);
        return true;
    }

    public Iterable<Maquina> listAllMachines() {
        return this.mr.findAll();
    }
}

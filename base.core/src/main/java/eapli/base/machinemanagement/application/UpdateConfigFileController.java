package eapli.base.machinemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.machinemanagement.domain.Maquina;
import eapli.base.machinemanagement.repositories.MachineRepository;
import eapli.base.utils.Description;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class UpdateConfigFileController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final MachineRepository mr = PersistenceContext.repositories().maquinaManagement();

    public void updateConfig(final String path, Description description, Maquina m) {
        m.updateConfig(path, description);
        this.mr.save(m);
    }
}

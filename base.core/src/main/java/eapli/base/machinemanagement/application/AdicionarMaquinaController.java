package eapli.base.machinemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.machinemanagement.domain.MachineBuilder;
import eapli.base.machinemanagement.domain.Maquina;
import eapli.base.machinemanagement.domain.Timestamp;
import eapli.base.machinemanagement.repositories.MachineRepository;
import eapli.base.productionlinemanagement.domain.LinhaProducao;
import eapli.base.productionlinemanagement.repositories.LinhaProducaoRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.utils.Description;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Date;

@UseCaseController
public class AdicionarMaquinaController {

    private final MachineRepository mr = PersistenceContext.repositories().maquinaManagement();
    private final LinhaProducaoRepository lpr = PersistenceContext.repositories().linhaProducaoManagement();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public void adicionarMaquina(String id_Maquina, int num_Serie, String fabricante, String modelo, Date anoFabrico, Description description, Timestamp ts_instalacao, int uniqueID) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_CHAO_FABRICA);
        MachineBuilder mb = new MachineBuilder().withId(id_Maquina).withNumSerie(num_Serie).withFabricante(fabricante).withModelo(modelo).withAnoFabrico(anoFabrico).withDescricao(description).withTsInstalacao(ts_instalacao).withUniqueID(uniqueID);
        mr.save(mb.build());
    }

    public Iterable<LinhaProducao> listarLinhasProd() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_CHAO_FABRICA);
        return this.lpr.findAll();
    }

    public Iterable<Maquina> listarMaquinas() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_CHAO_FABRICA);
        return this.mr.findAll();
    }

    public boolean associarMaquinaLinhaProducao(LinhaProducao lp, Maquina m) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_CHAO_FABRICA);
        return lp.adicionarMaquina(m);
    }
}

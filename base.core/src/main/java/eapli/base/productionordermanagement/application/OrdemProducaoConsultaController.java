package eapli.base.productionordermanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productionordermanagement.domain.Estado;
import eapli.base.productionordermanagement.domain.OrdemProducao;
import eapli.base.productionordermanagement.repositories.OrdemProducaoRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Arrays;
import java.util.List;

public class OrdemProducaoConsultaController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final OrdemProducaoRepository opr = PersistenceContext.repositories().ordemProducaoManagement();

    public OrdemProducaoConsultaController() {
    }

    public Iterable<OrdemProducao> listallOrders() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_PRODUCAO);
        return this.opr.findAll();
    }

    public Iterable<OrdemProducao> listOrdensEstado(Estado estado) {
        return opr.listByEstado(estado);
    }

    public Iterable<Estado> listAllEstados() {
        Estado[] ntEnum = Estado.values();
        List<Estado> ntList = Arrays.asList(ntEnum);
        return ntList;
    }
}


package eapli.base.productionordermanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productionordermanagement.repositories.OrdemProducaoRepository;
import eapli.base.productmanagement.repositories.ProdutoRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.IOException;

public class ImportarOrdensProducaoController {

    private final OrdemProducaoRepository ordemProducaoRepository = PersistenceContext.repositories().ordemProducaoManagement();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public void importCSV(String fileName) throws IOException {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_PRODUCAO);
        ordemProducaoRepository.loadOrdensProducao(fileName);
    }
}

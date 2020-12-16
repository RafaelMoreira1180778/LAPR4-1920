package eapli.base.rawmaterialmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.rawmaterialmanagement.domain.CategoriaMateriaPrimaBuilder;
import eapli.base.rawmaterialmanagement.repositories.CategoriaMateriaPrimaRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class AdicionarCategoriaMateriaPrimaController {

    private final CategoriaMateriaPrimaRepository categoriaMateriaPrimaRepository = PersistenceContext.repositories().catMPManagement();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();


    public void adicionarCategoriaMateriaPrima(String id_cat) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_PRODUCAO);
        final CategoriaMateriaPrimaBuilder cmpb = new CategoriaMateriaPrimaBuilder().withId(id_cat);
        this.categoriaMateriaPrimaRepository.save(cmpb.build());
    }
}
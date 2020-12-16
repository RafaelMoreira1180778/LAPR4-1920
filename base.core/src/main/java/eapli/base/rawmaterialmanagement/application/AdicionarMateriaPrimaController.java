package eapli.base.rawmaterialmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.rawmaterialmanagement.domain.CategoriaMateriaPrima;
import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.base.rawmaterialmanagement.domain.RawMaterialBuilder;
import eapli.base.rawmaterialmanagement.repositories.CategoriaMateriaPrimaRepository;
import eapli.base.rawmaterialmanagement.repositories.MateriaPrimaRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.utils.Description;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class AdicionarMateriaPrimaController {

    private final MateriaPrimaRepository materiaPrimaRepository = PersistenceContext.repositories().materiaPrimaManagement();
    private final CategoriaMateriaPrimaRepository catMPR = PersistenceContext.repositories().catMPManagement();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public void adicionarMateriaPrima(String idP, Description descriptionP) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_PRODUCAO);
        final RawMaterialBuilder rmb = new RawMaterialBuilder().witId(idP).withDescricao(descriptionP);
        this.materiaPrimaRepository.save(rmb.build());
    }

    public Iterable<CategoriaMateriaPrima> listarCategorias() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_PRODUCAO);
        return this.catMPR.findAll();
    }

    public Iterable<RawMaterial> listarTodasMP() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_PRODUCAO);
        return this.materiaPrimaRepository.findAll();
    }

    public boolean adicionarMPaCAT(CategoriaMateriaPrima c, RawMaterial m) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_PRODUCAO);
        return c.adicionarMateriaPrima(m);
    }
}


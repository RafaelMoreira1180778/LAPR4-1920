package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.FichaProducao;
import eapli.base.productmanagement.domain.Quantidades;
import eapli.base.productmanagement.repositories.FichaProducaoRepository;
import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.base.rawmaterialmanagement.repositories.MateriaPrimaRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;


@UseCaseController
public class EspecificarFichaProducaoController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final FichaProducaoRepository fichaprodutoRepository = PersistenceContext.repositories().fichaproducaoManagement();
    private final MateriaPrimaRepository mpr = PersistenceContext.repositories().materiaPrimaManagement();

    public void EspecificarFichaProducao(String id, ArrayList<RawMaterial> listRawMaterial, ArrayList<Quantidades> listQuantidades) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_PRODUCAO);
        final FichaProducao ficha = new FichaProducao(id, listRawMaterial, listQuantidades);
        this.fichaprodutoRepository.save(ficha);

        if (fichaprodutoRepository.findByIDf(id) == fichaprodutoRepository) {
            System.out.println("Esta ficha ja existe!");
        } else {
            fichaprodutoRepository.save(ficha);
        }
    }

    public Iterable<RawMaterial> listarMPs() {
        return this.mpr.findAll();
    }
}


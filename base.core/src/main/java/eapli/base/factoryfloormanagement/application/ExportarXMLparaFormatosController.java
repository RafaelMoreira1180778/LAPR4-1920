package eapli.base.factoryfloormanagement.application;

import eapli.base.factoryfloormanagement.domain.ExportarXMLparaFormatos;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class ExportarXMLparaFormatosController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public void export(FileFormat format) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_PRODUCAO);
        ExportarXMLparaFormatos exportar = new ExportarXMLparaFormatos();

        try {
            exportar.generateOutFile(format);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

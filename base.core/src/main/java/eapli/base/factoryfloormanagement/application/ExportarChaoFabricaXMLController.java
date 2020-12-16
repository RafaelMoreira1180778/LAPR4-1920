package eapli.base.factoryfloormanagement.application;

import eapli.base.factoryfloormanagement.domain.ExportarChaoFabricaXML;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class ExportarChaoFabricaXMLController {

    private static int MAX_TIPOS_DADOS = 9;
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private int[] tipoDadosParaExportar = new int[MAX_TIPOS_DADOS];

    public void exportarChaoFabricaXML() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_PRODUCAO);
        String filename = "ficheiros/ChaoDeFabrica.xml";
        String dataInicio = "0";
        String dataFim = "0";
        ExportarChaoFabricaXML exportarChaoFabricaXML = new ExportarChaoFabricaXML(filename, tipoDadosParaExportar, dataInicio, dataFim);
        exportarChaoFabricaXML.exportarParaXML();
    }

    public boolean adicionarTipoDadoParaExportar(int tipoDado, int posicao) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_PRODUCAO);
        if (tipoDado > 0 && tipoDado < 10 && tipoDado != 4) {
            for(int i = 0; i < tipoDadosParaExportar.length; i++) {
                if (tipoDado == tipoDadosParaExportar[i]) {
                    System.out.println("\nDado Repetido\n");
                    return false;
                }
            }
            tipoDadosParaExportar[posicao] = tipoDado;
            return true;
        }
        System.out.println("\nDado Invalido\n");
        return false;
    }
}

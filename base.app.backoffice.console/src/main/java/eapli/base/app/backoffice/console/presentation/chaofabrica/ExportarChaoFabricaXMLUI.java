package eapli.base.app.backoffice.console.presentation.chaofabrica;

import eapli.base.factoryfloormanagement.application.ExportarChaoFabricaXMLController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

public class ExportarChaoFabricaXMLUI extends AbstractUI {

    ExportarChaoFabricaXMLController controller = new ExportarChaoFabricaXMLController();

    @Override
    protected boolean doShow() {
        int posicao = 0;
        int tipoDado;

        mostrarTiposDados();
        tipoDado = Console.readInteger("Selecionar Tipo Dado para Exportar:");
        try {
            while(tipoDado != 0) {
                if (controller.adicionarTipoDadoParaExportar(tipoDado, posicao)) {
                    posicao++;
                    System.out.println("\n---Adicionado---\n\n");
                }
                mostrarTiposDados();
                tipoDado = Console.readInteger("Selecionar Tipo Dado para Exportar:");
            }


        controller.exportarChaoFabricaXML();
        } catch (Exception e) {

        }
        System.out.println("\nExportado para XML com sucesso\n");
        return true;
    }

    @Override
    public String headline() {
        return "Exportar Chao de Fabrica Para XML";
    }

    private void mostrarTiposDados() {
        System.out.println("1: Materias Primas");
        System.out.println("2: Categorias Materias Primas");
        System.out.println("3: Produtos");
        //System.out.println("4: Fichas de Producao");
        System.out.println("5: Linhas de Producao");
        System.out.println("6: Encomendas");
        System.out.println("7: Maquinas");
        System.out.println("8: Depositos");
        System.out.println("9: Ordens de Producao");
        System.out.println("0: Concluir");
    }
}

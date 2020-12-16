package eapli.base.app.backoffice.console.presentation.produtos;


import eapli.base.productmanagement.application.EspecificarFichaProducaoController;
import eapli.base.productmanagement.domain.FichaProducao;
import eapli.base.productmanagement.domain.Quantidades;
import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

import java.util.ArrayList;


public class EspecificarFichaProducaoUI extends AbstractUI {

    private final EspecificarFichaProducaoController controller = new EspecificarFichaProducaoController();

    @Override
    protected boolean doShow() {

        ArrayList<RawMaterial> lmp = new ArrayList<>();
        ArrayList<Quantidades> lq = new ArrayList<>();

        final String id = Console.readLine("ID da ficha de producao: ");
        final int qntMP = Console.readInteger("Quantas materias primas vai inserir?");

        Iterable<RawMaterial> lplist = controller.listarMPs();

        for (int i = 0; i < qntMP; i++) {
            SelectWidget<RawMaterial> selector = new SelectWidget<>("\nSelecionar ", lplist, new MateriasPrimasPrinter());
            selector.show();
            RawMaterial mp = selector.selectedElement();
            lmp.add(mp);
            String qt = Console.readLine("Quantidade: ");
            lq.add(new Quantidades(qt));
        }

        try {
            this.controller.EspecificarFichaProducao(id, lmp, lq);
            System.out.println(new FichaProducao(id, lmp, lq));
        } catch (Exception e) {

        }
        return true;
    }

    @Override
    public String headline() {
        return "Adicionar Ficha de Producao";
    }

}

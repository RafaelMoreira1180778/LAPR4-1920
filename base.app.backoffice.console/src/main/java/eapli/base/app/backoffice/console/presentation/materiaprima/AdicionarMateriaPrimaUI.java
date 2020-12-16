package eapli.base.app.backoffice.console.presentation.materiaprima;

import eapli.base.rawmaterialmanagement.application.AdicionarMateriaPrimaController;
import eapli.base.rawmaterialmanagement.domain.CategoriaMateriaPrima;
import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.base.utils.Description;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

public class AdicionarMateriaPrimaUI extends AbstractUI {

    private final AdicionarMateriaPrimaController controller = new AdicionarMateriaPrimaController();

    @Override
    protected boolean doShow() {

        Iterable<CategoriaMateriaPrima> lcmp = controller.listarCategorias();

        final SelectWidget<CategoriaMateriaPrima> selector = new SelectWidget<>("Selecionar Categoria desta MP:", lcmp, new CategoriasPrinter());
        selector.show();
        final CategoriaMateriaPrima catMP = selector.selectedElement();

        final String id = Console.readLine("ID da materia prima:");
        final String desc = Console.readLine("Descricao da MP: ");

        try {
            this.controller.adicionarMateriaPrima(id, new Description(desc));
            if (this.controller.adicionarMPaCAT(catMP, new RawMaterial(id, new Description(desc)))) {
                System.out.println("A matéria prima com o ID " + id + " foi adicionada a categoria " + catMP.identity());
            }
        } catch (Exception e) {

        }

        return true;
    }

    @Override
    public String headline() {
        return "Adicionar Materia Prima Ao Catalogo";
    }
}

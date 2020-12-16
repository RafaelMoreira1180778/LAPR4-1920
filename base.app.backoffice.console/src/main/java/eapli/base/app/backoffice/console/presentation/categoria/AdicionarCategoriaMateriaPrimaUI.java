package eapli.base.app.backoffice.console.presentation.categoria;

import eapli.base.rawmaterialmanagement.application.AdicionarCategoriaMateriaPrimaController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;


public class AdicionarCategoriaMateriaPrimaUI extends AbstractUI {

    private final AdicionarCategoriaMateriaPrimaController controller = new AdicionarCategoriaMateriaPrimaController();

    @Override
    protected boolean doShow() {

        final String id_cat = Console.readLine("ID categoria de materias primas:");

        try {
            this.controller.adicionarCategoriaMateriaPrima(id_cat);
        } catch (Exception e) {

        }

        return true;
    }

    @Override
    public String headline() {
        return "Adicionar Categoria Materia Prima";
    }

}

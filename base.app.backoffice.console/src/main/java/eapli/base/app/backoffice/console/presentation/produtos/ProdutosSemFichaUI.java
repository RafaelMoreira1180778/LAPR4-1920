package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.base.productmanagement.application.ProdutosSemFichaController;
import eapli.base.productmanagement.domain.Produto;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

import java.util.ArrayList;
import java.util.List;

public class ProdutosSemFichaUI extends AbstractUI {

    private final ProdutosSemFichaController controller = new ProdutosSemFichaController();

    @Override
    protected boolean doShow() {

        final String answer = Console.readLine("Pretende consultar os produtos sem uma ficha de producao associada? (sim / nao)");

        try {
            if (answer.equals("sim")) {
                controller.produtosSemFicha();
            }
        } catch (Exception e) {

        }

        return true;
    }

    @Override
    public String headline() {
        return "Consultar Produtos sem Ficha de Producao";
    }
}

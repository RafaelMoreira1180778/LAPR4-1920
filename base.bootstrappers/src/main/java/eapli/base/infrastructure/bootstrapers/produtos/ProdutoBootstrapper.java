package eapli.base.infrastructure.bootstrapers.produtos;

import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.productmanagement.application.AdicionarProdutoController;
import eapli.base.productmanagement.domain.CategoriaProduto;
import eapli.base.productmanagement.domain.FichaProducao;
import eapli.base.productmanagement.domain.Quantidades;
import eapli.base.utils.Description;
import eapli.framework.actions.Action;


public class ProdutoBootstrapper extends BaseBootstrapper implements Action {

    @Override
    public boolean execute() {
        adicionarProduto(new FichaProducao(), "PD1", "IDC1", new Description("DESC1"), new Description("Descricao1"), new Quantidades("50"), new CategoriaProduto("123"));
        adicionarProduto(new FichaProducao(), "PD2", "IDC2", new Description("DESC2"), new Description("Descricao2"), new Quantidades("30"), new CategoriaProduto("131"));
        adicionarProduto(new FichaProducao(), "PD3", "IDC3", new Description("DESC3"), new Description("Descricao3"), new Quantidades("20"), new CategoriaProduto("132"));
        return true;
    }

    private void adicionarProduto(FichaProducao fichaProducao, String idProduto, String idComercial, Description descriptionBreve, Description descriptionCompleta, Quantidades qtd, CategoriaProduto cat) {
        AdicionarProdutoController ampc = new AdicionarProdutoController();
        ampc.adicionarProduto(fichaProducao, idProduto, idComercial, descriptionBreve, descriptionCompleta, qtd, cat);
    }
}


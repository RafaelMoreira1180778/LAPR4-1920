package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.base.productmanagement.application.AdicionarProdutoController;
import eapli.base.productmanagement.domain.CategoriaProduto;
import eapli.base.productmanagement.domain.FichaProducao;
import eapli.base.productmanagement.domain.Quantidades;
import eapli.base.utils.Description;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

public class AdicionarProdutoUI extends AbstractUI {

    private final AdicionarProdutoController controller = new AdicionarProdutoController();

    public String checkNull(String in) {
        String out;

        if(in.equals("null")) {
            out = null;
        } else {
            out = in;
        }

        return out;
    }

    @Override
    protected boolean doShow() {
        String p, ic, fp, db, dc, u, c;

        FichaProducao fipr;
        Description debr;
        Description deco;
        Quantidades qu;
        CategoriaProduto ca;

        final String idProduto = Console.readLine("ID do Produto: ");
        final String idComercial = Console.readLine("ID Comercial: ");
        final String idFichaProducao = Console.readLine("ID da Ficha de Produção: ");
        final String descricaoBreve = Console.readLine("Descricao Breve do Produto: ");
        final String descricaoCompleta = Console.readLine("Descricao Completa do Produto: ");
        final String unidade = Console.readLine("Unidade do Produto: ");
        final String categoria = Console.readLine("Categoria do produto: ");

        p = checkNull(idProduto);
        ic = checkNull(idComercial);

        fp = checkNull(idFichaProducao);
        if(fp == null) {
            fipr = null;
        } else {
            fipr = new FichaProducao(fp);
        }

        db = checkNull(descricaoBreve);
        if(db == null) {
            debr = null;
        } else {
            debr = new Description(db);
        }

        dc = checkNull(descricaoCompleta);
        if(dc == null) {
            deco = null;
        } else {
            deco = new Description(dc);
        }

        u = checkNull(unidade);
        if(u == null) {
            qu = null;
        } else {
            qu = new Quantidades(u);
        }

        c = checkNull(categoria);
        if(c == null) {
            ca = null;
        } else {
            ca = new CategoriaProduto(c);
        }

        try {
            this.controller.adicionarProduto(fipr, p, ic, debr, deco, qu, ca);
        } catch (Exception e) {

        }

        return true;
    }

    @Override
    public String headline() {
        return "Adicionar Produto";
    }
}

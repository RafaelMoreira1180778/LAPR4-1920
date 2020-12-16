package eapli.base.app.backoffice.console.presentation.ordemproducao;

import eapli.base.productionordermanagement.application.AdicionarOrdemProducaoController;
import eapli.base.productionordermanagement.domain.ContentorIdEncomendas;
import eapli.base.productmanagement.domain.Quantidades;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

import java.util.ArrayList;
import java.util.Date;

public class AdicionarOrdemProducaoUI extends AbstractUI {

    private final AdicionarOrdemProducaoController controller = new AdicionarOrdemProducaoController();

    @Override
    protected boolean doShow() {

        final String idOP = Console.readLine("ID da Ordem de Produção: ");
        final int diaEmissao = Integer.parseInt(Console.readLine("Dia de Emissão: "));
        final int mesEmissao = Integer.parseInt(Console.readLine("Mês de Emissão: "));
        final int anoEmissao = Integer.parseInt(Console.readLine("Ano de Emissão: "));
        final int diaPrevisao = Integer.parseInt(Console.readLine("Dia de Previsão de Execução: "));
        final int mesPrevisao = Integer.parseInt(Console.readLine("Mês de Previsão de Execução: "));
        final int anoPrevisao = Integer.parseInt(Console.readLine("Ano de Previsão de Execução: "));
        final String idProduto = Console.readLine("ID do Produto a Produzir: ");
        final int quantidade = Integer.parseInt(Console.readLine("Quantidade a Produzir: "));
        final String unidade = Console.readLine("Unidade do Produto: ");
        final int nEncomendas = Integer.parseInt(Console.readLine("Número de Encomendas: "));

        final ArrayList<String> idEncomendas = new ArrayList<>();

        for (int i = 0; i < nEncomendas; i++) {
            String idEnc = Console.readLine("ID da Encomenda #" + (i + 1) + ":");
            idEncomendas.add(idEnc);
        }

        try {
            this.controller.adicionarOrdemProducao(idOP, new Date(anoEmissao, mesEmissao, diaEmissao), new Date(anoPrevisao, mesPrevisao, diaPrevisao), idProduto, quantidade, new Quantidades(unidade), new ContentorIdEncomendas(idEncomendas));
        } catch (Exception e) {

        }

        return true;
    }

    @Override
    public String headline() {
        return "Adicionar Ordem de Produção";
    }
}

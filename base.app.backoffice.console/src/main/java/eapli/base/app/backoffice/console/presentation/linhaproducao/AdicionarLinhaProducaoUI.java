package eapli.base.app.backoffice.console.presentation.linhaproducao;

import eapli.base.productionlinemanagement.application.AdicionarLinhaProducaoController;
import eapli.base.productionlinemanagement.domain.EstadoProcessamento;
import eapli.base.rawmaterialmanagement.application.AdicionarCategoriaMateriaPrimaController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

import java.util.Date;


public class AdicionarLinhaProducaoUI extends AbstractUI {

    private final AdicionarLinhaProducaoController controller = new AdicionarLinhaProducaoController();

    @Override
    protected boolean doShow() {

        final String id_lp = Console.readLine("ID da linha de producao:");
        final EstadoProcessamento ep;

        System.out.println("Estado do Processamento de Mensagens:");
        System.out.println("1 - Ativo");
        System.out.println("2 - Desativo");

        int op;
        op = Integer.parseInt(Console.readLine("Estado:"));
        switch (op){
            case 1:
                ep = EstadoProcessamento.ATIVO;
                break;
            default:
                ep = EstadoProcessamento.DESATIVO;
                break;
        }

        final int dia = Integer.parseInt(Console.readLine("Dia da ultima ocorrencia: "));
        final int mes = Integer.parseInt(Console.readLine("Mes da ultima ocorrencia: "));
        final int ano = Integer.parseInt(Console.readLine("Ano da ultima ocorrencia: "));

        final int hora = Integer.parseInt(Console.readLine("Horas da ultima ocorrencia: "));
        final int min = Integer.parseInt(Console.readLine("Minutos da ultima ocorrencia: "));

        try {
            this.controller.adicionarLinhaProducao(id_lp, ep, new Date(ano - 1900, mes - 1, dia, hora, min));
        } catch (Exception e) {

        }

        return true;
    }

    @Override
    public String headline() {
        return "Adicionar Linha de Producao";
    }

}

package eapli.base.app.spm;

import eapli.base.app.spm.application.ProcessingHandler;
import eapli.framework.util.Console;

import java.util.Date;

public class ServicoProcessamentoMensagens {

    public static void main(String[] args) {

        final String idLP = Console.readLine("ID da Linha de Produção: ");

        final int intervaloTempo = Integer.parseInt(Console.readLine("Intervalo de Tempo: "));

        final int dia = Integer.parseInt(Console.readLine("Dia de Realização: "));
        final int mes = Integer.parseInt(Console.readLine("Mês de Realização: "));
        final int ano = Integer.parseInt(Console.readLine("Ano de Realização: "));
        final int horaInicio = Integer.parseInt(Console.readLine("Hora de Início: "));
        final int minInicio = Integer.parseInt(Console.readLine("Minuto de Início: "));

        ProcessingHandler ph = new ProcessingHandler(idLP, intervaloTempo, new Date(ano - 1900, mes - 1, dia, horaInicio, minInicio));

        ph.run();
    }
}

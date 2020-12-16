package eapli.base.app.backoffice.console.presentation.linhaproducao;

import eapli.base.productionlinemanagement.application.ConsultarEstadoController;
import eapli.base.productionlinemanagement.domain.EstadoProcessamento;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

import java.util.Date;

public class ConsultarEstadoUI extends AbstractUI {

    private final ConsultarEstadoController controller = new ConsultarEstadoController();

    @Override
    protected boolean doShow() {

        EstadoProcessamento ep;
        EstadoProcessamento novoEP;
        Date ultimaOcorrencia;

        final String idLP = Console.readLine("ID da Linha de Producao: ");

        final String menu = ("\nOpções: \n" +
                             "1 - Consultar o estado atual do processamento de mensagens;\n" +
                             "2 - Alterar o estado do processamento de mensagens;\n" +
                             "3 - Consultar a última ocorrência do processamento de mensagens;\n" +
                             "4 - Executar novamente o processamento de mensagens;\n" +
                             "0 - Sair.");

        int op;

        try {
            do{
                System.out.println(menu);
                op = Integer.parseInt(Console.readLine("Opção: "));

                switch(op) {
                    case 1:
                        ep = this.controller.currentStatus(idLP);
                        System.out.println("Estado Atual: " + ep.toString());
                        break;

                    case 2:
                        System.out.println("Novo Estado do Processamento de Mensagens:");
                        System.out.println("1 - Ativo");
                        System.out.println("2 - Desativo");
                        int a;
                        a = Integer.parseInt(Console.readLine("Estado:"));
                        switch (a){
                            case 1:
                                novoEP = EstadoProcessamento.ATIVO;
                                break;
                            default:
                                novoEP = EstadoProcessamento.DESATIVO;
                                break;
                        }
                        this.controller.changeStatus(idLP, novoEP);
                        System.out.println("Estado de Processamento alterado!");
                        break;

                    case 3:
                        ultimaOcorrencia = this.controller.ultimaOcorrencia(idLP);
                        System.out.println("O ultimo processamento de mensagens da linha " + idLP + " ocorreu a " + ultimaOcorrencia.toString());
                        break;

                    case 4:
                        this.controller.reRunProcessamento();
                        break;

                    default:
                        break;
                }
            } while (op != 0);
        } catch (Exception e) {

        }
        return true;
    }

    @Override
    public String headline() {
        return "Consultar Estado do Processamento de Mensagens";
    }
}

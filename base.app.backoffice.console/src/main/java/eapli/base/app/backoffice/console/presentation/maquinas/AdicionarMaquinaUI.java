package eapli.base.app.backoffice.console.presentation.maquinas;

import eapli.base.machinemanagement.application.AdicionarMaquinaController;
import eapli.base.machinemanagement.domain.Maquina;
import eapli.base.machinemanagement.domain.Timestamp;
import eapli.base.productionlinemanagement.domain.LinhaProducao;
import eapli.base.utils.Description;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

import java.sql.Time;
import java.util.Date;

public class AdicionarMaquinaUI extends AbstractUI {

    private final AdicionarMaquinaController controller = new AdicionarMaquinaController();

    @Override
    protected boolean doShow() {


        Iterable<LinhaProducao> lplist = controller.listarLinhasProd();
        final SelectWidget<LinhaProducao> selector = new SelectWidget<>("Selecionar linha de producao para enquadrar maquina", lplist, new LinhasProdPrinter());
        selector.show();
        final LinhaProducao lp = selector.selectedElement();

        final String id_Maquina = Console.readLine("ID da maquina:");
        final int num_Serie = Console.readInteger("Numero de Serie:");
        final String fabricante = Console.readLine("Fabricante");
        final String modelo = Console.readLine("Modelo:");
        final Date data = Console.readDate("Data de fabrico no formato ano/mes/dia:");
        final String descricao = Console.readLine("Descricao da maquina:");
        final Date data_instalacao = Console.readDate("Data de instalacao: ");
        final int h_instalacao = Console.readInteger("Hora de instalacao:");
        final int m_instalacao = Console.readInteger("Minutos de instalacao:");
        final int uniqueID = Console.readInteger("Unique Identifier:");

        try {
            this.controller.adicionarMaquina(id_Maquina, num_Serie, fabricante, modelo, data, new Description(descricao), new Timestamp(data_instalacao, new Time(h_instalacao, m_instalacao, 0)), uniqueID);
            if (this.controller.associarMaquinaLinhaProducao(lp, new Maquina(id_Maquina, num_Serie, fabricante, modelo, data, new Description(descricao), new Timestamp(data_instalacao, new Time(h_instalacao, m_instalacao, 0))))) {
                System.out.println("A máquina com ID " + id_Maquina + " foi adicionada a linha de producao com ID " + lp.identity());
            }
        } catch (Exception e) {
        }

        return true;
    }

    @Override
    public String headline() {
        return "Adicionar Máquina";
    }
}

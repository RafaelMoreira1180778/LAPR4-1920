package eapli.base.infrastructure.bootstrapers.maquinas;

import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.machinemanagement.application.AdicionarMaquinaController;
import eapli.base.machinemanagement.domain.Timestamp;
import eapli.base.machinemanagement.repositories.MachineRepository;
import eapli.base.utils.Description;
import eapli.framework.actions.Action;

import java.util.Calendar;
import java.util.Date;


public class MachineBootstrapper extends BaseBootstrapper implements Action {

    @Override
    public boolean execute() {
        adicionarMaquina("M30", 12345, "LG", "LG321", new Date(2020, Calendar.APRIL, 13), new Description("DESC1"), new Timestamp(13, 4, 2020, 6, 30), 222);
        adicionarMaquina("M31", 15421, "Becken", "B32", new Date(2020, Calendar.APRIL, 14), new Description("DESC1"), new Timestamp(14, 4, 2020, 5, 30), 222);
        adicionarMaquina("M32", 13421, "LG", "LG541", new Date(2020, Calendar.APRIL, 15), new Description("DESC1"), new Timestamp(15, 4, 2020, 2, 40), 333);

        MachineRepository mr = PersistenceContext.repositories().maquinaManagement();

        mr.setMachineIP("1.1.1.1", mr.findByID("M30").get().identity());
        mr.setMachineIP("1.1.1.2", mr.findByID("M31").get().identity());
        mr.setMachineIP("1.1.1.3", mr.findByID("M32").get().identity());

        return true;
    }

    private void adicionarMaquina(String id_Maquina, int num_Serie, String fabricante, String modelo, Date anoFabrico, Description description, Timestamp ts_instalacao, int uniqueID) {
        AdicionarMaquinaController amc = new AdicionarMaquinaController();
        amc.adicionarMaquina(id_Maquina, num_Serie, fabricante, modelo, anoFabrico, description, ts_instalacao, uniqueID);
    }

}

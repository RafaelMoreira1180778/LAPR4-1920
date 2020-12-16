package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.machinemanagement.domain.Maquina;
import eapli.base.machinemanagement.repositories.MachineRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaMachineRepository extends JpaAutoTxRepository<Maquina, String, String> implements MachineRepository {

    public JpaMachineRepository(TransactionalContext autoTx) {
        super(autoTx, "id_Maquina");
    }

    public JpaMachineRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id_Maquina");
    }

    @Override
    public Optional<Maquina> findByID(String id_m) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id_m", id_m);
        return matchOne("e.id_Maquina = :id_m", params);
    }

    public void setMachineIP(String IP, String machineID) {
        Maquina m = null;
        Optional<Maquina> fm = findByID(machineID);
        if (fm.isPresent()) {
            m = fm.get();
        }
        m.setIp(IP);
        save(m);
    }
}

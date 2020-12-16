package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.notifications.domain.Notification;
import eapli.base.notifications.domain.NotificationType;
import eapli.base.notifications.repositories.NotificationsRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JpaNotificationsRepository extends JpaAutoTxRepository<Notification, String, String> implements NotificationsRepository {

    public JpaNotificationsRepository(TransactionalContext autoTx) {
        super(autoTx, "idNotification");
    }

    public JpaNotificationsRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "idNotification");
    }

    @Override
    public List<Notification> listByProductionLine(String idLP) {
        final Map<String, Object> params = new HashMap<>();
        params.put("idLp", idLP);
        return match("e.idProductionLine = :idLp", params);
    }

    @Override
    public List<Notification> listByIdMachine(String idMachine) {
        final Map<String, Object> params = new HashMap<>();
        params.put("idMachine", idMachine);
        return match("e.idMachine = :idMachine", params);
    }

    @Override
    public List<Notification> listByDescription(String description) {
        final Map<String, Object> params = new HashMap<>();
        params.put("description", description);
        return match("e.description = :description", params);
    }

    @Override
    public List<Notification> listByType(NotificationType type) {
        final Map<String, Object> params = new HashMap<>();
        params.put("type", type);
        return match("e.type = :type", params);
    }
}

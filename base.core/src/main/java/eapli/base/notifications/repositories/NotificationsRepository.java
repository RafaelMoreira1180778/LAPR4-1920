package eapli.base.notifications.repositories;

import eapli.base.notifications.domain.Notification;
import eapli.base.notifications.domain.NotificationType;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface NotificationsRepository extends DomainRepository<String, Notification> {

    List<Notification> listByProductionLine(String idLP);

    List<Notification> listByIdMachine(String idMachine);

    List<Notification> listByDescription(String description);

    List<Notification> listByType(NotificationType type);

}

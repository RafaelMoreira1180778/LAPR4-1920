package eapli.base.infrastructure.bootstrapers.notifications;

import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.notifications.application.ListErrorNotificationController;
import eapli.base.notifications.domain.NotificationBuilder;
import eapli.base.notifications.domain.NotificationType;
import eapli.base.notifications.repositories.NotificationsRepository;
import eapli.base.utils.Description;
import eapli.framework.actions.Action;
import eapli.framework.time.util.Calendars;

import java.util.Calendar;

public class NotificationsBootstrapper extends BaseBootstrapper implements Action {

    private final ListErrorNotificationController controller = new ListErrorNotificationController();
    private final NotificationsRepository nr = PersistenceContext.repositories().notificationManagement();

    @Override
    public boolean execute() {
        addNotificationError("IDN1", "M30", "LP1", Calendars.now(), new Description("descN1"), NotificationType.MISSING_MATERIAL, false);
        addNotificationError("IDN2", "M30", "LP1", Calendars.now(), new Description("descN2"), NotificationType.MISSING_MATERIAL, false);
        addNotificationError("IDN3", "M31", "LP2", Calendars.now(), new Description("descN3"), NotificationType.MISSING_INFORMATION, false);
        addNotificationError("IDN4", "M31", "LP2", Calendars.now(), new Description("descN4"), NotificationType.MISSING_INFORMATION, false);
        return true;
    }

    public void addNotificationError(String idNotification, String idMachine, String idLP, Calendar createdOn, Description desc, NotificationType type, Boolean status) {
        NotificationBuilder nb = new NotificationBuilder().withData(idNotification, idMachine, idLP, createdOn, desc, type, status);
        nr.save(nb.build());
    }

}
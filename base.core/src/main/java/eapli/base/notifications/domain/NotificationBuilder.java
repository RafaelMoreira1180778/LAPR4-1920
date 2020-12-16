package eapli.base.notifications.domain;

import eapli.base.utils.Description;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.time.util.Calendars;

import java.util.Calendar;

public class NotificationBuilder implements DomainFactory<Notification> {

    private String idNotification;
    private String idMachine;
    private String idProcutionLine;
    private Calendar createdOn;
    private Description description;
    private boolean status;
    private NotificationType type;

    public NotificationBuilder NotificationBuilder() {
        return this;
    }

    public NotificationBuilder withData(String idNotification, String idMachine, String idProcutionLine, Calendar createdOn, Description description, NotificationType type, boolean status) {
        this.idNotification = idNotification;
        this.idMachine = idMachine;
        this.idProcutionLine = idProcutionLine;
        this.createdOn = createdOn;
        this.description = description;
        this.type = type;
        this.status = status;
        return this;
    }

    public NotificationBuilder withNotificationID(String id) {
        this.idNotification = id;
        return this;
    }

    public NotificationBuilder withMachineID(String id) {
        this.idMachine = id;
        return this;
    }

    public NotificationBuilder withPLineID(String id) {
        this.idNotification = id;
        return this;
    }

    public NotificationBuilder withCreatedOn(Calendar createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public NotificationBuilder withDescription(Description desc) {
        this.description = desc;
        return this;
    }

    public NotificationBuilder withStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public NotificationBuilder withType(NotificationType type) {
        this.type = type;
        return this;
    }

    @Override
    public Notification build() {
        if (createdOn != null) {
            this.createdOn = Calendars.now();
        }
        return new Notification(idNotification, idMachine, idProcutionLine, createdOn, description, type, status);
    }
}

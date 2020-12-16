package eapli.base.notifications.domain;

import eapli.base.utils.Description;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Calendar;

/**
 * @author Paulo Moreira 1180778
 */
@Entity
public class Notification implements AggregateRoot<String> {

    @Transient
    private final String STATUS_ZERO = "POR RESOLVER";
    @Transient
    private final String STATUS_ONE = "RESOLVIDO";

    @Id
    private String idNotification;
    private String idMachine;
    private String idProcutionLine;
    private Calendar createdOn;
    private Description description;
    private NotificationType type;
    private boolean status;

    public Notification() {
    }

    public Notification(String idNotification, String idMachine, String idProcutionLine, Calendar createdOn, Description description, NotificationType type, boolean status) {
        this.idNotification = idNotification;
        this.idMachine = idMachine;
        this.idProcutionLine = idProcutionLine;
        this.createdOn = createdOn;
        this.description = description;
        this.type = type;
        this.status = status;
    }

    @Override
    public String toString() {
        return "\nNotification " + this.idNotification + ":\n" +
                "ID Maquina: " + this.idMachine + " | Linha de Producao " + this.idProcutionLine + "\n" +
                "Criada em: " + this.createdOn.getTime().toString() + "\n" +
                "Descricao: " + this.description.toString() + "\n" +
                "Estado: " + getStatus();
    }

    public String getStatus() {
        return this.status ? STATUS_ONE : STATUS_ZERO;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getIdMachine() {
        return this.idMachine;
    }

    public Description getDescription() {
        return this.description;
    }

    public Calendar getCreationDate() {
        return this.createdOn;
    }

    public NotificationType getType() {
        return this.type;
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public String identity() {
        return this.idNotification;
    }
}

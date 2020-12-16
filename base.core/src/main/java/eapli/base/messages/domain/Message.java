package eapli.base.messages.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public class Message implements AggregateRoot<String> {

    @Id
    @GeneratedValue
    private int idMessage;

    private String idMachine;
    private MessageType type;
    private Calendar generatedOn;

    public Message() {
    }

    public Message(String idMachine, MessageType type, Calendar generatedOn) {
        Preconditions.nonEmpty(idMachine);
        Preconditions.nonNull(type);
        Preconditions.nonNull(generatedOn);
        this.idMachine = idMachine;
        this.type = type;
        this.generatedOn = generatedOn;
    }

    public MessageType getType() {
        return this.type;
    }

    public String getIdMachine() {
        return this.idMachine;
    }

    public Calendar getGeneratedOn() {
        return this.generatedOn;
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public String identity() {
        return String.valueOf(this.idMessage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return idMessage == message.idMessage &&
                idMachine.equals(message.idMachine) &&
                type == message.type &&
                generatedOn.equals(message.generatedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMessage, idMachine, type, generatedOn);
    }
}

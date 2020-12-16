package eapli.base.messages.domain;

import javax.persistence.Entity;
import java.util.Calendar;
import java.util.Objects;

@Entity
public class ReturnActivityMessage extends Message {

    private String error;

    public ReturnActivityMessage() {
    }

    public ReturnActivityMessage(String idMachine, MessageType type, Calendar generatedOn, String error) {
        super(idMachine, type, generatedOn);
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReturnActivityMessage)) return false;
        if (!super.equals(o)) return false;
        ReturnActivityMessage that = (ReturnActivityMessage) o;
        return error.equals(that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), error);
    }

    @Override
    public String toString() {
        return "ReturnActivityMessage{" +
                "error='" + error + '\'' +
                '}';
    }
}

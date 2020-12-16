package eapli.base.messages.domain;

import javax.persistence.Entity;
import java.util.Calendar;

@Entity
public class ActivityStopMessage extends Message {

    public ActivityStopMessage() {
    }

    public ActivityStopMessage(String idMachine, MessageType type, Calendar generatedOn) {
        super(idMachine, type, generatedOn);
    }

    @Override
    public String toString() {
        return "PauseActivityMessage{}" + super.toString();
    }
}

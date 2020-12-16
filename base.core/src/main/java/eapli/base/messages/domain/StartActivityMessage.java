package eapli.base.messages.domain;

import javax.persistence.Entity;
import java.util.Calendar;
import java.util.Objects;

@Entity
public class StartActivityMessage extends Message {

    private String productionOrder;

    public StartActivityMessage() {
    }

    public StartActivityMessage(String idMachine, MessageType type, Calendar generatedOn, String productionOrder) {
        super(idMachine, type, generatedOn);
        this.productionOrder = productionOrder;
    }

    public String getProductionOrder() {
        return productionOrder;
    }

    public void setProductionOrder(String productionOrder) {
        this.productionOrder = productionOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StartActivityMessage)) return false;
        if (!super.equals(o)) return false;
        StartActivityMessage that = (StartActivityMessage) o;
        return productionOrder.equals(that.productionOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), productionOrder);
    }

    @Override
    public String toString() {
        return "StartActivityMessage{" +
                "productionOrder='" + productionOrder + '\'' +
                '}';
    }
}

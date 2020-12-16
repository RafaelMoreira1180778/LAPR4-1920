package eapli.base.messages.domain;

import javax.persistence.Entity;
import java.util.Calendar;
import java.util.Objects;

@Entity
public class ConsumptionMessage extends Message {

    private String product;
    private String quantity;
    private String idDeposit;

    public ConsumptionMessage() {
    }

    public ConsumptionMessage(String idMachine, MessageType type, Calendar generatedOn, String product, String quantity, String idDeposit) {
        super(idMachine, type, generatedOn);
        this.product = product;
        this.quantity = quantity;
        this.idDeposit = idDeposit;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getIdDeposit() {
        return idDeposit;
    }

    public void setIdDeposit(String idDeposit) {
        this.idDeposit = idDeposit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConsumptionMessage)) return false;
        if (!super.equals(o)) return false;
        ConsumptionMessage that = (ConsumptionMessage) o;
        return product.equals(that.product) &&
                quantity.equals(that.quantity) &&
                idDeposit.equals(that.idDeposit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), product, quantity, idDeposit);
    }

    @Override
    public String toString() {
        return "ConsumptionMessage{" +
                "product='" + product + '\'' +
                ", quantity='" + quantity + '\'' +
                ", idDeposit='" + idDeposit + '\'' +
                '}';
    }
}

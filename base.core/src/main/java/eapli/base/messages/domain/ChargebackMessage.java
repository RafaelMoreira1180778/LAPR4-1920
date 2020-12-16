package eapli.base.messages.domain;

import javax.persistence.Entity;
import java.util.Calendar;
import java.util.Objects;

@Entity
public class ChargebackMessage extends Message {

    private String product;
    private String quantity;
    private String deposit;

    public ChargebackMessage() {
    }

    public ChargebackMessage(String idMachine, MessageType type, Calendar generatedOn, String product, String quantity, String deposit) {
        super(idMachine, type, generatedOn);
        this.product = product;
        this.quantity = quantity;
        this.deposit = deposit;
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

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChargebackMessage)) return false;
        if (!super.equals(o)) return false;
        ChargebackMessage that = (ChargebackMessage) o;
        return product.equals(that.product) &&
                quantity.equals(that.quantity) &&
                deposit.equals(that.deposit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), product, quantity, deposit);
    }

    @Override
    public String toString() {
        return "ChargebackMessage{" +
                "product='" + product + '\'' +
                ", quantity='" + quantity + '\'' +
                ", deposit='" + deposit + '\'' +
                '}';
    }
}

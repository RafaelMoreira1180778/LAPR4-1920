package eapli.base.messages.domain;

import javax.persistence.Entity;
import java.util.Calendar;
import java.util.Objects;

@Entity
public class ProductionMessage extends Message {

    private String product;
    private String quantity;
    private String lot;

    public ProductionMessage() {
    }

    public ProductionMessage(String idMachine, MessageType type, Calendar generatedOn, String product, String quantity, String lot) {
        super(idMachine, type, generatedOn);
        this.product = product;
        this.quantity = quantity;
        this.lot = lot;
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

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductionMessage)) return false;
        if (!super.equals(o)) return false;
        ProductionMessage that = (ProductionMessage) o;
        return product.equals(that.product) &&
                quantity.equals(that.quantity) &&
                lot.equals(that.lot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), product, quantity, lot);
    }

    @Override
    public String toString() {
        return "ProductionMessage{" +
                "product='" + product + '\'' +
                ", quantity='" + quantity + '\'' +
                ", lot='" + lot + '\'' +
                '}';
    }
}

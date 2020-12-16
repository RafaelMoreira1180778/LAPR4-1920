package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Quantidades implements ValueObject, Comparable<Quantidades> {

    @Id
    private String unidade;

    public Quantidades() {
    }

    public Quantidades(String un) {
        this.unidade = un;
    }

    @Override
    public String toString() {
        return "Quantidades: \n" +
                "   - Unidade: " + unidade + "\n";
    }

    @Override
    public int compareTo(Quantidades o) {
        return this.unidade.compareTo(o.unidade);
    }

    public String getUnidade() {
        return unidade;
    }

}

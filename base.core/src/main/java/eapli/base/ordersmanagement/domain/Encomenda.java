package eapli.base.ordersmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Encomenda implements AggregateRoot<String> {

    @Id
    private String idEncomenda;
    private ContentorOrdensProducao ordens;

    public Encomenda() {

    }

    public Encomenda(String id, ContentorOrdensProducao op) {
        this.idEncomenda = id;
        this.ordens = op;
    }

    @Override
    public String toString() {
        return "Encomenda: \n" +
                "   - ID da Encomenda: " + idEncomenda + "\n" +
                ordens +
                "-------------------";
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public String identity() {
        return this.idEncomenda;
    }

    public String getIdEncomenda() {
        return idEncomenda;
    }

    public ContentorOrdensProducao getOrdens() {
        return ordens;
    }
}

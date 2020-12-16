package eapli.base.depositsmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Depositos implements AggregateRoot<String>{

    @Id
    private String id_Deposito;
    private Descricao_Deposito descricao;

    public Depositos() {
    }

    public Depositos(String idD, Descricao_Deposito descricao) {

        this.id_Deposito = idD;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Deposito {" +
                "id_Deposito= " + id_Deposito + '\'' +
                "descricao " + descricao +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_Deposito);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public String identity() {
        return this.id_Deposito;
    }

    public String getId_Deposito() {
        return id_Deposito;
    }

    public Descricao_Deposito getDescricao() {
        return descricao;
    }
}

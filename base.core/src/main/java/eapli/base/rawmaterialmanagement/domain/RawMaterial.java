package eapli.base.rawmaterialmanagement.domain;

import eapli.base.utils.Description;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class RawMaterial implements AggregateRoot<String> {

    @Id
    private String id_MateriaPrima;
    private Description description;

    public RawMaterial() {
    }

    public RawMaterial(String idP, Description descriptionP) {
        Preconditions.nonEmpty(idP, "ID invalido para a materia prima");
        this.id_MateriaPrima = idP;
        this.description = descriptionP;
    }

    @Override
    public String toString() {
        return "RawMaterial{" +
                "id_MateriaPrima='" + id_MateriaPrima + '\'' +
                ", descricao=" + description +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_MateriaPrima, description);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public String identity() {
        return this.id_MateriaPrima;
    }

    public String getId() {
        return this.id_MateriaPrima;
    }

    public Description getDescription() {
        return description;
    }
}

package eapli.base.rawmaterialmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class CategoriaMateriaPrima implements AggregateRoot<String> {

    @Id
    private String id_Categoria;
    @OneToMany
    private List<RawMaterial> listaMateriasPrimas;

    public CategoriaMateriaPrima() {
    }

    public CategoriaMateriaPrima(String id_CategoriaP) {
        Preconditions.nonEmpty(id_CategoriaP, "ID invalido para a categoria de materias primas");
        this.id_Categoria = id_CategoriaP;
        listaMateriasPrimas = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_Categoria);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public String identity() {
        return this.id_Categoria;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id_Categoria='" + id_Categoria + '\'' +
                ", listaMateriasPrimas=" + listaMateriasPrimas +
                '}';
    }

    public String getId() {
        return this.id_Categoria;
    }

    public List<RawMaterial> getListaMateriasPrimas() {
        return listaMateriasPrimas;
    }

    public boolean adicionarMateriaPrima(RawMaterial mp) {
        return listaMateriasPrimas.add(mp);
    }

}

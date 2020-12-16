package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoriaProduto implements DomainEntity<String>, AggregateRoot<String> {

    @Id
    private String idCategoria;
    @OneToMany
    @Column
    private List<Produto> listProdutos;

    public CategoriaProduto() {
    }

    public CategoriaProduto(String idCategoria) {
        this.idCategoria = idCategoria;
        listProdutos = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategoria);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public String identity() {
        return this.idCategoria;
    }

    @Override
    public String toString() {
        return "Categoria de Produto: \n" +
                "   - ID da Categoria: " + idCategoria + "\n" +
                "   - Lista de Produtos: " + listProdutos + "\n";
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public List<Produto> getListProdutos() {
        return listProdutos;
    }

    public boolean adicionarProduto(Produto prod) {
        return listProdutos.add(prod);
    }

}

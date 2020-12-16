package eapli.base.productmanagement.domain;

import eapli.base.utils.Description;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Produto implements AggregateRoot<String> {

    private FichaProducao fichaProducao;
    @Id
    private String idProduto;
    private String idComercial;
    private Lote lote;
    private Description descriptionBreve;
    private Description descriptionCompleta;
    private Quantidades unidade;
    private CategoriaProduto categoria;

    public Produto() {
    }

    public Produto(FichaProducao fichaProducao, String idProduto, String idComercial, Description descriptionBreve, Description descriptionCompleta, Quantidades qtd, CategoriaProduto cat) {
        this.fichaProducao = fichaProducao;
        this.idProduto = idProduto;
        this.idComercial = idComercial;
        this.descriptionBreve = descriptionBreve;
        this.descriptionCompleta = descriptionCompleta;
        this.unidade = qtd;
        this.categoria = cat;
    }

    public Produto(String idProduto, String idComercial, Description descriptionBreve, Description descriptionCompleta, Quantidades qtd, CategoriaProduto cat) {
        this.idProduto = idProduto;
        this.idComercial = idComercial;
        this.descriptionBreve = descriptionBreve;
        this.descriptionCompleta = descriptionCompleta;
        this.unidade = qtd;
        this.categoria = cat;
    }

    @Override
    public String toString() {
        return "Produto: \n" +
                "   - Ficha de Producao: " + fichaProducao + "\n" +
                "   - ID do Produto: " + idProduto + "\n" +
                "   - ID Comercial: " + idComercial + "\n" +
                "   - Descricao Breve: " + descriptionBreve + "\n" +
                "   - Descricao Completa: " + descriptionCompleta + "\n" +
                unidade + "\n" +
                "   - Categoria: " + categoria + "\n" +
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
        return this.idProduto;
    }

    public FichaProducao getFichaProducao() {
        return fichaProducao;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public String getIdComercial() {
        return idComercial;
    }

    public Description getDescriptionBreve() {
        return descriptionBreve;
    }

    public Description getDescriptionCompleta() {
        return descriptionCompleta;
    }

    public Quantidades getUnidade() {
        return unidade;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }
}

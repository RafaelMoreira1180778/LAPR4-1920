package eapli.base.productmanagement.domain;

import eapli.base.notifications.domain.Notification;
import eapli.base.notifications.domain.NotificationBuilder;
import eapli.base.notifications.domain.NotificationType;
import eapli.base.utils.Description;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.time.util.Calendars;

import java.util.Calendar;

public class ProdutoBuilder implements DomainFactory<Produto> {

    private Calendar createdOn;
    private String idProduto;
    private String idComercial;
    private Description descriptionBreve;
    private Description descriptionCompleta;
    private Quantidades unidade;
    private CategoriaProduto categoria;

    public eapli.base.productmanagement.domain.ProdutoBuilder ProdutoBuilder() {
        return this;
    }

    public eapli.base.productmanagement.domain.ProdutoBuilder withData(String idProduto, String idComercial, Description descriptionBreve, Description descriptionCompleta, Quantidades unidade, CategoriaProduto categoria) {
        this.idProduto = idProduto;
        this.idComercial = idComercial;
        this.descriptionBreve = descriptionBreve;
        this.descriptionCompleta = descriptionCompleta;
        this.unidade = unidade;
        this.categoria = categoria;
        return this;
    }

    public eapli.base.productmanagement.domain.ProdutoBuilder withProdutoID(String id) {
        this.idProduto = id;
        return this;
    }

    public eapli.base.productmanagement.domain.ProdutoBuilder withComercialID(String id) {
        this.idComercial = id;
        return this;
    }

    public eapli.base.productmanagement.domain.ProdutoBuilder withDescb(Description descriptionBreve) {
        this.descriptionBreve = descriptionBreve;
        return this;
    }

    public eapli.base.productmanagement.domain.ProdutoBuilder withDescc(Description descriptionCompleta) {
        this.descriptionCompleta = descriptionCompleta;
        return this;
    }

    public eapli.base.productmanagement.domain.ProdutoBuilder withDescription(Quantidades unidade) {
        this.unidade = unidade;
        return this;
    }

    public eapli.base.productmanagement.domain.ProdutoBuilder withStatus(CategoriaProduto categoria) {
        this.categoria = categoria;
        return this;
    }

    public ProdutoBuilder withCreatedOn(Calendar createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    @Override
    public Produto build() {
        if (createdOn != null) {
            this.createdOn = Calendars.now();
        }
        return new Produto(idProduto, idComercial, descriptionBreve, descriptionCompleta, unidade, categoria);
    }
}
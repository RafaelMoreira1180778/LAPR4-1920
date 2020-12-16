package eapli.base.rawmaterialmanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.time.util.Calendars;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CategoriaMateriaPrimaBuilder implements DomainFactory<CategoriaMateriaPrima> {

    private Calendar createdOn;
    private String id_Categoria;

    public CategoriaMateriaPrimaBuilder CategoriaMateriaPrimaBuilder() {
        return this;
    }

    public CategoriaMateriaPrimaBuilder withData(String id_Categoria) {
        withId(id_Categoria);
        return this;
    }

    public CategoriaMateriaPrimaBuilder withId(String id_Categoria) {
        this.id_Categoria = id_Categoria;
        return this;
    }

    @Override
    public CategoriaMateriaPrima build() {
        if (createdOn != null) {
            this.createdOn = Calendars.now();
        }
        return new CategoriaMateriaPrima(this.id_Categoria);
    }
}

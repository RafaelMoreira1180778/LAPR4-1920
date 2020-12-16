package eapli.base.rawmaterialmanagement.domain;

import eapli.base.utils.Description;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.time.util.Calendars;

import java.util.Calendar;

public class RawMaterialBuilder implements DomainFactory<RawMaterial> {

    private Calendar createdOn;
    private String id_MateriaPrima;
    private Description description;

    public RawMaterialBuilder RawMaterialBuilder() {
        return this;
    }

    public RawMaterialBuilder withData(String id_MateriaPrima, Description description) {
        this.description = description;
        this.id_MateriaPrima = id_MateriaPrima;
        return this;
    }

    public RawMaterialBuilder witId(String id_MateriaPrima) {
        this.id_MateriaPrima = id_MateriaPrima;
        return this;
    }

    public RawMaterialBuilder withDescricao(Description description) {
        this.description = description;
        return this;
    }

    @Override
    public RawMaterial build() {
        if (createdOn != null) {
            this.createdOn = Calendars.now();
        }
        return new RawMaterial(this.id_MateriaPrima, this.description);
    }
}

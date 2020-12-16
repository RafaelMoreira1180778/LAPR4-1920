package eapli.base.utils;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

public class Description implements ValueObject, Comparable<Description> {

    private String descStr;

    public Description() {
    }

    public Description(String descP) {
        Preconditions.nonEmpty(descP, "Descricao invalida");
        this.descStr = descP;
    }

    @Override
    public String toString() {
        return this.descStr;
    }

    @Override
    public int compareTo(Description o) {
        return this.descStr.compareTo(o.descStr);
    }

    public String getDescription(){ return this.descStr; }

}

package eapli.base.depositsmanagement.domain;

import eapli.framework.domain.model.ValueObject;

public class Descricao_Deposito implements ValueObject, Comparable<eapli.base.depositsmanagement.domain.Descricao_Deposito> {

    private String descStr;

    public Descricao_Deposito() {
    }

    public Descricao_Deposito(String descP) {
        this.descStr = descP;
    }

    @Override
    public String toString() {
        return "Descricao_Deposito{" +
                "descStr='" + descStr + '\'' +
                '}';
    }

    @Override
    public int compareTo(Descricao_Deposito o) {
        return this.descStr.compareTo(o.descStr);
    }

    public String getDescStr() {
        return descStr;
    }
}

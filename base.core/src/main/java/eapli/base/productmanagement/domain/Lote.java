package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;

public class Lote implements ValueObject, Comparable<Lote> {

    private String idLote;
    private String descStr;

    public Lote() {
    }

    public Lote(String id) {
        this.idLote = id;
    }

    public Lote(String id, String desc) {
        this.idLote = id;
        this.descStr = desc;
    }

    @Override
    public String toString() {
        return "Lote: \n" +
                "   - ID do Lote: " + descStr + "\n";
    }

    @Override
    public int compareTo(Lote o) {
        return this.idLote.compareTo(o.idLote);
    }

    public String getIdLote() {
        return idLote;
    }

    public String getDescStr() {
        return descStr;
    }
}

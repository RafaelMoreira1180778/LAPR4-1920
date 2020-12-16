package eapli.base.ordersmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import java.util.ArrayList;
import java.util.List;

public class ContentorOrdensProducao implements ValueObject {

    private List<String> ops;

    public ContentorOrdensProducao () {
        this.ops = new ArrayList<>();
    }

    public ContentorOrdensProducao (ArrayList<String> ops) {
        this.ops = ops;
    }

    public List<String> getOps() {
        return this.ops;
    }

    @Override
    public String toString() {
        return "IDs das Ordens de Produção da Encomenda: " + ops + "\n" +
                "-------------------";
    }
}

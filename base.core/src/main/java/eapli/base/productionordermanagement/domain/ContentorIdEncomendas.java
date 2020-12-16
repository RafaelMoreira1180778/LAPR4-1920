package eapli.base.productionordermanagement.domain;

import eapli.framework.domain.model.ValueObject;

import java.util.ArrayList;
import java.util.List;

public class ContentorIdEncomendas implements ValueObject {

    private List<String> ids;

    public ContentorIdEncomendas () {
        this.ids = new ArrayList<>();
    }

    public ContentorIdEncomendas (ArrayList<String> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "IDs das Encomendas: " + ids + "\n" +
                "-------------------";
    }

    public List<String> getIds() {
        return ids;
    }
}

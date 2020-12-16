package eapli.base.productmanagement.domain;

import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FichaProducao implements Comparable<String>, AggregateRoot<String> {

    @Id
    private String idFichaProducao;

    @OneToMany
    private List<RawMaterial> listRawMaterial;

    @OneToMany
    private List<Quantidades> listQuantidades;

    public FichaProducao() {
    }

    public FichaProducao(String id) {
        this.idFichaProducao = id;
        this.listRawMaterial = new ArrayList<>();
        this.listQuantidades = new ArrayList<>();
    }

    public FichaProducao(String id, ArrayList<RawMaterial> listRawMaterial, ArrayList<Quantidades> listQuantidades) {
        this.idFichaProducao = id;
        this.listRawMaterial = listRawMaterial;
        this.listQuantidades = listQuantidades;
    }

    @Override
    public String toString() {
        return "Ficha de Producao {" +
                "ID da ficha de produção: " + idFichaProducao + '\n' +
                "lista de materias prima: " + listRawMaterial + '\n' +
                "lista da quantidade de materias prima: " + listQuantidades + '\n' +
                '}';
    }


    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public String identity() {
        return null;
    }

    public String getIdFichaProducao() {
        return idFichaProducao;
    }

    public List<RawMaterial> getListRawMaterial() {
        return listRawMaterial;
    }

    public List<Quantidades> getListQuantidades() {
        return listQuantidades;
    }
}

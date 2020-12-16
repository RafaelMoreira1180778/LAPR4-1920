package eapli.base.productionlinemanagement.domain;

import eapli.base.machinemanagement.domain.Maquina;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class LinhaProducao implements AggregateRoot<String> {
    @Id
    private String id_LinhaProducao;
    private EstadoProcessamento estadoProcessamento;
    private Date ultimaOcorrencia;
    @OneToMany
    @Column
    private List<Maquina> listaMaquinas;

    public LinhaProducao() {
    }

    public LinhaProducao(String idLP, EstadoProcessamento est, Date uo) {
        this.id_LinhaProducao = idLP;
        listaMaquinas = new ArrayList<Maquina>();
        this.estadoProcessamento = est;
        this.ultimaOcorrencia = uo;
    }

    @Override
    public String toString() {
        return "Linha Producao{" +
                "id_LinhaProducao='" + id_LinhaProducao + '\'' +
                "estado atual do processamento='" + estadoProcessamento + '\'' +
                "data do ultimo processamento='" + ultimaOcorrencia + '\'' +
                ", listaMaquinas=" + listaMaquinas +
                '}';
    }

    public void setEstadoProcessamento(EstadoProcessamento novoEstado) {
        this.estadoProcessamento = novoEstado;
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_LinhaProducao);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public String identity() {
        return this.id_LinhaProducao;
    }

    public String getIdLinhaProducao() {
        return id_LinhaProducao;
    }

    public List<Maquina> getListaMaquinas() {
        return listaMaquinas;
    }

    public boolean adicionarMaquina(Maquina m) {
        return this.listaMaquinas.add(m);
    }

}

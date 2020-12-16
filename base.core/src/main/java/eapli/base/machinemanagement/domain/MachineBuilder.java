package eapli.base.machinemanagement.domain;

import eapli.base.utils.Description;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.time.util.Calendars;

import java.util.Calendar;
import java.util.Date;

public class MachineBuilder implements DomainFactory<Maquina> {

    private String idMaquina;
    private int numSerie;
    private String fabricante;
    private String modelo;
    private Date anoFabrico;
    private Description description;
    private Timestamp tsInstalacao;
    private Calendar createdOn;
    private int uniqueID;

    public MachineBuilder MachineBuilder() {
        return this;
    }

    public MachineBuilder withData(final String idMaquina, final int numSerie, final String fabricante,
                                   final String modelo, final Date anoFabrico, final Description decricao, final Timestamp tsInstalacao) {
        withId(idMaquina);
        withNumSerie(numSerie);
        withFabricante(fabricante);
        withModelo(modelo);
        withAnoFabrico(anoFabrico);
        withDescricao(description);
        withTsInstalacao(tsInstalacao);
        return this;
    }

    public MachineBuilder withId(final String idMaquina) {
        this.idMaquina = idMaquina;
        return this;
    }

    public MachineBuilder withUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
        return this;
    }

    public MachineBuilder withNumSerie(final int numSerie) {
        this.numSerie = numSerie;
        return this;
    }

    public MachineBuilder withFabricante(final String fabricante) {
        this.fabricante = fabricante;
        return this;
    }

    public MachineBuilder withModelo(final String modelo) {
        this.modelo = modelo;
        return this;
    }

    public MachineBuilder withAnoFabrico(final Date anoFabrico) {
        this.anoFabrico = anoFabrico;
        return this;
    }

    public MachineBuilder withDescricao(final Description description) {
        this.description = description;
        return this;
    }

    public MachineBuilder withTsInstalacao(final Timestamp tsInstalacao) {
        this.tsInstalacao = tsInstalacao;
        return this;
    }

    @Override
    public Maquina build() {

        if (createdOn != null) {
            this.createdOn = Calendars.now();
        }

        Maquina m = new Maquina(this.idMaquina, this.numSerie, this.fabricante, this.modelo, this.anoFabrico, this.description,
                this.tsInstalacao);
        if (this.uniqueID != 0) m.setUniqueID(this.uniqueID);

        return m;
    }

}
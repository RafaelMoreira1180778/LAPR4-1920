package eapli.base.machinemanagement.domain;

import eapli.base.utils.Description;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Maquina implements AggregateRoot<String> {

    @Id
    private String id_Maquina;
    private int uniqueID;
    private int num_Serie;
    private String fabricante;
    private String modelo;
    private Date anoFabrico;
    private Description description;
    private Timestamp ts_instalacao;
    private byte[] config;
    private String ip;
    private MachineState machState;
    private String linhaProducao;

    public Maquina() {
    }

    public Maquina(String id_Maquina, int num_Serie, String fabricante, String modelo, Date anoFabrico, Description description, Timestamp ts_instalacao) {

        Preconditions.nonEmpty(id_Maquina, "ID da maquina invalido");
        Preconditions.isPositive(num_Serie, "Numero de Serie Invalido");
        Preconditions.nonEmpty(modelo, "Fabricante da maquina invalido");
        Preconditions.nonEmpty(modelo, "Modelo da maquina invalido");

        this.id_Maquina = id_Maquina;
        this.num_Serie = num_Serie;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.anoFabrico = anoFabrico;
        this.description = description;
        this.ts_instalacao = ts_instalacao;
        this.linhaProducao = linhaProducao;
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public String identity() {
        return this.id_Maquina;
    }

    public String getId() {
        return this.id_Maquina;
    }

    public int getNum_Serie() {
        return num_Serie;
    }

    public String getFabricante() {
        return fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public Date getAnoFabrico() {
        return anoFabrico;
    }

    public Description getDescription() {
        return description;
    }

    public Timestamp getTs_instalacao() {
        return ts_instalacao;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public byte[] getConfig() {
        return this.config;
    }

    public void setConfig(byte[] configFile) {
        this.config = configFile;
    }

    public MachineState getMachState() {
        return machState;
    }

    public void setMachState(MachineState machState) {
        this.machState = machState;
    }

    public String getLinhaProducao() {
        return linhaProducao;
    }

    public void setLinhaProducao(String linhaProducao) {
        this.linhaProducao = linhaProducao;
    }

    public void activateState() {
        this.machState = MachineState.ON;
    }

    public void deactivateState() {
        this.machState = MachineState.OFF;
    }

    public void updateConfig(String path, Description description) {
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getId_Maquina() {
        return id_Maquina;
    }
}

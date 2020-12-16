package eapli.base.machinemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class Timestamp implements ValueObject, Comparable<Timestamp> {

    private Date data;
    private Time time;

    public Timestamp() {
    }

    public Timestamp(int dia, int mes, int ano, int hora, int minutos) {
        this.data = new Date(ano, mes, dia);
        this.time = new Time(hora, minutos, 0);
    }

    public Timestamp(Date data, Time tempo) {
        this.data = data;
        this.time = tempo;
    }

    @Override
    public String toString() {
        return "Data: " + this.data + " / Hora: " + this.time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Timestamp timestamp = (Timestamp) o;
        return data.equals(timestamp.data) && time.equals(timestamp.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, time);
    }

    @Override
    public int compareTo(Timestamp o) {
        return this.data.compareTo(o.data);
    }

    public Date getData() {
        return data;
    }

    public Time getTime() {
        return time;
    }
}

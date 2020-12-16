package eapli.base.app.spm.application;

import java.util.Date;

public class ProcessingHandler implements Runnable{

    private String idLP;
    private int intervaloTempo;
    private Date inicio;

    public ProcessingHandler(String idLP, int intervaloTempo, Date inicio) {
        this.idLP = idLP;
        this.intervaloTempo = intervaloTempo;
        this.inicio = inicio;
    }

    @Override
    public void run() {

    }
}

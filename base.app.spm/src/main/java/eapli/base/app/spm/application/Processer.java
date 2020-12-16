package eapli.base.app.spm.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.messages.repositories.MessagesRepository;
import eapli.base.productionlinemanagement.repositories.LinhaProducaoRepository;

public class Processer implements Runnable{

    private MessagesRepository mr;
    private LinhaProducaoRepository lpr;

    @Override
    public void run() {

        mr = PersistenceContext.repositories().messagesManagement();
        lpr = PersistenceContext.repositories().linhaProducaoManagement();
    }
}

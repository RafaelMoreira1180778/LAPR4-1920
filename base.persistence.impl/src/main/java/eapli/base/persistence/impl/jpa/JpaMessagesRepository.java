package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.messages.domain.Message;
import eapli.base.messages.repositories.MessagesRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaMessagesRepository extends JpaAutoTxRepository<Message, String, String> implements MessagesRepository {

    public JpaMessagesRepository(TransactionalContext autoTx) {
        super(autoTx, "idMessage");
    }

    public JpaMessagesRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "idMessage");
    }
}

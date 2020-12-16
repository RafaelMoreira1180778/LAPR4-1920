package eapli.base.messages.repositories;

import eapli.base.messages.domain.Message;
import eapli.framework.domain.repositories.DomainRepository;

public interface MessagesRepository extends DomainRepository<String, Message> {
}

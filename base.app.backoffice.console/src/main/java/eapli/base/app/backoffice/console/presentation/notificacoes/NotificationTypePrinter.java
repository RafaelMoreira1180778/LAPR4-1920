package eapli.base.app.backoffice.console.presentation.notificacoes;

import eapli.base.notifications.domain.NotificationType;
import eapli.framework.visitor.Visitor;

/**
 * @author Paulo Moreira 1180778
 */
public class NotificationTypePrinter implements Visitor<NotificationType> {
    @Override
    public void visit(NotificationType visitee) {
        System.out.println(visitee.toString());
    }
}

package eapli.base.notifications.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.notifications.domain.Notification;
import eapli.base.notifications.repositories.NotificationsRepository;
import eapli.base.productionlinemanagement.domain.LinhaProducao;
import eapli.base.productionlinemanagement.repositories.LinhaProducaoRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import java.util.List;

public class ArquivarNotificacoesErroController {

    private final LinhaProducaoRepository lpr = PersistenceContext.repositories().linhaProducaoManagement();
    private final NotificationsRepository nr = PersistenceContext.repositories().notificationManagement();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public Iterable<Notification> listAllNotifications() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_CHAO_FABRICA);
        return this.nr.findAll();
    }

    public Iterable<LinhaProducao> listAllLPS() {
        return this.lpr.findAll();
    }

    public List<Notification> listNotificationsByPL(LinhaProducao lp) {
        String lpID = lp.identity();
        return nr.listByProductionLine(lpID);
    }

    public void arquiveNotifications(List<Notification> ln) {
        for (Notification n : this.nr.findAll()) {
            n.setStatus(false);
        }
    }

}

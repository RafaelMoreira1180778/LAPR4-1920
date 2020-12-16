package eapli.base.notifications.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.notifications.domain.Notification;
import eapli.base.notifications.domain.NotificationType;
import eapli.base.notifications.repositories.NotificationsRepository;
import eapli.base.productionlinemanagement.domain.LinhaProducao;
import eapli.base.productionlinemanagement.repositories.LinhaProducaoRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@UseCaseController
public class ListErrorNotificationArchController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final NotificationsRepository nr = PersistenceContext.repositories().notificationManagement();
    private final LinhaProducaoRepository lpr = PersistenceContext.repositories().linhaProducaoManagement();

    public ListErrorNotificationArchController() {
    }

    public Iterable<Notification> listAllNotifications() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_CHAO_FABRICA);
        return this.nr.findAll();
    }

        public List<Notification> listNotificationsBetweenDates(Calendar start, Calendar end) {

            List<Notification> tmp = new ArrayList<>();

            for (Notification n : this.nr.findAll()) {
                Calendar cd = n.getCreationDate();
                if (cd.after(start) && cd.before(end)) {
                    tmp.add(n);
                }
            }

            return tmp;
        }

        public void listByIdMachine(String idMachine) {
            List<Notification> listmachine = nr.listByIdMachine(idMachine);
            System.out.println(listmachine.toString());
        }

        public List<Notification> listNotificationsByPL(LinhaProducao lp) {
            String lpID = lp.identity();
            return nr.listByProductionLine(lpID);
        }

        public void listByDescription(String description) {
            List<Notification> listdes = nr.listByDescription(description);
            System.out.println(listdes.toString());
        }

        public void arquiveNotifications(List<Notification> ln) {
            for (Notification n : this.nr.findAll()) {
                n.setStatus(false);
            }
        }

        public List<Notification> listNotificationsByType(NotificationType type) {
            return nr.listByType(type);
        }

        public Iterable<NotificationType> listAllNotificationTypes() {
            NotificationType[] ntEnum = NotificationType.values();
            List<NotificationType> ntList = Arrays.asList(ntEnum);
            return ntList;
        }

    public String listAllDetails(Notification n) {
            return n.toString();
        }

        public Iterable<LinhaProducao> listAllLPS() {
            return this.lpr.findAll();
        }

}


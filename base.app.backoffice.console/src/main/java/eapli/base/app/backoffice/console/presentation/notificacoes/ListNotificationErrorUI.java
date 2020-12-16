package eapli.base.app.backoffice.console.presentation.notificacoes;

import eapli.base.app.backoffice.console.presentation.maquinas.LinhasProdPrinter;
import eapli.base.notifications.application.ListErrorNotificationController;
import eapli.base.notifications.domain.Notification;
import eapli.base.notifications.domain.NotificationType;
import eapli.base.productionlinemanagement.domain.LinhaProducao;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Paulo Moreira 1180778
 */
public class ListNotificationErrorUI extends AbstractUI {

    private final ListErrorNotificationController controller = new ListErrorNotificationController();

    private final String FILTER_BY_DATE = "Filtrar por Data";
    private final String FILTER_BY_PRODUCTIONLINE = "Filtrar por Linha de Producao";
    private final String FILTER_BY_NOTIFICATIONTYPE = "Filtrar por Tipo de Erro";

    @Override
    protected boolean doShow() {

        List<String> optionsList = new ArrayList();
        optionsList.add(FILTER_BY_DATE);
        optionsList.add(FILTER_BY_PRODUCTIONLINE);
        optionsList.add(FILTER_BY_NOTIFICATIONTYPE);

        final SelectWidget<String> selector = new SelectWidget<>("Selecionar Categoria desta MP:", optionsList);
        selector.show();
        String selectedOption = selector.selectedElement();

        if (selectedOption.equals(FILTER_BY_DATE)) {

            Calendar start = Console.readCalendar("Data de inicio do intervalo");
            Calendar end = Console.readCalendar("Data de fim do intervalo");

            listDetails(this.controller.listNotificationsBetweenDates(start, end));
        } else if (selectedOption.equals(FILTER_BY_PRODUCTIONLINE)) {

            Iterable<LinhaProducao> lplist = controller.listAllLPS();
            final SelectWidget<LinhaProducao> selectorLP = new SelectWidget<>("Selecionar linha de producao", lplist, new LinhasProdPrinter());
            selectorLP.show();
            final LinhaProducao lp = selectorLP.selectedElement();
            System.out.println(lp.identity());

            listDetails(this.controller.listNotificationsByPL(lp));
        } else if (selectedOption.equals(FILTER_BY_NOTIFICATIONTYPE)) {

            Iterable<NotificationType> ntList = controller.listAllNotificationTypes();
            final SelectWidget<NotificationType> selectorNT = new SelectWidget<>("Selecionar o tipo de notificacao", ntList, new NotificationTypePrinter());
            selectorNT.show();
            NotificationType nt = selectorNT.selectedElement();

            listDetails(this.controller.listNotificationsByType(nt));

        }

        return true;
    }

    @Override
    public String headline() {
        return "Listar Notificações de Erros";
    }

    private void listDetails(Iterable<Notification> l) {
        final SelectWidget<Notification> selectorDetails = new SelectWidget<>("Notificações que cumprem os parametros:", l);
        selectorDetails.show();
    }
}

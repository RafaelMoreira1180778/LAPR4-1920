package eapli.base.app.backoffice.console.presentation.notificacoes;

import eapli.base.app.backoffice.console.presentation.maquinas.LinhasProdPrinter;
import eapli.base.notifications.application.ArquivarNotificacoesErroController;
import eapli.base.productionlinemanagement.domain.LinhaProducao;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class ArquivarNotificacoesErroUI extends AbstractUI {

    private final ArquivarNotificacoesErroController controller = new ArquivarNotificacoesErroController();

    @Override
    public String headline() {
        return "Arquivar notificações de erros";
    }

    @Override
    protected boolean doShow() {

        Iterable<LinhaProducao> lplist = controller.listAllLPS();
        final SelectWidget<LinhaProducao> selectorLP = new SelectWidget<>("Selecionar linha de producao", lplist, new LinhasProdPrinter());
        selectorLP.show();
        final LinhaProducao lp = selectorLP.selectedElement();
        System.out.println(lp.identity());

        this.controller.arquiveNotifications(this.controller.listNotificationsByPL(lp));

        return true;
    }



}
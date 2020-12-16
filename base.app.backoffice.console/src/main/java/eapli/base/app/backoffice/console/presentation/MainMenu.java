/*
 * Copyright (c) 2013-2019 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.app.backoffice.console.presentation;

import eapli.base.Application;
import eapli.base.app.backoffice.console.presentation.authz.AddUserUI;
import eapli.base.app.backoffice.console.presentation.authz.DeactivateUserAction;
import eapli.base.app.backoffice.console.presentation.authz.ListUsersAction;
import eapli.base.app.backoffice.console.presentation.categoria.AdicionarCategoriaMateriaPrimaAction;
import eapli.base.app.backoffice.console.presentation.chaofabrica.ExportarChaoFabricaXMLAction;
import eapli.base.app.backoffice.console.presentation.chaofabrica.ExportarXMLToFormatAction;
import eapli.base.app.backoffice.console.presentation.clientuser.AcceptRefuseSignupRequestAction;
import eapli.base.app.backoffice.console.presentation.deposito.AdicionarDepositoAction;
import eapli.base.app.backoffice.console.presentation.encomendas.OrdemProducaoEncomendaAction;
import eapli.base.app.backoffice.console.presentation.linhaproducao.AdicionarLinhaProducaoAction;
import eapli.base.app.backoffice.console.presentation.linhaproducao.ConsultarEstadoAction;
import eapli.base.app.backoffice.console.presentation.maquinas.AddConfigFileAction;
import eapli.base.app.backoffice.console.presentation.maquinas.AdicionarMaquinaAction;
import eapli.base.app.backoffice.console.presentation.materiaprima.AdicionarMateriaPrimaAction;
import eapli.base.app.backoffice.console.presentation.notificacoes.ArquivarNotificationActionError;
import eapli.base.app.backoffice.console.presentation.notificacoes.ListErrorNotificationArchAction;
import eapli.base.app.backoffice.console.presentation.notificacoes.ListNotificationActionError;
import eapli.base.app.backoffice.console.presentation.ordemproducao.AdicionarOrdemProducaoAction;
import eapli.base.app.backoffice.console.presentation.ordemproducao.ImportarOrdensProducaoAction;
import eapli.base.app.backoffice.console.presentation.ordemproducao.OrdemProducaoConsultaAction;
import eapli.base.app.backoffice.console.presentation.produtos.AdicionarProdutoAction;
import eapli.base.app.backoffice.console.presentation.produtos.EspecificarFichaProducaoAction;
import eapli.base.app.backoffice.console.presentation.produtos.ImportarProdutosAction;
import eapli.base.app.backoffice.console.presentation.produtos.ProdutosSemFichaAction;
import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;

    // GCF MENU
    private static final int ADICIONAR_MAQUINA_OPTION = 1;
    private static final int ESPECIFICAR_LINHA_PRODUCAO_OPTION = 2;
    private static final int ADICIONAR_DEPOSITO_OPTION = 3;
    private static final int ADD_CONFIG_FILE = 4;
    private static final int LIST_NOTIFICATION_ERRORS = 5;
    private static final int ARQUIVAR_NOTIFICACOES = 6;
    private static final int LIST_NOTIFICATION_ERRORS_ARCHIVED = 7;
    private static final int CONSULTAR_ESTADO_PROCESSAMENTO_LP = 8;


    //GP MENU
    private static final int ADICIONAR_MATERIAPRIMA_OPTION = 1;
    private static final int DEFINIR_CATEGORIA_MATERIAPRIMA_OPTION = 2;
    private static final int PRODUTOS_SEM_FICHA_PRODUCAO_OPTION = 3;
    private static final int IMPORTAR_PRODUTOS_CSV_OPTION = 4;
    private static final int ADICIONAR_PRODUTO_OPTION = 5;
    private static final int ESPECIFICAR_FICHA_OPTION = 6;
    private static final int ADICIONAR_ORDEM_PRODUCAO_OPTION = 7;
    private static final int IMPORTAR_ORDENS_PRODUCAO_OPTION = 8;
    //private static final int LISTAR_MPS = 8;
    private static final int LISTAR_ORDENS_PRODUCAO_ENCOMENDA_OPTION = 9;
    private static final int CONSULTAR_ORDEM_PRODUCAO_NUM_ESTADO = 10;
    private static final int EXPORTAR_CHAO_FABRICA_XML_OPTION = 11;
    private static final int EXPORTAR_XML_VARIOS_FORMATOS = 12;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int SETTINGS_OPTION = 2;
    private static final int USERS_OPTION = 3;

    private static final String SEPARATOR_LABEL = "--------------";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "Base [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Base [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.POWER_USER, BaseRoles.ADMIN)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_OPTION, usersMenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.GESTOR_CHAO_FABRICA)) {
            final Menu settingsMenu = buildGCFMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.GESTOR_PRODUCAO)) {
            final Menu settingsMenu = buildGPMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Thanks for using our app :)"));

        return mainMenu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        menu.addItem(ADD_USER_OPTION, "Add User", new AddUserUI()::show);
        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
        menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
        menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request", new AcceptRefuseSignupRequestAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildGCFMenu() {
        final Menu menu = new Menu("Gerir Chao de Fabrica >");

        menu.addItem(ADICIONAR_MAQUINA_OPTION, "Adicionar Maquina", new AdicionarMaquinaAction());
        menu.addItem(ESPECIFICAR_LINHA_PRODUCAO_OPTION, "Especificar Linha de Producao", new AdicionarLinhaProducaoAction());
        menu.addItem(ADICIONAR_DEPOSITO_OPTION, "Adicionar Depósito", new AdicionarDepositoAction());
        menu.addItem(ADD_CONFIG_FILE, "Adicionar Ficheiro Configuracao", new AddConfigFileAction());

        menu.addItem(LIST_NOTIFICATION_ERRORS, "Listar Notificacoes de Erros", new ListNotificationActionError());

        menu.addItem(ARQUIVAR_NOTIFICACOES, "Arquivar Notificação de Erros", new ArquivarNotificationActionError());
        menu.addItem(LIST_NOTIFICATION_ERRORS_ARCHIVED, "Listar Notificacoes de Erros Arquivados", new ListErrorNotificationArchAction());

        menu.addItem(CONSULTAR_ESTADO_PROCESSAMENTO_LP, "Consultar Estado de Processamento de Mensagens", new ConsultarEstadoAction());

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildGPMenu() {
        final Menu menu = new Menu("Gerir Producao >");

        menu.addItem(ADICIONAR_MATERIAPRIMA_OPTION, "Adicionar Materia Prima", new AdicionarMateriaPrimaAction());
        menu.addItem(DEFINIR_CATEGORIA_MATERIAPRIMA_OPTION, "Definir Categoria Materia Prima", new AdicionarCategoriaMateriaPrimaAction());
        menu.addItem(PRODUTOS_SEM_FICHA_PRODUCAO_OPTION, "Consultar Produtos sem Ficha de Producao", new ProdutosSemFichaAction());
        menu.addItem(IMPORTAR_PRODUTOS_CSV_OPTION, "Importar Produtos A Partir de Ficheiro", new ImportarProdutosAction());
        menu.addItem(ADICIONAR_PRODUTO_OPTION, "Adicionar Produto", new AdicionarProdutoAction());
        menu.addItem(ESPECIFICAR_FICHA_OPTION, "Especificar Ficha de Producao", new EspecificarFichaProducaoAction());
        menu.addItem(ADICIONAR_ORDEM_PRODUCAO_OPTION, "Adicionar Ordem de Produção", new AdicionarOrdemProducaoAction());
        menu.addItem(IMPORTAR_ORDENS_PRODUCAO_OPTION, "Importar Ordens de Produção", new ImportarOrdensProducaoAction());
        menu.addItem(LISTAR_ORDENS_PRODUCAO_ENCOMENDA_OPTION, "Listar as Ordens de Produção de uma Encomenda", new OrdemProducaoEncomendaAction());
        menu.addItem(CONSULTAR_ORDEM_PRODUCAO_NUM_ESTADO, "Consultar ordens de producao num estado", new OrdemProducaoConsultaAction());
        menu.addItem(EXPORTAR_CHAO_FABRICA_XML_OPTION, "Exportar Chao de Fabrica para XML", new ExportarChaoFabricaXMLAction());
        menu.addItem(EXPORTAR_XML_VARIOS_FORMATOS, "Exportar Ficheiro XML para outro formato", new ExportarXMLToFormatAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }


}

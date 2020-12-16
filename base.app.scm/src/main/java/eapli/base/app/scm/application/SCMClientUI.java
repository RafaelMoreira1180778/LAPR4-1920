package eapli.base.app.scm.application;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

public class SCMClientUI extends AbstractUI {

    private final SCMClient client = new SCMClient();

    @Override
    protected boolean doShow() {

        String ip = Console.readLine("IP da Maquina IP");
        String filepath = Console.readLine("File Path");

        try {
            this.client.sendRequest(ip, filepath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String headline() {
        return "Machine Configuration";
    }
}
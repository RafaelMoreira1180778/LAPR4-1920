package eapli.base.app.scm.utils;

import eapli.base.messages.domain.*;
import eapli.framework.util.Console;

import java.util.Calendar;

public class Utils {

    private static final int PARAMETERS_CONSUMPTION = 6;
    private static final int PARAMETERS_PRODUCTIONDELIVERY = 6;
    private static final int PARAMETERS_PRODUCTION = 6;
    private static final int PARAMETERS_CHARGEBACK = 6;
    private static final int PARAMETERS_STARTACTIVITY = 4;
    private static final int PARAMETERS_RETURNACTIVITY = 4;
    private static final int PARAMETERS_ACTIVITYSTOP = 3;
    private static final int PARAMETERS_ENDOFACTIVITY = 4;


    public static Message createMessages(String s) {

        String[] fileLine = s.split(";");
        final int numParameters = fileLine.length;

        final String idMachine = fileLine[0];
        final MessageType type = getMessageType(fileLine[1]);
        final Calendar createdOn = readDate(fileLine[2]);

        switch (fileLine[1]) {
            case ("C0"):
                if (numParameters == PARAMETERS_CONSUMPTION) {
                    final String product = fileLine[3];
                    final String quantity = fileLine[4];
                    final String idDeposit = fileLine[5];
                    return new ConsumptionMessage(idMachine, type, createdOn, product, quantity, idDeposit);
                }
            case ("C9"):
                if (numParameters == PARAMETERS_PRODUCTIONDELIVERY) {
                    final String product = fileLine[3];
                    final String quantity = fileLine[4];
                    final String idDeposit = fileLine[5];
                    return new ProductionDeliveryMessage(idMachine, type, createdOn, product, quantity, idDeposit);
                }
            case ("P1"):
                if (numParameters == PARAMETERS_PRODUCTION) {
                    final String product = fileLine[3];
                    final String quantity = fileLine[4];
                    final String lot = fileLine[5];
                    return new ProductionMessage(idMachine, type, createdOn, product, quantity, lot);
                }
            case ("P2"):
                if (numParameters == PARAMETERS_CHARGEBACK) {
                    final String product = fileLine[3];
                    final String quantity = fileLine[4];
                    final String idDeposit = fileLine[5];
                    return new ChargebackMessage(idMachine, type, createdOn, product, quantity, idDeposit);
                }
            case ("S0"):
                if (numParameters == PARAMETERS_STARTACTIVITY) {
                    final String productionOrder = fileLine[3];
                    return new StartActivityMessage(idMachine, type, createdOn, productionOrder);
                }
            case ("S1"):
                if (numParameters == PARAMETERS_RETURNACTIVITY) {
                    final String error = fileLine[3];
                    return new ReturnActivityMessage(idMachine, type, createdOn, error);
                }
            case ("S8"):
                if (numParameters == PARAMETERS_ACTIVITYSTOP) {
                    return new ActivityStopMessage(idMachine, type, createdOn);
                }
            case ("S9"):
                if (numParameters == PARAMETERS_ENDOFACTIVITY) {
                    final String productionOrder = fileLine[3];
                    return new EndOfActivityMessage(idMachine, type, createdOn, productionOrder);
                }
        }
        return null;
    }

    private static MessageType getMessageType(String s) {
        switch (s) {
            case ("C0"):
                return MessageType.C0;
            case ("C9"):
                return MessageType.C9;
            case ("P1"):
                return MessageType.P1;
            case ("P2"):
                return MessageType.P2;
            case ("S0"):
                return MessageType.S0;
            case ("S1"):
                return MessageType.S1;
            case ("S8"):
                return MessageType.S8;
            case ("S9"):
                return MessageType.S9;
        }
        return null;
    }

    private static Calendar readDate(String s) {

        final int year = Integer.parseInt(s.substring(0, 4));
        final int month = Integer.parseInt(s.substring(4, 6));
        final int day = Integer.parseInt(s.substring(6, 8));
        final int hour = Integer.parseInt(s.substring(8, 10));
        final int minutes = Integer.parseInt(s.substring(10, 12));
        final int seconds = Integer.parseInt(s.substring(12, 14));

        Calendar c = null;

        c = new Calendar.Builder().setDate(year, month, day).setTimeOfDay(hour, minutes, seconds).build();

        return c;
    }

    public static String getPath(String prompt) {
        String path = Console.readLine(prompt);
        return path.replaceAll("\"", "");
    }


}

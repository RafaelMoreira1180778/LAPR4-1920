package eapli.base.machinemanagement.domain;

import eapli.base.depositsmanagement.domain.Depositos;
import eapli.base.utils.Description;
import eapli.base.utils.FileManager;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class MaquinaTest {

    private final String IDMACHINE_ONE = "ID1";
    private final String IDMACHINE_TWO = "ID2";

    public static Maquina dummyMachine(String id_Maquina, int num_Serie, String fabricante, String modelo, Date anoFabrico, Description description, Timestamp ts_instalacao) {
        final MachineBuilder fb = new MachineBuilder();
        return fb.withData(id_Maquina, num_Serie, fabricante, modelo, anoFabrico, description, ts_instalacao).build();
    }

    private Maquina getDummyMachineOne() {
        return dummyMachine(IDMACHINE_ONE, 1234, "fab", "model", new Date(14, Calendar.MAY, 2000), new Description("desc"), new Timestamp(new Date(22, Calendar.JUNE, 2020), new Time(15, 0, 0)));
    }

    private Maquina getDummyMachineTwo() {
        return dummyMachine(IDMACHINE_TWO, 1234, "fab", "model", new Date(14, Calendar.MAY, 2000), new Description("desc"), new Timestamp(new Date(22, Calendar.JUNE, 2020), new Time(15, 0, 0)));
    }

    @Test
    public void ensureMachineEqualsPassesForTheSameID() {
        final Maquina m1 = getDummyMachineOne();
        final Maquina m2 = getDummyMachineOne();

        final boolean b = m1.equals(m2);

        assertTrue(b);
    }

    @Test
    public void ensureMachineEqualsFailsForDifferentID() {
        final Maquina m1 = getDummyMachineOne();
        final Maquina m2 = getDummyMachineTwo();

        final boolean b = m1.equals(m2);

        assertFalse(b);
    }

    @Test
    public void ensureMachineEqualsFailsForDifferentObjectTypes() {
        final Maquina m = getDummyMachineOne();
        final Depositos d = new Depositos();

        final boolean b = m.equals(d);

        assertFalse(b);
    }

    @Test
    public void ensureMachineIsTheSameAsItsInstance() {
        final Maquina m = getDummyMachineOne();

        final boolean b = m.sameAs(m);

        assertTrue(b);
    }

    @Test
    public void ensureMachineIsntTheSameAsOtherInstance() {
        final Maquina m1 = getDummyMachineOne();
        final Maquina m2 = getDummyMachineTwo();

        final boolean b = m1.sameAs(m2);

        assertFalse(b);
    }

    @Test
    public void ensureMachineConfigurationLoads() {
        ClassLoader cl = getClass().getClassLoader();
        FileManager fm = new FileManager();
        String fPath = null;
        File f;
        try {
            f = Paths.get(cl.getResource("configFile.txt").toURI()).toFile();
            fPath = f.getAbsolutePath();
        } catch (URISyntaxException e) {
            System.out.println("Test file not found for method ensureMachineConfigLoads");
        }

        final Maquina m = getDummyMachineOne();
        m.setConfig(fm.importFile(fPath));

        String expected = "testConfig";
        String actual = new String(m.getConfig(), StandardCharsets.UTF_8);

        assertEquals("ensureMachineConfigLoads: config not equal", expected, actual);
    }

}
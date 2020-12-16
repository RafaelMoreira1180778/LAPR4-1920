package eapli.base.rawmaterialmanagement.domain;

import eapli.base.machinemanagement.domain.Maquina;
import eapli.base.utils.Description;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RawMaterialTest {

    private final String ID_RM_ONE = "ID1";
    private final String ID_RM_TWO = "ID2";

    public static RawMaterial dummyRawMaterial(String idP, Description descriptionP) {

        RawMaterialBuilder rmb = new RawMaterialBuilder();
        return rmb.withData(idP, descriptionP).build();
    }

    private RawMaterial getRawMaterialDummyOne() {
        return dummyRawMaterial(ID_RM_ONE, new Description("desc1"));
    }

    private RawMaterial getRawMaterialDummyTwo() {
        return dummyRawMaterial(ID_RM_TWO, new Description("desc2"));
    }

    @Test
    public void ensureRawMaterialEqualsFailsForDifferentID() {
        final RawMaterial rm1 = getRawMaterialDummyOne();
        final RawMaterial rm2 = getRawMaterialDummyTwo();

        final boolean b = rm1.equals(rm2);

        assertFalse(b);
    }

    @Test
    public void ensureRawMaterialEqualsPassesForSameID() {
        final RawMaterial rm1 = getRawMaterialDummyOne();
        final RawMaterial rm2 = getRawMaterialDummyOne();

        final boolean b = rm1.equals(rm2);

        assertTrue(b);
    }

    @Test
    public void ensureRawMaterialEqualsFailsForDifferentObjectTypes() {
        final RawMaterial rm1 = getRawMaterialDummyOne();
        final Maquina m2 = new Maquina();

        final boolean b = rm1.equals(m2);

        assertFalse(b);
    }

    @Test
    public void ensureRawMaterialIsTheSameAsItsInstance() {
        final RawMaterial rm = getRawMaterialDummyOne();

        final boolean b = rm.sameAs(rm);

        assertTrue(b);
    }

    @Test
    public void ensureRawMaterialIsntTheSameAsAnotherInstance() {
        final RawMaterial rm1 = getRawMaterialDummyOne();
        final RawMaterial rm2 = getRawMaterialDummyTwo();

        final boolean b = rm1.sameAs(rm2);

        assertFalse(b);
    }


}
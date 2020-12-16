package eapli.base.infrastructure.bootstrapers.materiasprimas;

import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.base.rawmaterialmanagement.domain.RawMaterialBuilder;
import eapli.base.rawmaterialmanagement.repositories.MateriaPrimaRepository;
import eapli.base.utils.Description;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class MateriaPrimaBootstrapperTest extends BaseBootstrapper {

    private final String ID_ONE = "MP1";
    private final String ID_TWO = "MP2";
    ;
    private final String ID_THREE = "MP3";
    private final String DESC_ONE = "DESC1";
    private final String DESC_TWO = "DESC2";
    private final String DESC_THREE = "DESC3";
    private MateriaPrimaRepository mr;
    private MateriaPrimaBootstrapper mpb = new MateriaPrimaBootstrapper();

    private RawMaterial getRawMaterialDummy(String id, String desc) {
        RawMaterialBuilder rmb = new RawMaterialBuilder();
        return rmb.withData(id, new Description(desc)).build();
    }

    private RawMaterial getRawMaterialDummyOne() {
        return getRawMaterialDummy(ID_ONE, DESC_ONE);
    }

    private RawMaterial getRawMaterialDummyTwo() {
        return getRawMaterialDummy(ID_TWO, DESC_TWO);
    }

    private RawMaterial getRawMaterialDummyThree() {
        return getRawMaterialDummy(ID_THREE, DESC_THREE);
    }


    @Ignore
    public void ensureExecuteWorks() {
        super.authenticateForBootstrapping();
        mpb.execute();

        List<RawMaterial> rml = new ArrayList<>();
        Iterable<RawMaterial> rmi = mr.findAll();
        rmi.forEach(rml::add);

        assertTrue(rml.contains(getRawMaterialDummyOne()));
        assertTrue(rml.contains(getRawMaterialDummyTwo()));
        assertTrue(rml.contains(getRawMaterialDummyThree()));
    }
}
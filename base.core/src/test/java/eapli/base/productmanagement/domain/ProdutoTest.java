package eapli.base.productmanagement.domain;

import eapli.base.depositsmanagement.domain.Depositos;
import eapli.base.utils.Description;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ProdutoTest {

    private final String ID_PRODUTO_ONE = "ID1";
    private final String ID_PRODUTO_TWO = "ID2";

    public static Produto dummyProduto(String idProduto, String idComercial, Description descriptionBreve, Description descriptionCompleta, Quantidades qtd, CategoriaProduto cat) {
        final ProdutoBuilder fb = new ProdutoBuilder();
        return fb.withData(idProduto, idComercial, descriptionBreve, descriptionCompleta, qtd, cat).build();
    }

    private Produto getDummyProdutoOne() {
        return dummyProduto(ID_PRODUTO_ONE, "PD1", new Description("DESC1"), new Description("Descricao1"), new Quantidades("50"), new CategoriaProduto("123"));
    }

    private Produto getDummyProdutoTwo() {
        return dummyProduto(ID_PRODUTO_TWO, "PD1", new Description("DESC1"), new Description("Descricao1"), new Quantidades("50"), new CategoriaProduto("123"));
    }

    @Test
    public void ensureProdutoEqualsPassesForTheSameID() {
        final Produto p1 = getDummyProdutoOne();
        final Produto p2 = getDummyProdutoOne();

        final boolean b = p1.equals(p2);

        assertTrue(b);
    }

    @Test
    public void ensureProdutoEqualsFailsForDifferentID() {
        final Produto p1 = getDummyProdutoOne();
        final Produto p2 = getDummyProdutoTwo();

        final boolean b = p1.equals(p2);

        assertFalse(b);
    }

    @Test
    public void ensureProdutoEqualsFailsForDifferentObjectTypes() {
        final Produto p = getDummyProdutoOne();
        final Depositos d = new Depositos();

        final boolean b = p.equals(d);

        assertFalse(b);
    }

    @Test
    public void ensureProdutoIsTheSameAsItsInstance() {
        final Produto p = getDummyProdutoOne();

        final boolean b = p.sameAs(p);

        assertTrue(b);
    }

    @Test
    public void ensureProdutoIsntTheSameAsOtherInstance() {
        final Produto p1 = getDummyProdutoOne();
        final Produto p2 = getDummyProdutoTwo();

        final boolean b = p1.sameAs(p2);

        assertFalse(b);
    }


}
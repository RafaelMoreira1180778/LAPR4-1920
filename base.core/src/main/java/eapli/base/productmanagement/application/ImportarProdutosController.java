package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.repositories.ProdutoRepository;

import java.io.IOException;

public class ImportarProdutosController {

    private final ProdutoRepository jpar = PersistenceContext.repositories().productManagement();

    public void importCSV(String fileName) throws  IOException {
        jpar.loadProdutos(fileName);
    }
}

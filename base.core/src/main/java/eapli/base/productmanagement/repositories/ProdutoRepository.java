package eapli.base.productmanagement.repositories;

import eapli.base.productmanagement.domain.Produto;
import eapli.framework.domain.repositories.DomainRepository;

import java.io.IOException;
import java.util.List;

public interface ProdutoRepository extends DomainRepository<String, Produto> {

    List<Produto> hasFichaProducao();

    void loadProdutos(String fileName) throws IOException;
}
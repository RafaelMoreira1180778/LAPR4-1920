package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Produto;
import eapli.base.productmanagement.repositories.ProdutoRepository;
import eapli.framework.application.UseCaseController;

import java.util.List;

@UseCaseController
public class ProdutosSemFichaController {

    final ProdutoRepository jpar = PersistenceContext.repositories().productManagement();

    public void produtosSemFicha() {
        List<Produto> produtosSemFicha = jpar.hasFichaProducao();

        if(produtosSemFicha.isEmpty()) {
            System.out.println("Todos os Produtos têm Ficha de Producao!");
        } else {
            for (Produto prod : produtosSemFicha) {
                System.out.printf("%s", prod.toString());
            }
        }
    }
}
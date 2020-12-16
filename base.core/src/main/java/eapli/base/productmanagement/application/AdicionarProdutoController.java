package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.CategoriaProduto;
import eapli.base.productmanagement.domain.FichaProducao;
import eapli.base.productmanagement.domain.Produto;
import eapli.base.productmanagement.domain.Quantidades;
import eapli.base.productmanagement.repositories.ProdutoRepository;
import eapli.base.utils.Description;
import eapli.framework.application.UseCaseController;

@UseCaseController
public class AdicionarProdutoController {

    final ProdutoRepository jpar = PersistenceContext.repositories().productManagement();

    public void adicionarProduto(FichaProducao fichaProducao, String idProduto, String idComercial, Description descriptionBreve, Description descriptionCompleta, Quantidades qtd, CategoriaProduto cat) {
        final Produto prod = new Produto(fichaProducao, idProduto, idComercial, descriptionBreve, descriptionCompleta, qtd, cat);
        this.jpar.save(prod);
    }
}
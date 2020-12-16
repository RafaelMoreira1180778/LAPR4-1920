package eapli.base.persistence.impl.jpa;

import eapli.base.productmanagement.domain.CategoriaProduto;
import eapli.base.productmanagement.domain.Produto;
import eapli.base.productmanagement.domain.Quantidades;
import eapli.base.productmanagement.repositories.ProdutoRepository;
import eapli.base.utils.Description;

import javax.persistence.TypedQuery;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JpaProductRepository extends BasepaRepositoryBase<Produto, String, String> implements ProdutoRepository {

    public JpaProductRepository(String puname) {
        super(puname, "code");
    }

    @Override
    public List<Produto> hasFichaProducao() {
        TypedQuery<Produto> query = createQuery("SELECT p FROM Produto p WHERE p.fichaProducao IS null", Produto.class);
        return query.getResultList();
    }

    /* Retirado de um projeto realizado no âmbito da UC de Estruturas de Informação*/
    @Override
    public void loadProdutos(String fileName) throws IOException {
        FileReader fr = new FileReader(fileName + ".csv");
        BufferedReader br = new BufferedReader(fr);
        String linha;
        int iteration = 0;
        while ((linha = br.readLine()) != null) {
            if(iteration == 0) {
                iteration++;
            } else {
                String[] temp = linha.trim().split(";");
                Produto prod = new Produto(temp[0].trim(), temp[1].trim(), new Description(temp[2].trim()), new Description(temp[3].trim()), new Quantidades(temp[4].trim()), new CategoriaProduto(temp[5].trim()));
                save(prod);
            }
        }
        br.close();
    }
}

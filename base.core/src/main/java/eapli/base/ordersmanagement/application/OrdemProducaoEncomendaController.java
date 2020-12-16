package eapli.base.ordersmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.domain.ContentorOrdensProducao;
import eapli.base.ordersmanagement.repositories.EncomendaRepository;
import eapli.base.productmanagement.domain.Produto;

import java.util.List;

public class OrdemProducaoEncomendaController {

    final EncomendaRepository repo = PersistenceContext.repositories().encomendaManagement();

    public void ordensProdEncomenda(String idEncomenda) {

        ContentorOrdensProducao cop = repo.listOrdensProducao(idEncomenda);
        List<String> ops = cop.getOps();

        if(ops.isEmpty()) {
            System.out.println("A Encomenda não tem Ordens de Produção definidas!");
        } else {
            System.out.printf("%s", ops.toString());
        }
    }
}

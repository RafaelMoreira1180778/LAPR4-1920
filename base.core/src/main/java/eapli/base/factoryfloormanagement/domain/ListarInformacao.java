package eapli.base.factoryfloormanagement.domain;

import eapli.base.depositsmanagement.domain.Depositos;
import eapli.base.depositsmanagement.repositories.DepositosRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.machinemanagement.domain.Maquina;
import eapli.base.machinemanagement.repositories.MachineRepository;
import eapli.base.ordersmanagement.domain.Encomenda;
import eapli.base.ordersmanagement.repositories.EncomendaRepository;
import eapli.base.productionlinemanagement.domain.LinhaProducao;
import eapli.base.productionlinemanagement.repositories.LinhaProducaoRepository;
import eapli.base.productionordermanagement.domain.OrdemProducao;
import eapli.base.productionordermanagement.repositories.OrdemProducaoRepository;
import eapli.base.productmanagement.domain.FichaProducao;
import eapli.base.productmanagement.domain.Produto;
import eapli.base.productmanagement.repositories.FichaProducaoRepository;
import eapli.base.productmanagement.repositories.ProdutoRepository;
import eapli.base.rawmaterialmanagement.domain.CategoriaMateriaPrima;
import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.base.rawmaterialmanagement.repositories.CategoriaMateriaPrimaRepository;
import eapli.base.rawmaterialmanagement.repositories.MateriaPrimaRepository;

public class ListarInformacao {

    private final MateriaPrimaRepository materiaPrimaRepository = PersistenceContext.repositories().materiaPrimaManagement();
    private final CategoriaMateriaPrimaRepository categoriaMateriaPrimaRepository = PersistenceContext.repositories().catMPManagement();
    private final ProdutoRepository produtoRepository = PersistenceContext.repositories().productManagement();
    private final FichaProducaoRepository fichaProducaoRepository = PersistenceContext.repositories().fichaproducaoManagement();
    private final LinhaProducaoRepository linhaProducaoRepository = PersistenceContext.repositories().linhaProducaoManagement();
    private final EncomendaRepository encomendaRepository = PersistenceContext.repositories().encomendaManagement();
    private final MachineRepository machineRepository = PersistenceContext.repositories().maquinaManagement();
    private final DepositosRepository depositosRepository = PersistenceContext.repositories().depositoManagement();
    private final OrdemProducaoRepository ordemProducaoRepository = PersistenceContext.repositories().ordemProducaoManagement();

    public ListarInformacao() {
    }

    public Iterable<RawMaterial> listarTodasMateriasPrimas() {
        return this.materiaPrimaRepository.findAll();
    }

    public Iterable<CategoriaMateriaPrima> listarTodasCategoriasMateriasPrimas() {
        return this.categoriaMateriaPrimaRepository.findAll();
    }

    public Iterable<Produto> listarTodosProdutos(){
        return this.produtoRepository.findAll();
    }

    public Iterable<FichaProducao> listarTodasFichasProducao() {
        return this.fichaProducaoRepository.findAll();
    }

    public Iterable<LinhaProducao> listarTodasLinhasProducao() {
        return this.linhaProducaoRepository.findAll();
    }

    public Iterable<Encomenda> listarTodasEncomendas() {
        return this.encomendaRepository.findAll();
    }

    public Iterable<Maquina> listarTodasMaquinas() {
        return this.machineRepository.findAll();
    }

    public Iterable<Depositos> listarTodosDepositos() {
        return this.depositosRepository.findAll();
    }

    public Iterable<OrdemProducao> listarTodasOrdensProducao() {
        return this.ordemProducaoRepository.findAll();
    }
}

package eapli.base.productionordermanagement.domain;

import eapli.base.productmanagement.domain.Quantidades;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class OrdemProducao implements AggregateRoot<String> {

    @Id
    private String idOrdemProducao;
    private Estado estado;
    private Date dataEmissao;
    private Date previsaoExecucao;
    private String idProduto;
    private ContentorIdEncomendas idEncomendas;
    private int quantidades;
    private Quantidades unidade;

    public OrdemProducao() {

    }

    public OrdemProducao(String id, Estado es, Date de, Date pe, String idProd, ContentorIdEncomendas idEnc, int quant, Quantidades un) {
        this.idOrdemProducao = id;
        this.estado = es;
        this.dataEmissao = de;
        this.previsaoExecucao = pe;
        this.idProduto = idProd;
        this.idEncomendas = idEnc;
        this.quantidades = quant;
        this.unidade = un;
    }

    public OrdemProducao(String id, Date dataEmissao, Date previsaoExecucao, String idProduto, int quant, Quantidades uni, ContentorIdEncomendas idEnc) {
        this.idOrdemProducao = id;
        this.dataEmissao = dataEmissao;
        this.previsaoExecucao = previsaoExecucao;
        this.idProduto = idProduto;
        this.quantidades = quant;
        this.unidade = uni;
        this.idEncomendas = idEnc;

    }

    @Override
    public String toString() {
        return "Ordem de Produção: \n" +
                "   - ID da Ordem de Produção: " + idOrdemProducao + "\n" +
                "   - Estado: " + getEstado() + "\n" +
                "   - Data de Emissão: " + dataEmissao + "\n" +
                "   - Data de Previsão de Execução: " + previsaoExecucao + "\n" +
                "   - ID do Produto: " + idProduto + "\n" +
                unidade + "\n" +
                idEncomendas + "\n" +
                "   - Quantidade: " + quantidades + "\n" +
                "-------------------";
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public String identity() {
        return this.idOrdemProducao;
    }

    public String getIdOrdemProducao() {
        return idOrdemProducao;
    }

    public Estado getEstado() {
        return estado;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public Date getPrevisaoExecucao() {
        return previsaoExecucao;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public ContentorIdEncomendas getIdEncomendas() {
        return idEncomendas;
    }

    public int getQuantidades() {
        return quantidades;
    }

    public Quantidades getUnidade() {
        return unidade;
    }
}

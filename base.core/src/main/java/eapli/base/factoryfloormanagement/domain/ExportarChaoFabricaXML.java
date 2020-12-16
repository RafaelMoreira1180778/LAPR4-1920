package eapli.base.factoryfloormanagement.domain;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


import eapli.base.depositsmanagement.domain.Depositos;
import eapli.base.depositsmanagement.domain.Descricao_Deposito;
import eapli.base.machinemanagement.domain.Maquina;
import eapli.base.machinemanagement.domain.Timestamp;
import eapli.base.ordersmanagement.domain.ContentorOrdensProducao;
import eapli.base.ordersmanagement.domain.Encomenda;
import eapli.base.productionlinemanagement.domain.LinhaProducao;
import eapli.base.productionordermanagement.domain.ContentorIdEncomendas;
import eapli.base.productionordermanagement.domain.Estado;
import eapli.base.productionordermanagement.domain.OrdemProducao;
import eapli.base.productmanagement.domain.FichaProducao;
import eapli.base.productmanagement.domain.Produto;
import eapli.base.productmanagement.domain.Quantidades;
import eapli.base.rawmaterialmanagement.domain.CategoriaMateriaPrima;
import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ExportarChaoFabricaXML {

    private String filename;
    private int[] tipoDadosParaExportar;
    private String dataInicio;
    private String dataFim;

    private final ListarInformacao listarInformacao = new ListarInformacao();

    public ExportarChaoFabricaXML(String filename, int[] tipoDadosParaExportar, String dataInicio, String dataFim) {
        this.filename = filename;
        this.tipoDadosParaExportar = tipoDadosParaExportar;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        ordenarArray(tipoDadosParaExportar);
    }

    public void exportarParaXML() {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {

            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            doc.setXmlStandalone(true);
            Element mainRootElement = doc.createElement("ChaoDeFabrica");
            //mainRootElement.setAttribute("xmlns", "http://www.isep.com/ChaoDeFabrica");
            mainRootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            mainRootElement.setAttribute("xsi:noNameschemaLocation", "ChaoDeFabrica.xsd");

            doc.appendChild(mainRootElement);

            for(int i = 0; i < tipoDadosParaExportar.length; i++) {
                if(tipoDadosParaExportar[i] != 0) {
                    mainRootElement.appendChild(criarFilhos(doc, tipoDadosParaExportar[i]));
                }
            }
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.transform(new DOMSource(doc),
                    new StreamResult(new FileOutputStream(filename)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Node criarFilhos(Document doc, int tipoDado) {

        switch (tipoDado){
            //materia prima
            case 1:
                Element materiasPrimas = doc.createElement("MateriasPrimas");
                for(RawMaterial matPrima : listarInformacao.listarTodasMateriasPrimas()) {
                    materiasPrimas.appendChild(adicionarMateriaPrima(doc, matPrima.getId(), matPrima.getDescription().getDescription()));
                }
                return materiasPrimas;

            case 2:
                Element categoriasMateriasPrimas = doc.createElement("CategoriasMateriasPrimas");
                for(CategoriaMateriaPrima catMP : listarInformacao.listarTodasCategoriasMateriasPrimas()) {
                    categoriasMateriasPrimas.appendChild(adicionarCategoriaMateriaPrima(doc, catMP.getId(),catMP.getListaMateriasPrimas()));
                }
                return categoriasMateriasPrimas;
            case 3:
                Element produtos = doc.createElement("Produtos");
                for(Produto prod : listarInformacao.listarTodosProdutos()) {
                    produtos.appendChild(adicionarProduto(doc, prod.getIdProduto(), prod.getIdComercial(), prod.getDescriptionBreve().getDescription(),
                            prod.getDescriptionCompleta().getDescription(), prod.getUnidade().getUnidade(), prod.getCategoria().getIdCategoria()));
                }
                return produtos;
            case 4:
                /*Element fichasProducao = doc.createElement("FichasProducao");
                for(FichaProducao fprod : listarInformacao.listarTodasFichasProducao()) {
                    fichasProducao.appendChild(adicionarFichaProducao(doc, fprod.getIdFichaProducao(), fprod.getListRawMaterial(), fprod.getListQuantidades()));
                }
                return fichasProducao;*/
                break;
            case 5:
                Element linhasProducao = doc.createElement("LinhasProducao");
                for(LinhaProducao lp : listarInformacao.listarTodasLinhasProducao()) {
                    linhasProducao.appendChild(adicionarLinhaProducao(doc, lp.getIdLinhaProducao(), lp.getListaMaquinas()));
                }
                return linhasProducao;
            case 6:
                Element encomendas = doc.createElement("Encomendas");
                for(Encomenda enc : listarInformacao.listarTodasEncomendas()) {
                    encomendas.appendChild(adicionarEncomenda(doc, enc.getIdEncomenda(), enc.getOrdens().getOps()));
                }
                return encomendas;
            case 7:
                Element maquinas = doc.createElement("Maquinas");
                for(Maquina maq : listarInformacao.listarTodasMaquinas()) {
                    maquinas.appendChild(adicionarMaquina(doc, maq.getId(), Integer.toString(maq.getNum_Serie()), maq.getFabricante(), maq.getModelo(),
                            String.valueOf(maq.getAnoFabrico().getYear()), maq.getDescription().getDescription(), maq.getTs_instalacao()));
                }
                return maquinas;
            case 8:
                Element depositos = doc.createElement("Depositos");
                for(Depositos dep : listarInformacao.listarTodosDepositos()) {
                    depositos.appendChild(adicionarDepostito(doc, dep.getId_Deposito(), dep.getDescricao().getDescStr()));
                }
                return depositos;
            case 9:
                Element ordensProducao = doc.createElement("OrdensProducao");
                for(OrdemProducao op : listarInformacao.listarTodasOrdensProducao()) {
                    ordensProducao.appendChild(adicionarOrdemProducao(doc, op.getIdOrdemProducao(), op.getEstado(), op.getIdProduto(),
                            formatData(op.getDataEmissao()), formatData(op.getPrevisaoExecucao()), op.getIdEncomendas(), Integer.toString(op.getQuantidades()),
                            op.getUnidade().getUnidade()));
                }
                return ordensProducao;
            default:
                break;
        }
        return null;
    }

    private Node adicionarMateriaPrima(Document doc, String id, String description) {
        Element matPrima = doc.createElement("MateriaPrima");
        matPrima.setAttribute("id", id);
        matPrima.appendChild(getElemento(doc, "Descricao", description));
        return matPrima;
    }

    private Node adicionarCategoriaMateriaPrima(Document doc, String id, List<RawMaterial> listaMateriasPrimas) {
        Element catMP = doc.createElement("CategoriaMateriasPrimas");
        catMP.setAttribute("id", id);
        //System.out.format("tamanho = %d", listaMateriasPrimas.size());
        for (RawMaterial rm : listaMateriasPrimas) {
            Element matPrima = doc.createElement("MateriaPrima");
            matPrima.setAttribute("id", id);
            catMP.appendChild(matPrima);
        }
        return catMP;
    }

    private Node adicionarProduto(Document doc, String idProduto, String idComercial, String description, String descriptionComp, String unidade, String idCategoria) {
        Element produto = doc.createElement("Produto");
        produto.setAttribute("id", idProduto);
        produto.appendChild(getElemento(doc, "IDComercial", idComercial));
        produto.appendChild(getElemento(doc, "DescricaoBreve", description));
        produto.appendChild(getElemento(doc, "DescricaoCompleta", descriptionComp));
        produto.appendChild(getElemento(doc, "Unidade", unidade));
        produto.appendChild(getElemento(doc, "IDCategoria", idCategoria));
        return produto;
    }

    private Node adicionarFichaProducao(Document doc, String idFichaProducao, ArrayList<RawMaterial> listRawMaterial, ArrayList<Quantidades> listQuantidades) {
        Element fichaProducao = doc.createElement("FichaProducao");
        fichaProducao.setAttribute("id", idFichaProducao);
        for(int i = 0; i < listRawMaterial.size(); i++) {
            Element matP = doc.createElement("MateriaPrima");
            matP.appendChild(getElemento(doc, "ID", listRawMaterial.get(i).getId()));
            matP.appendChild(getElemento(doc, "Quantidade", listQuantidades.get(i).getUnidade()));
            fichaProducao.appendChild(matP);
        }
        return fichaProducao;
    }

    private Node adicionarLinhaProducao(Document doc, String idLinhaProducao, List<Maquina> listaMaquinas) {
        Element linhaProducao = doc.createElement("LinhaProducao");
        linhaProducao.setAttribute("id", idLinhaProducao);
        for(Maquina maquina : listaMaquinas) {
            Element maq = doc.createElement("Maquina");
            maq.setAttribute("id", maquina.getId());
            linhaProducao.appendChild(maq);
        }
        return linhaProducao;
    }

    private Node adicionarEncomenda(Document doc, String idEncomenda,  List<String> ordensProducao) {
        Element encomenda = doc.createElement("Encomenda");
        encomenda.setAttribute("id", idEncomenda);
        for(String op : ordensProducao) {
            Element ordemP = doc.createElement("OrdemProducao");
            ordemP.setAttribute("id", op);
            encomenda.appendChild(ordemP);
        }
        return encomenda;
    }

    private Node adicionarMaquina(Document doc, String id, String numSerie, String fabricante, String modelo, String anoFabrico, String description, Timestamp ts_instalacao) {
        Element maquina = doc.createElement("Maquina");
        maquina.setAttribute("id", id);
        maquina.appendChild(getElemento(doc, "numeroSerie", numSerie));
        maquina.appendChild(getElemento(doc, "Fabricante", fabricante));
        maquina.appendChild(getElemento(doc, "Modelo", modelo));
        maquina.appendChild(getElemento(doc, "AnoFabrico", anoFabrico));
        maquina.appendChild(getElemento(doc, "Descricao", description));
        Element instalacao = doc.createElement("Instalacao");
        instalacao.appendChild(getElemento(doc, "Data", formatData(ts_instalacao.getData())));
        instalacao.appendChild(getElemento(doc, "Hora", ts_instalacao.getTime().toString()));
        maquina.appendChild(instalacao);
        return maquina;
    }

    private Node adicionarDepostito(Document doc, String id_deposito, String descricao) {
        Element deposito = doc.createElement("Deposito");
        deposito.setAttribute("id", id_deposito);
        deposito.appendChild(getElemento(doc, "Descricao", descricao));
        return deposito;
    }

    private Node adicionarOrdemProducao(Document doc, String idOrdemProducao, Estado estado, String idProduto, String dataEmissao, String dataPrevisao,
                                        ContentorIdEncomendas idEncomendas, String quantidade, String unidade) {
        Element ordemProducao = doc.createElement("OrdemProducao");
        ordemProducao.setAttribute("id", idOrdemProducao);
        //ordemProducao.appendChild(getElemento(doc, "Estado", estado));
        Element produto = doc.createElement("Produto");
        produto.setAttribute("id", idProduto);
        ordemProducao.appendChild(produto);
        //System.out.println(dataEmissao);
        ordemProducao.appendChild(getElemento(doc, "DataEmissao", dataEmissao));
        ordemProducao.appendChild(getElemento(doc, "DataPrevisao", dataPrevisao));
        Element encomendas = doc.createElement("encomendas");
        for (String idEncomenda : idEncomendas.getIds()) {
            encomendas.appendChild(getElemento(doc, "idEncomenda", idEncomenda));
        }
        ordemProducao.appendChild(encomendas);
        ordemProducao.appendChild(getElemento(doc, "Quantidade", quantidade));
        ordemProducao.appendChild(getElemento(doc, "UnidadeMedida", unidade));
        return ordemProducao;
    }

    private Node getElemento(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    private String formatData(Date data) {
        return String.format("%d/%d/%d", data.getYear(), data.getMonth(), data.getDay());
    }


    private void ordenarArray(int array[]) {
        int n = array.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

}

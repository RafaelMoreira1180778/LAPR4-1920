package eapli.base.persistence.impl.jpa;

import eapli.base.productionordermanagement.domain.ContentorIdEncomendas;
import eapli.base.productionordermanagement.domain.Estado;
import eapli.base.productionordermanagement.domain.OrdemProducao;
import eapli.base.productionordermanagement.repositories.OrdemProducaoRepository;
import eapli.base.productmanagement.domain.Quantidades;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class JpaOrdemProducaoRepository extends BasepaRepositoryBase<OrdemProducao, String, String> implements OrdemProducaoRepository {

    public JpaOrdemProducaoRepository(String puname) {
        super(puname, "code");
    }

    @Override
    public void loadOrdensProducao(String fileName) throws IOException {
        FileReader fr = new FileReader(fileName + ".csv");
        BufferedReader br = new BufferedReader(fr);
        String linha;
        int iteration = 0;
        while ((linha = br.readLine()) != null) {
            if(iteration == 0) {
                iteration++;
            } else {
                String[] temp = linha.trim().split(";");
                String idsEncomendas = temp[6];
                String[] tempID = idsEncomendas.trim().split(",");
                ArrayList<String> idEncomendas = new ArrayList<>();
                for (int i=0; i < tempID.length; i++) {
                    idEncomendas.add(tempID[i]);
                }
                String[] dataEmissao = temp[1].trim().split("/");
                int anoEmissao = Integer.parseInt(dataEmissao[0]);
                int mesEmissao = Integer.parseInt(dataEmissao[1]);
                int diaEmissao = Integer.parseInt(dataEmissao[2]);
                String[] dataPrevisao = temp[1].trim().split("/");
                int anoPrevisao = Integer.parseInt(dataPrevisao[0]);
                int mesPrevisao = Integer.parseInt(dataPrevisao[1]);
                int diaPrevisao = Integer.parseInt(dataPrevisao[2]);
                OrdemProducao ordemP = new OrdemProducao(temp[0].trim(), new Date(anoEmissao, mesEmissao, diaEmissao),
                        new Date(anoPrevisao, mesPrevisao, diaPrevisao), temp[3].trim(),
                        Integer.parseInt(temp[4].trim()), new Quantidades(temp[5].trim()), new ContentorIdEncomendas(idEncomendas));
                save(ordemP);
            }
        }
        br.close();
        fr.close();
    }

    @Override
    public List<OrdemProducao> listByEstado(Estado estado) {
        final Map<String, Object> params = new HashMap<>();
        params.put("estado", estado);
        return match("e.estado = :estado", params);
    }
}

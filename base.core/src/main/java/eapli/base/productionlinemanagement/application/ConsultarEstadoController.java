package eapli.base.productionlinemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productionlinemanagement.domain.EstadoProcessamento;
import eapli.base.productionlinemanagement.domain.LinhaProducao;
import eapli.base.productionlinemanagement.repositories.LinhaProducaoRepository;
import eapli.framework.application.UseCaseController;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@UseCaseController
public class ConsultarEstadoController {

    private final LinhaProducaoRepository linhaProducaoRepository = PersistenceContext.repositories().linhaProducaoManagement();

    public EstadoProcessamento currentStatus(String idLP) {
        return linhaProducaoRepository.currentStatus(idLP);
    }

    public void changeStatus(String idLP, EstadoProcessamento novoEstado) {
        LinhaProducao lp = linhaProducaoRepository.findByID(idLP);
        linhaProducaoRepository.delete(lp);

        lp.setEstadoProcessamento(novoEstado);

        linhaProducaoRepository.save(lp);

        if (novoEstado.equals(EstadoProcessamento.ATIVO)) {
            reRunProcessamento();
        }
    }

    public Date ultimaOcorrencia(String idLP) {
        return linhaProducaoRepository.ultimaOcorrencia(idLP);
    }

    public void reRunProcessamento() {
        try {
            Process p =  Runtime.getRuntime().exec("./run-spm.bat", null, new File("C:/Users/migue/Documents/lei_isep_2019_20_sem4_2de_1161360_1170617_1171060_1180778_1180/run-spm.bat")) ;
        } catch (IOException ex) {

        }
    }
}

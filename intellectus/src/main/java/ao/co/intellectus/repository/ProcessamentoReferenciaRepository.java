package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.ProcessamentoReferencia;

public interface ProcessamentoReferenciaRepository extends CrudRepository<ProcessamentoReferencia,Integer>{
    public List<ProcessamentoReferencia> findByAnoLectivo(AnoLectivo anoLectivo);
    public ProcessamentoReferencia findByAnoLectivoAndMes(AnoLectivo anoLectivo,String mes);
}

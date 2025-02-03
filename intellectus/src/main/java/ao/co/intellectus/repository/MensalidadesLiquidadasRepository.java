package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.model.MensalidadesLiquidadas;

public interface MensalidadesLiquidadasRepository extends CrudRepository<MensalidadesLiquidadas,Long>{
    public List<MensalidadesLiquidadas> findByNumeroDeAlunoAndAnoLectivo(Integer numeroDeAluno,AnoLectivo anoLectivo);
    public MensalidadesLiquidadas findByNumeroDeAlunoAndAnoLectivoAndEmolumento(Integer numeroDeAluno,AnoLectivo anoLectivo,Emolumento emolumento);
}
package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.ContaCorrenteCandidato;

public interface ContaCorrenteCandidatoRepository extends CrudRepository<ContaCorrenteCandidato,Integer>{
     public ContaCorrenteCandidato findById(Integer numero);
     public ContaCorrenteCandidato findByCandidato(Candidato candidato);
     public ContaCorrenteCandidato findByAnoLectivoAndNumeroDeCandidato(AnoLectivo anoLectivo,String numero);    
}

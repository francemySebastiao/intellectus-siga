package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.HistoricoCreditoCandidato;

public interface HistoricoCreditoCandidatoRepository extends JpaRepository<HistoricoCreditoCandidato,Integer>{
   public List<HistoricoCreditoCandidato> findByCandidato(Candidato candidato);
}

package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.ProgHistoricoCrtBolseiro;

public interface ProgHistoricoCrtBolseiroRepository extends JpaRepository<ProgHistoricoCrtBolseiro, Integer>{

	public List<ProgHistoricoCrtBolseiro> findByAnoLectivoId(Integer anoLectivo);
}

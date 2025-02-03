package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.InscricoesAnuladas;

public interface InscricoesAnuladasRepository extends CrudRepository<InscricoesAnuladas, Integer>{
	public InscricoesAnuladas findByAnoLectivo(Integer anoLectivo);
}

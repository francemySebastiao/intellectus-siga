package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.RegistroBaseDeDadosPosGraduacao;

public interface RegistroBaseDeDadosPosGraduacaoRepository extends CrudRepository<RegistroBaseDeDadosPosGraduacao, Integer> {
	public List<RegistroBaseDeDadosPosGraduacao> findByAnoLectivo(Integer anoLectivo);
}

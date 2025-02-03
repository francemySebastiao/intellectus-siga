package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.RegistroBaseDadosAproveitamento;

public interface RegistroBaseDadosAproveitamentoRepository extends CrudRepository<RegistroBaseDadosAproveitamento, Integer> {
	public List<RegistroBaseDadosAproveitamento> findByAnolectivo(Integer anolectivo);
}

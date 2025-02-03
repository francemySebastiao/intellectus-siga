package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.ResgistroBaseDadosAcesso;

public interface ResgistroBaseDadosAcessoRepository extends CrudRepository<ResgistroBaseDadosAcesso, Integer> {

	public List<ResgistroBaseDadosAcesso> findByAnoLectivo(Integer anoLectvio);

}

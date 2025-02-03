package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.RegistroBaseDadosGraduadosPreliminarDefinitivo;

public interface RegistroBaseDadosGraduadosPreliminarDefinitivoRepository extends CrudRepository<RegistroBaseDadosGraduadosPreliminarDefinitivo, Integer> {
		public List<RegistroBaseDadosGraduadosPreliminarDefinitivo> findByAnoLectivo(Integer anoLectvio);
}

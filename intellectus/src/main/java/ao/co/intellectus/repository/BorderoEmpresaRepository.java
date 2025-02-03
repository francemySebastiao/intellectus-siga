package ao.co.intellectus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.BorderoEmpresa;

public interface BorderoEmpresaRepository extends JpaRepository<BorderoEmpresa, Integer> {

	@Query(value="SELECT * FROM T_BORDERO_EMPRESA WHERE NUMERO =:numero and CODIGO_BANCO =:banco",nativeQuery=true)
	public BorderoEmpresa buscarNumeroBanco(@Param("numero") String numero, @Param("banco") Integer  banco);
}

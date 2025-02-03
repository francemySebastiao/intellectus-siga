package ao.co.intellectus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.ContaCorrenteEmpresa;

public interface ContaCorreteEmpresaRepository extends JpaRepository<ContaCorrenteEmpresa, Integer> {

	@Query(value="SELECT * FROM T_CONTA_CORRENTE_EMPRESA WHERE CODIGO_EMPRESA =:empresa",nativeQuery=true)
 	public ContaCorrenteEmpresa buscarEmpresa(@Param("empresa") Integer empresa);
}

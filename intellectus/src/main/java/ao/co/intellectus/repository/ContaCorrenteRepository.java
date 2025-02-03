package ao.co.intellectus.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.ContaCorrenteAluno;

public interface ContaCorrenteRepository extends CrudRepository<ContaCorrenteAluno, Integer> {
	public ContaCorrenteAluno findByAluno(Aluno aluno);

	@Query(value = "SELECT * FROM T_CONTA_CORRENTE WHERE CODIGO_EMPRESA =:empresa", nativeQuery = true)
	public ContaCorrenteAluno buscarEmpresa(@Param("empresa") Integer empresa);
}

package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.RegistoCertificado;

public interface RegistoCertificadoRepository extends CrudRepository<RegistoCertificado, Integer>{
	
	public RegistoCertificado findByNumeroCertificado(String numeroCertificado);
	public RegistoCertificado findByAluno(Aluno aluno);

}

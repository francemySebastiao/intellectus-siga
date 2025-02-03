package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.CertificadoIntermedio;

public interface CertificadoIntermedioRepository extends CrudRepository<CertificadoIntermedio,Integer>{
	public List<CertificadoIntermedio> findByNumeroAndValidacao(Integer numero,boolean validacao);
	public List<CertificadoIntermedio> findByNumero(Integer numero);
	public List<CertificadoIntermedio> findByNumeroOrderByAnoCurricularAsc(Integer numero);
	
	public List<CertificadoIntermedio> findByNumeroOrderByAnoCurricular(Integer numero);
	
	public List<CertificadoIntermedio> findByNumeroAndCodigoDisciplina(Integer numero,Integer codigo);
	public List<CertificadoIntermedio> findByNumeroAndAnoCurricular(Integer numero,Integer anoCurricular);
}
 
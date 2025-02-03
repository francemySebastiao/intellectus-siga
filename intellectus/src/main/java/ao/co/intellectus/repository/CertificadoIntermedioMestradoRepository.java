package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.CertificadoIntermedioMestrado;

public interface CertificadoIntermedioMestradoRepository extends JpaRepository<CertificadoIntermedioMestrado, Integer> {
	
	public List<CertificadoIntermedioMestrado> findByNumeroAndValidacao(Integer numero,boolean validacao);

}

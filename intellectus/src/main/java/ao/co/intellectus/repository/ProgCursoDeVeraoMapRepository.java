package ao.co.intellectus.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.ProgCursoDeVeraoMap;
import ao.co.intellectus.model.TipoProvaExtraOrdinaria;

public interface ProgCursoDeVeraoMapRepository extends JpaRepository<ProgCursoDeVeraoMap, Integer> {
	
	public List<ProgCursoDeVeraoMap> findByPagamentoAndDataInscricaoBetween(String pagamento,Date de, Date para);
	
	public List<ProgCursoDeVeraoMap> findByDataInscricaoBetween(Date de, Date para);
	
	public List<ProgCursoDeVeraoMap> findByCodigoCursoAndDataInscricaoBetween(Integer codigoCurso,Date de, Date para);
	
	public List<ProgCursoDeVeraoMap> findByProvaAndCodigoCursoAndDataInscricaoBetween(TipoProvaExtraOrdinaria prova,Integer codigoCurso,Date de, Date para);
	
	
	public List<ProgCursoDeVeraoMap> findByProvaAndCodigoCursoAndDataInscricaoBetweenAndPagamento(TipoProvaExtraOrdinaria prova,Integer codigoCurso,Date de, Date para,String pagamento);
	
//codigoCurso

}

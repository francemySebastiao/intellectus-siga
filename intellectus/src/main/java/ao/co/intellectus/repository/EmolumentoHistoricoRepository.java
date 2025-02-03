package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.model.EmolumentoHistorico;

public interface EmolumentoHistoricoRepository extends CrudRepository<EmolumentoHistorico,Integer>{
	public EmolumentoHistorico findByEmolumentoAndCursoAndAnoLectivo(Emolumento emolumento,Curso curso,AnoLectivo anoLectivo);
	
	public List<EmolumentoHistorico> findByCursoAndAnoLectivo(Curso curso,AnoLectivo anoLectivo);
	public List<EmolumentoHistorico> findByAnoLectivo(AnoLectivo anoLectivo);
	
	public EmolumentoHistorico findByEmolumentoAndAnoLectivo(Emolumento emolumento,AnoLectivo anoLectivo);
	
	public EmolumentoHistorico findByCursoAndAnoLectivoAndEmolumentoId(Curso curso,AnoLectivo anoLectivo,Integer codigoEmolumento);
	
	//@Query(value = "SELECT * FROM T_EMOLUMENTO_HISTORICO WHERE CODIGO_EMOLUMENTO =:emolumento AND CODIGO_CURSO =:curso  AND CODIGO_ANO_LECTIVO =:anoLectivo AND TURNO =:turno", nativeQuery = true)
	//public EmolumentoHistorico buscarEmolumentoTurno(@Param("emolumento") Integer emolumento, @Param("curso") Integer curso, @Param("anoLectivo") Integer anoLectivo, @Param("turno") String turno);
	
	//@Query(value = "SELECT * FROM T_EMOLUMENTO_HISTORICO WHERE CODIGO_EMOLUMENTO =:emolumento AND CODIGO_CURSO =:curso  AND CODIGO_ANO_LECTIVO = (SELECT ID FROM T_ANO_LECTIVO WHERE STATUS = 1) AND TURNO =:turno", nativeQuery = true)
	//public EmolumentoHistorico buscarEmolumentoAnoActivo(@Param("emolumento") Integer emolumento, @Param("curso") Integer curso, @Param("turno") String turno);
	 
}
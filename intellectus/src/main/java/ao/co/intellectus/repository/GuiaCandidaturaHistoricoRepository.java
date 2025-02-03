package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaCandidatura;
import ao.co.intellectus.model.GuiaCandidaturaHistorico;

public interface GuiaCandidaturaHistoricoRepository extends CrudRepository<GuiaCandidaturaHistorico, Integer>{
	public List<GuiaCandidaturaHistorico> findByGuia(GuiaCandidatura guia);
	public List<GuiaCandidaturaHistorico> findByGuiaAndEmolumento(Guia guia,Emolumento emolumento);
	
	public List<GuiaCandidaturaHistorico> findByCandidato(Candidato candidato);
	
	@Query(value="SELECT * FROM T_GUIA_CANDIDATURA_HISTORICO WHERE CODIGO_GUIA_CANDIDATURA =:guia",nativeQuery=true)
	public List<GuiaCandidaturaHistorico> buscarIdGuia(@Param("guia") Integer guia);
}

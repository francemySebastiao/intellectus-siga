package ao.co.intellectus.repository.exame_electronico;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.exame_eletronico.ExameEletronico;
import ao.co.intellectus.model.exame_eletronico.Pergunta;
import ao.co.intellectus.model.exame_eletronico.TipoExame;

public interface ExameEletronicoRepository extends JpaRepository<ExameEletronico, Integer> {

	public List<ExameEletronico> findByCandidatoAndTipoExame(Candidato candidato, TipoExame tipoExame);
	
	public List<ExameEletronico> findByCandidato(Candidato candidato);
	public List<ExameEletronico> findByCandidatoAndDataFimProva(Candidato candidato, Date dataExame);
	
	public List<ExameEletronico> findByCandidatoId(Integer codigoCandidato);
	
	
	
	//public List<ExameEletronico> findByCandidatoIdAndRespostaCorreta(Integer codigoCandidato,boolean correta);
	
	
	
	public List<ExameEletronico> findByCandidatoOrderByTipoExameAsc(Candidato candidato);
	public List<ExameEletronico> findDistinctTipoExameByCandidato(Candidato candidato);
	public List<ExameEletronico> findByCandidatoAndTipoExameNot(Candidato candidato, TipoExame tipoExame);
	public ExameEletronico findByCandidatoAndTipoExameAndPergunta(Candidato candidato, TipoExame tipoExame,Pergunta pergunta);
	
	public List<ExameEletronico> findByCandidatoNumeroCandidatoAndAnoLectivoAnoLectivo(Integer codigoCandidato, Integer anoLectivo);
	public List<ExameEletronico> findByCandidatoNomeAndAnoLectivoAnoLectivo(String nome, Integer anoLectivo);
	
	
	
	public List<ExameEletronico> findByCursoIdAndAnoLectivoId(Integer curso,Integer anoLectivo);

}

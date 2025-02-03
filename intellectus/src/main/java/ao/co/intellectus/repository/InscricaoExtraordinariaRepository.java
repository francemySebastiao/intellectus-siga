package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.InscricaoExtraordinaria;
import ao.co.intellectus.model.TipoProvaExtraOrdinaria;

public interface InscricaoExtraordinariaRepository extends CrudRepository<InscricaoExtraordinaria,Integer>{
	public List<InscricaoExtraordinaria> findByAnoLectivoAndAluno(AnoLectivo anoLectivo,Aluno aluno);
	public List<InscricaoExtraordinaria> findByAluno(Aluno aluno);
	
	public List<InscricaoExtraordinaria> findByAnoLectivoAndAlunoAndDisciplina(AnoLectivo anoLectivo,Aluno aluno,Disciplina disciplina);
	
	
	//public List<InscricaoExtraordinaria> findByAnoLectivoAndAlunoAndDisciplinaAndGuiaPagamentoLiquidada(AnoLectivo anoLectivo,Aluno aluno,Disciplina disciplina,boolean liquidada);
	
	public List<InscricaoExtraordinaria> findByAnoLectivoAndAlunoAndDisciplinaAndProva(AnoLectivo anoLectivo,Aluno aluno,Disciplina disciplina,TipoProvaExtraOrdinaria prova);
	
	
	
	//prova
	public List<InscricaoExtraordinaria> findByAnoLectivoAndAlunoAndDisciplinaAndGuiaPagamentoLiquidada(AnoLectivo anoLectivo,Aluno aluno,Disciplina disciplina,boolean liquidada);
	
	public List<InscricaoExtraordinaria> findByAlunoAndDisciplinaAndGuiaPagamentoLiquidada(Aluno aluno,Disciplina disciplina,boolean liquidada);
	
	
	
	//public List<InscricaoExtraordinaria> findByAnoLectivoAndAlunoAndDisciplinaAndGuiaPagamentoLiquidadaAndProva(AnoLectivo anoLectivo,Aluno aluno,Disciplina disciplina,boolean liquidada);
	
	
	public List<InscricaoExtraordinaria> findByAnoLectivoAndAlunoAndDisciplinaAndGuiaPagamentoLiquidadaAndProva(AnoLectivo anoLectivo,Aluno aluno,Disciplina disciplina,boolean liquidada,TipoProvaExtraOrdinaria prova);
	public Page<InscricaoExtraordinaria> findByNomeroDeAluno(String numeroAluno, Pageable paginacao);
	public Page<InscricaoExtraordinaria> findByNomeroDeAlunoAndAnoLectivoAnoLectivo(String numeroAluno,
			Integer anoLectivo, Pageable paginacao);
	
	
	
	
}
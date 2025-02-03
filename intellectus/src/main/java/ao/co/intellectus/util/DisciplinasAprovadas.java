package ao.co.intellectus.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.HistoricoAluno;
import ao.co.intellectus.model.HistoricoEquivalencia;
import ao.co.intellectus.repository.EquivalenciaHistoricoRepository;
import ao.co.intellectus.repository.HistoricoAlunoRepository;



public class DisciplinasAprovadas {
	
	private static List<HistoricoAluno> disciplinas;
	private static Map<Integer, HistoricoAluno> disciplinasCursadas;
	private static Map<Integer, HistoricoAluno> disciplinasCursadasPorFrequencia;
	private static Map<Integer, HistoricoEquivalencia> disciplinasEquivalencias;
	
	
	public static List<HistoricoAluno> pesquisar(Aluno aluno,boolean aprovadas,HistoricoAlunoRepository repositoryCursadas, EquivalenciaHistoricoRepository equivalenciaRepository){	
		disciplinas = new ArrayList<HistoricoAluno>();
		
		List<HistoricoAluno> historicoDisciplinasCursadas=aprovadas ? 
		repositoryCursadas.findByAlunoAndCursoAndAprovadoAndDisciplinaStatusAndNotaFinalGreaterThanEqual(aluno, aluno.getCurso(), aprovadas, true, 10)
		:
		repositoryCursadas.findByAlunoAndCursoAndAprovadoAndDisciplinaStatus(aluno, aluno.getCurso(), aprovadas, true);
		
		
		//System.err.println("CADEIRAS REPROVADAS: "+historicoDisciplinasCursadas.size());
		
		disciplinasCursadas = DisciplinasAprovadas.disciplinasCursadas(historicoDisciplinasCursadas);

		List<HistoricoEquivalencia> historicoDisciplinasEquivalencias = equivalenciaRepository.findByAlunoNumeroDeAlunoAndEstadoAndDisciplinaDestinoCurso(aluno.getNumeroDeAluno(), true,aluno.getCurso());
		disciplinasEquivalencias = DisciplinasAprovadas.disciplinasEquivalencias(historicoDisciplinasEquivalencias);
	
		
		return DisciplinasAprovadas.disciplinas;
	}
	
	private static Map<Integer, HistoricoAluno> disciplinasCursadas(List<HistoricoAluno> historicoDisicplinasCursadas) {
		disciplinasCursadas = new HashMap<Integer, HistoricoAluno>();
		disciplinasCursadasPorFrequencia = new HashMap<Integer, HistoricoAluno>();
		for (HistoricoAluno o : historicoDisicplinasCursadas) {
			if(!disciplinasCursadas.containsKey(o.getDisciplina().getId())){
				
				//if(aprovadas) {}
				if(o.getNotaFinal()!=null) {
					
				}
				
				disciplinasCursadas.put(o.getDisciplina().getId(), o);
				disciplinasCursadasPorFrequencia.put(o.getDisciplina().getId(), o);
			}
		}
		for (HistoricoAluno dadosDaDisciplina : historicoDisicplinasCursadas) {
			boolean containsKey = disciplinasCursadas.containsKey(dadosDaDisciplina.getDisciplina().getId());
			if(containsKey) {
				DisciplinasAprovadas.disciplinas.add(dadosDaDisciplina);
				disciplinasCursadas.remove(dadosDaDisciplina.getDisciplina().getId());
			}
		}
		return disciplinasCursadas;
	}
	
	
	
	private static Map<Integer, HistoricoEquivalencia> disciplinasEquivalencias(List<HistoricoEquivalencia> historicoDisciplinasEquivalencias) {
		HistoricoAluno historicoAluno;
		disciplinasEquivalencias = new HashMap<Integer, HistoricoEquivalencia>();
		for (HistoricoEquivalencia o : historicoDisciplinasEquivalencias) {
			if(!disciplinasCursadas.containsKey(o.getDisciplinaDestino().getId())){
				
				disciplinasEquivalencias.put(o.getDisciplinaDestino().getId(), o);
			}
		}
		
		
		for (HistoricoEquivalencia o : historicoDisciplinasEquivalencias) {
			boolean containsKey = disciplinasCursadasPorFrequencia.containsKey(o.getDisciplinaDestino().getId());
			if(!containsKey) {
				boolean containsKeyB = disciplinasEquivalencias.containsKey(o.getDisciplinaDestino().getId());
				if(containsKeyB) {
					historicoAluno = new HistoricoAluno();
					historicoAluno.setId(o.getDisciplinaDestino().getId());
					//ATRIBUIR O VALOR AO ARRAY DE DISCIPLINAS
					DisciplinasAprovadas.disciplinas.add(historicoAluno);
					disciplinasEquivalencias.remove(o.getDisciplinaDestino().getId());
				}
			}
		}
		return disciplinasEquivalencias;
	}
}


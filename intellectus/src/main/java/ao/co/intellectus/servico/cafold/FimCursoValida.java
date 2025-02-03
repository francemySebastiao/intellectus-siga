package ao.co.intellectus.servico.cafold;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ao.co.intellectus.DTO.HistoricoAlunoCliente;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.HistoricoAluno;
import ao.co.intellectus.model.HistoricoEquivalencia;
import ao.co.intellectus.model.Situacao;
import ao.co.intellectus.repository.DisciplinaRepository;
import ao.co.intellectus.repository.EquivalenciaHistoricoRepository;
import ao.co.intellectus.repository.HistoricoAlunoRepository;

public class FimCursoValida {
	@SuppressWarnings("unused")
	private List<HistoricoAlunoCliente> verificarPlanoAluno(Aluno umAluno,HistoricoAlunoRepository repositoryHistoricoAluno,EquivalenciaHistoricoRepository equivalenciaHistoricoRepository,DisciplinaRepository disciplinaRepository) {
		List<HistoricoAluno> matriculasDoAluno = repositoryHistoricoAluno.findByAlunoAndCursoAndAprovadoAndDisciplinaStatus(umAluno, umAluno.getCurso(), true, true);

		Map<Integer, HistoricoAluno> mapa = new HashMap<Integer, HistoricoAluno>();
		for (HistoricoAluno o : matriculasDoAluno) {
			if (!mapa.containsKey(o.getDisciplina().getId())) {
				if (o.getNotaFinal() != null) {
					if (o.getNotaFinal() >= 10)
						mapa.put(o.getDisciplina().getId(), o);
				}
			}
		}

		Map<Integer, HistoricoEquivalencia> mapaE = new HashMap<Integer, HistoricoEquivalencia>();
		// BUSCA O HISTORICO DE EQUIVALENCIAS
		List<HistoricoEquivalencia> equivalencia = equivalenciaHistoricoRepository.findByAluno(umAluno);
		for (HistoricoEquivalencia o : equivalencia) {
			if (!mapa.containsKey(o.getDisciplinaDestino().getId())) {
				mapaE.put(o.getDisciplinaDestino().getId(), o);
			}
		}

		List<HistoricoAlunoCliente> alunosCliente = new ArrayList<HistoricoAlunoCliente>();
		HistoricoAlunoCliente aluno;

		// busca todas as disciplinas activas de um curso
		List<Disciplina> disciplinas = disciplinaRepository.findByCursoAndStatus(umAluno.getCurso(), true);
		HistoricoAluno buscaHistorico;
		HistoricoEquivalencia historicoEquivalencia;
		for (Disciplina o : disciplinas) {
			aluno = new HistoricoAlunoCliente();
			buscaHistorico = mapa.get(o.getId());
			historicoEquivalencia = mapaE.get(o.getId());

			aluno.setAnoCorricular(o.getAnoCorricular());
			aluno.setDisciplina(o.getDescricao());
			aluno.setCodigoDisciplina(o.getId());

			if (buscaHistorico != null) {
				aluno.setAnoLectivo(buscaHistorico.getAnoLectivo().getAnoLectivo());
				aluno.setAprovado(buscaHistorico.isAprovado());
				aluno.setNotaFinal(buscaHistorico.getNotaFinal());
				aluno.setSituacao(buscaHistorico.getSituacao());
				aluno.setTipoDisciplina(buscaHistorico.getDisciplina().getTipo());
				aluno.setTurma(buscaHistorico.getTurma().getTurma());
			} else if (historicoEquivalencia != null) {
				if (historicoEquivalencia.getNotaOrigem() >= 10) {
					aluno.setAnoLectivo(historicoEquivalencia.getEquivalencia().getAnoLectivo().getAnoLectivo());
					aluno.setAprovado(true);
					aluno.setNotaFinal(historicoEquivalencia.getNotaOrigem());
					aluno.setSituacao(Situacao.APROVADO);
					aluno.setTipoDisciplina(historicoEquivalencia.getDisciplinaDestino().getTipo());
					aluno.setTurma("EQ");
				}
			}
			alunosCliente.add(aluno);
		}
		return alunosCliente;
	}
	
	@SuppressWarnings("unused")
	private String verificarFimCurso(Aluno umAluno,HistoricoAlunoRepository repositoryHistoricoAluno,EquivalenciaHistoricoRepository equivalenciaHistoricoRepository,DisciplinaRepository disciplinaRepository) {
		
		String situacao="";
		
		List<HistoricoAluno> matriculasDoAluno = repositoryHistoricoAluno.findByAlunoAndCursoAndAprovadoAndDisciplinaStatusAndValidadaAndNotaFinalGreaterThanEqual(umAluno, umAluno.getCurso(), true, true,true,10);

		
		
		Map<Integer, HistoricoAluno> mapa = new HashMap<Integer, HistoricoAluno>();
		for (HistoricoAluno o : matriculasDoAluno) {
			if (!mapa.containsKey(o.getDisciplina().getId())) {
				if (o.getNotaFinal() != null) {
					if (o.getNotaFinal() >= 10)
						mapa.put(o.getDisciplina().getId(), o);
				}
			}
		}

		Map<Integer, HistoricoEquivalencia> mapaE = new HashMap<Integer, HistoricoEquivalencia>();
		// BUSCA O HISTORICO DE EQUIVALENCIAS
		List<HistoricoEquivalencia> equivalencia = equivalenciaHistoricoRepository.findByAluno(umAluno);
		for (HistoricoEquivalencia o : equivalencia) {
			if (!mapa.containsKey(o.getDisciplinaDestino().getId())) {
				mapaE.put(o.getDisciplinaDestino().getId(), o);
			}
		}

		List<HistoricoAlunoCliente> alunosCliente = new ArrayList<HistoricoAlunoCliente>();
		
		HistoricoAlunoCliente aluno;

		// busca todas as disciplinas activas de um curso
		List<Disciplina> disciplinas = disciplinaRepository.findByCursoAndStatus(umAluno.getCurso(), true);
		
		
		
		HistoricoAluno buscaHistorico;
		HistoricoEquivalencia historicoEquivalencia;
		for (Disciplina o : disciplinas) {
			aluno = new HistoricoAlunoCliente();
			buscaHistorico = mapa.get(o.getId());
			historicoEquivalencia = mapaE.get(o.getId());

			aluno.setAnoCorricular(o.getAnoCorricular());
			aluno.setDisciplina(o.getDescricao());
			aluno.setCodigoDisciplina(o.getId());

			if (buscaHistorico != null) {
				aluno.setAnoLectivo(buscaHistorico.getAnoLectivo().getAnoLectivo());
				aluno.setAprovado(buscaHistorico.isAprovado());
				aluno.setNotaFinal(buscaHistorico.getNotaFinal());
				aluno.setSituacao(buscaHistorico.getSituacao());
				aluno.setTipoDisciplina(buscaHistorico.getDisciplina().getTipo());
				aluno.setTurma(buscaHistorico.getTurma().getTurma());
			} else if (historicoEquivalencia != null) {
				if (historicoEquivalencia.getNotaOrigem() >= 10) {
					aluno.setAnoLectivo(historicoEquivalencia.getEquivalencia().getAnoLectivo().getAnoLectivo());
					aluno.setAprovado(true);
					aluno.setNotaFinal(historicoEquivalencia.getNotaOrigem());
					aluno.setSituacao(Situacao.APROVADO);
					aluno.setTipoDisciplina(historicoEquivalencia.getDisciplinaDestino().getTipo());
					aluno.setTurma("EQ");
				}
			}
			alunosCliente.add(aluno);
		}
		
		int aprovadas  =0;
		int totalCurso = disciplinas.size();
        for (HistoricoAlunoCliente o : alunosCliente) {
			if(o.getNotaFinal()!=null && o.isAprovado()) {
				aprovadas++;
			}
		}
		
        if(aprovadas>=totalCurso) {
        	situacao="FINALIZADO";
        }else {
        	situacao="Finalizadas "+aprovadas+"disciplinas de"+totalCurso;
        }
		return situacao;
	}
}

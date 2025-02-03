package ao.co.intellectus.servico.cafold;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.DisciplinaConcluidaCliente;
import ao.co.intellectus.DTO.DisciplinaCursandoCliente;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.CertificadoIntermedio;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.HistoricoAluno;
import ao.co.intellectus.model.HistoricoEquivalencia;
import ao.co.intellectus.repository.CertificadoIntermedioRepository;
import ao.co.intellectus.repository.DisciplinaRepository;
import ao.co.intellectus.repository.EquivalenciaHistoricoRepository;
import ao.co.intellectus.repository.HistoricoAlunoRepository;

@Service
public class DisciplinaService {
	
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private HistoricoAlunoRepository historicoAlunoRepository;
	@Autowired
	private EquivalenciaHistoricoRepository equivalenciaHistoricoRepository;
	@Autowired
	private CertificadoIntermedioRepository certificadoIntermedioRepository;
	
	public List<Disciplina> disciplinasPorCurso(Curso curso){
		return this.disciplinaRepository.findByCursoAndStatus(curso, true);
	}
	
	public 	List<CertificadoIntermedio> disciplinasCursadas(Aluno aluno){
		return this.certificadoIntermedioRepository.findByNumeroAndValidacao(Integer.parseInt(aluno.getNumeroDeAluno()), true);
	}

	public List<DisciplinaCursandoCliente> disciplinasInscritas(String numeroDoAluno) {
		List<DisciplinaCursandoCliente> disciplinasHistorico = new ArrayList<DisciplinaCursandoCliente>();
		List<HistoricoAluno> historicoAluno = this.historicoAlunoRepository.findByAlunoNumeroDeAluno(numeroDoAluno);
		for (HistoricoAluno haluno : historicoAluno) {
			DisciplinaCursandoCliente disciplinaHistorico = new DisciplinaCursandoCliente();

			disciplinaHistorico.setIdMatricula(haluno.getMatricula().getId());
			disciplinaHistorico.setDisciplina(haluno.getDisciplina().getDescricao());
			disciplinaHistorico.setAnoLectivo(haluno.getAnoLectivo().getAnoLectivo());
			disciplinaHistorico.setAnoCurricular(haluno.getDisciplina().getAnoCorricular());
			disciplinaHistorico.setTipo(haluno.getDisciplina().getTipo());
			disciplinaHistorico.setTurma(haluno.getTurma().getTurma());
			disciplinaHistorico.setValidado(haluno.isValidada());
			
			//FREQUENCIAS
			disciplinaHistorico.setPrimeiraAvaliacao(haluno.getAvaliacao1());
			disciplinaHistorico.setSegundaAvaliacvao(haluno.getAvaliacao2());
			disciplinaHistorico.setTerceiraAvaliacao(haluno.getAvaliacao3());
			disciplinaHistorico.setQuartaAvaliacao(haluno.getAvaliacao4());
			disciplinaHistorico.setPratica(haluno.getNotaPratica());
			
			// NOTAS...
			disciplinaHistorico.setFinalFrequencia(haluno.getNotaFinalContinua());
			disciplinaHistorico.setNotaFinal(haluno.getNotaFinal());
			disciplinaHistorico.setMelhoriaNota(haluno.getMelhoriaNota());
			// ÉPOCA ESPECIAL
			disciplinaHistorico.setNotaEpocaEspecial(haluno.getNotaEpocaEspecial());
			disciplinaHistorico.setNotaEpocaEspecialOral(haluno.getNotaEpocaEspecialOral());
			// EXAME
			disciplinaHistorico.setNotaExame(haluno.getNotaExame());
			disciplinaHistorico.setNotaExameOral(haluno.getNotaExameOral());
			// RECURSO
			disciplinaHistorico.setNotaRecurso(haluno.getNotaRecurso());
			disciplinaHistorico.setNotaRecursoOral(haluno.getNotaRecursoOral());

			// disciplinaHistorico.set
			disciplinasHistorico.add(disciplinaHistorico);
		}
		return disciplinasHistorico;
	}
	
	public List<DisciplinaConcluidaCliente>  disciplinasAprovadas(String numeroDoAluno){
		Aluno aluno = (numeroDoAluno == null) ? null: this.alunoService.aluno(numeroDoAluno);		
		if(aluno == null) {
			return null;
		}else {
			List<DisciplinaConcluidaCliente> disciplinasConcluidas = new ArrayList<DisciplinaConcluidaCliente>();
			List<Disciplina> disciplinasCurso = this.disciplinaRepository.findByCursoAndStatus(aluno.getCurso(), true);
			List<HistoricoAluno> aprovadas = this.historicoAlunoRepository.findByAlunoAndCursoAndAprovadoAndDisciplinaStatus(aluno,aluno.getCurso(),true,true);
			
			//AQUI TRATAMOS AS DISCIPLINAS QUE FORAM CONCLUIDAS... TANTO A EQUIVALENCIA QUANTO A FREQUENCIA NORMAL
			Map<Integer, HistoricoAluno> mapaA =new HashMap<Integer, HistoricoAluno>();
			Map<Integer, HistoricoAluno> mapaAB=new HashMap<Integer, HistoricoAluno>();
			
			for (HistoricoAluno o : aprovadas) {
				if(!mapaA.containsKey(o.getDisciplina().getId()))
				{
					mapaA.put(o.getDisciplina().getId(), o);
					mapaAB.put(o.getDisciplina().getId(), o);
				}
			}
			
			//VER DADOS DO HISTORICO
			//HistoricoAluno mapaHa;
			for (HistoricoAluno o : aprovadas) {
				DisciplinaConcluidaCliente disciplinasConcluida = new DisciplinaConcluidaCliente();

				boolean containsKey = mapaA.containsKey(o.getDisciplina().getId());
				if(containsKey) {
					disciplinasConcluida.setAnoCurricular(o.getDisciplina().getAnoCorricular());
					disciplinasConcluida.setAnoLectivo(o.getAnoLectivo().getAnoLectivo().toString());
					disciplinasConcluida.setDisciplina(o.getDisciplina().getDescricao());

					disciplinasConcluida.setNota(o.getNotaFinal());

					disciplinasConcluida.setSigla(o.getDisciplina().getSigla());
					disciplinasConcluida.setTipo(o.getDisciplina().getTipo());
					disciplinasConcluida.setTotalCurso(disciplinasCurso.size());

					//ATRIBUIR O VALOR AO ARRAY DE DISCIPLINAS
					disciplinasConcluidas.add(disciplinasConcluida);	
					
					mapaA.remove(o.getDisciplina().getId());
				}
			}
			
			
			
			Map<Integer, HistoricoEquivalencia> mapaB=new HashMap<Integer, HistoricoEquivalencia>();
			
			//VER DADOS DE EQUIVALENCIA
			//BUSCA O HISTORICO DE EQUIVALENCIAS			
			
			
			List<HistoricoEquivalencia> equivalencia = this.equivalenciaHistoricoRepository.findByAlunoNumeroDeAlunoAndEstadoAndDisciplinaDestinoCurso(aluno.getNumeroDeAluno(), true,aluno.getCurso());
			for (HistoricoEquivalencia o : equivalencia) {
				if(!mapaA.containsKey(o.getDisciplinaDestino().getId()))
				{
					mapaB.put(o.getDisciplinaDestino().getId(), o);
				}
			}
					
			for (HistoricoEquivalencia o : equivalencia) {
				DisciplinaConcluidaCliente disciplinasConcluida = new DisciplinaConcluidaCliente();
				//VERIFICA SE EXISTE NO MAPA A
				
				//mapaA.get(o.getDisciplinaDestino().getId());
				
				boolean containsKey = mapaAB.containsKey(o.getDisciplinaDestino().getId());
				
				if(!containsKey) {
					boolean containsKeyB = mapaB.containsKey(o.getDisciplinaDestino().getId());
					
					if(containsKeyB) {
						disciplinasConcluida.setAnoCurricular(o.getDisciplinaDestino().getAnoCorricular());
						disciplinasConcluida.setAnoLectivo(o.getEquivalencia().getAnoLectivo().getAnoLectivo().toString());
						disciplinasConcluida.setDisciplina(o.getDisciplinaDestino().getDescricao());
						disciplinasConcluida.setNota(o.getNotaOrigem());
						disciplinasConcluida.setSigla(o.getDisciplinaDestino().getSigla());
						disciplinasConcluida.setTipo(o.getDisciplinaDestino().getTipo());
						disciplinasConcluida.setEquivalencia(true);
						
						//ATRIBUIR O VALOR AO ARRAY DE DISCIPLINAS
						disciplinasConcluidas.add(disciplinasConcluida);
						mapaB.remove(o.getDisciplinaDestino().getId());
					}
				}
			}
			return disciplinasConcluidas;
		}
	}
	
	
	
}

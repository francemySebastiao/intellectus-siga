package ao.co.intellectus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.BaldeDisciplinas;
import ao.co.intellectus.DTO.BolseiroMatriculaEdicao;
import ao.co.intellectus.DTO.DisciplinaCliente;
import ao.co.intellectus.DTO.DisciplinaMatricula;
import ao.co.intellectus.DTO.DisciplinasTurmasCliente;
import ao.co.intellectus.DTO.EdicaoMatriculaNova;
import ao.co.intellectus.DTO.PassoDeEdicao;
import ao.co.intellectus.DTO.PlanoPagamentoEdicao;
import ao.co.intellectus.DTO.PreparacaoMatricula2Passo;
import ao.co.intellectus.DTO.PreparacaoMatriculaCliente;
import ao.co.intellectus.DTO.TipoInscricaoEdicao;
import ao.co.intellectus.DTO.TurmaCliente;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.EmpresaConvenio;
import ao.co.intellectus.model.Equivalencia;
import ao.co.intellectus.model.Grau;
import ao.co.intellectus.model.HistoricoAluno;
import ao.co.intellectus.model.HistoricoEquivalencia;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.PlanoPagamento;
import ao.co.intellectus.model.TipoInscricao;
import ao.co.intellectus.model.Turma;
import ao.co.intellectus.model.TurmaDisponivel;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.CursoRepository;
import ao.co.intellectus.repository.DisciplinaRepository;
import ao.co.intellectus.repository.EmpresaConvenioRepository;
import ao.co.intellectus.repository.EquivalenciaHistoricoRepository;
import ao.co.intellectus.repository.EquivalenciaRepository;
import ao.co.intellectus.repository.HistoricoAlunoRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.repository.PlanoPagamentoRepository;
import ao.co.intellectus.repository.TipoDeInscricaoRepository;
import ao.co.intellectus.repository.TurmaDisponivelRepository;
import ao.co.intellectus.repository.TurmaRepository;


@RestController
@RequestMapping("/preparacaoMatricula")
public class ControllerPreparacaoMatricula {
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private HistoricoAlunoRepository historicoAlunoRepository;
	@Autowired
	private TipoDeInscricaoRepository tipoDeInscricaoRepository;
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private TurmaDisponivelRepository turmaDisponivel;
	@Autowired
	private EquivalenciaRepository equivalenciaRepository;
	@Autowired
	private EquivalenciaHistoricoRepository equivalenciaHistoricoRepository;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private EmpresaConvenioRepository empresaConvenio;
	@Autowired
	private PlanoPagamentoRepository planoDePagamento;
	@Autowired
	private CursoRepository cursoRepository;
	@Autowired
	private TurmaRepository turmaRepository;

	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/preparar/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> prepararMatricula(@PathVariable Integer numero) {
		ResponseCliente c = new ResponseCliente();
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(numero.toString());
		List<AnoLectivo> anoLectivo = this.anoLectivoRepository.findByStatus(true);

		Matricula matricula = this.matriculaRepository.findByAlunoAndAnoLectivoAndAnulado(aluno, anoLectivo.get(0),false);
		
		//COLCOAR AS DISCIPLINAS DO CURSO EM UM BALDE
		BaldeDisciplinas balde;
		List<BaldeDisciplinas> balds=new ArrayList<BaldeDisciplinas>();
		List<Disciplina> disciplinas = this.disciplinaRepository.findByCursoAndStatus(aluno.getCurso(), true);
		
		int duracaoCurso=0;
		for (Disciplina o : disciplinas) {
			balde=new BaldeDisciplinas();
			balde.setAnoCurricular(o.getAnoCorricular());
			balde.setId(o.getId());
			
			balds.add(balde);
			
			if(o.getAnoCorricular()>duracaoCurso) {
				duracaoCurso=o.getAnoCorricular();				
			}
		}
		if (matricula == null) {
			// List<HistoricoAluno> hAluno;
			int diferenca = 0;
			int diferencaDif=0;
			
			int d1=0;
			int d2=0;
			int d3=0;
			int d4=0;
			int d5=0;
			
			boolean aprovado=false;
			
			int diferencaAnulada=0;

			int quantidadeAnosCurso = 0;
			int disciplinasFeita = 0;

			List<TipoInscricao> tipoInscricoesReprovados = new ArrayList<>();
			List<TipoInscricao> tipoInscricoes = this.tipoDeInscricaoRepository.findByStatus(true);

			
			// BUSCAR ALUNO PARA MONTAR A PREPARAÇÃO DA MATRICULA
			PreparacaoMatriculaCliente mCliente = new PreparacaoMatriculaCliente();
			
			mCliente.setNome(aluno.getNome());
			mCliente.setNumeroDeAluno(Integer.parseInt(aluno.getNumeroDeAluno()));
			mCliente.setCurso(aluno.getCurso().getPlano());
			mCliente.setCursoCodigo(aluno.getCurso().getId());
			mCliente.setContencioso(aluno.isContencioso());
			mCliente.setFimCurso(aluno.isFimCurso());
			mCliente.setInativo(aluno.isInactivo()); 
			mCliente.setAnoLectivo(anoLectivo.get(0).getId());

			List<DisciplinaMatricula> dMatriculas = new ArrayList<DisciplinaMatricula>();
			DisciplinaMatricula dMatricula;

			Disciplina ds;
			// BUSCA TODAS AS EQUIVALENCIAS DO ALUNO.
			List<Equivalencia> equivalencia = this.equivalenciaRepository.findByAluno(aluno);
			
			
			List<HistoricoEquivalencia> equivale;
			for (Equivalencia eq : equivalencia) {
				equivale = this.equivalenciaHistoricoRepository.findByEquivalencia(eq);
				for (HistoricoEquivalencia historicoEq : equivale) {
					ds = this.disciplinaRepository.findByCursoAndIdAndStatus(aluno.getCurso(),historicoEq.getDisciplinaDestino().getId(), true);

					if(ds!=null) {                                                                            
					dMatricula = new DisciplinaMatricula();
					dMatricula.setDescricao(ds.getDescricao());
					dMatricula.setId(ds.getId());
					dMatricula.setAnoCorricular(ds.getAnoCorricular());
					dMatricula.setConcluida(true);
					
					if(historicoEq.getNotaOrigem()>=10) {
						dMatriculas.add(dMatricula);					
					}
					}
				}
			}
			
			
			// 	BUSCA O HISTÓRICO COMPLETO DO ALUNO
			List<HistoricoAluno> historico = this.historicoAlunoRepository.findByAlunoAndCurso(aluno,aluno.getCurso());
			
			for (HistoricoAluno ha : historico) {
				if (ha.isAprovado()) {
					dMatricula = new DisciplinaMatricula();
					dMatricula.setDescricao(ha.getDisciplina().getDescricao());
					dMatricula.setId(ha.getDisciplina().getId());
					dMatricula.setAnoCorricular(ha.getDisciplina().getAnoCorricular());
					dMatricula.setConcluida(ha.isAprovado());
					dMatriculas.add(dMatricula);
				}
			}
			
			// VERIFICA SE É PRIMEIRA INSCRIÇÃO
			if (historico.isEmpty() && equivalencia.isEmpty()) {
				for (TipoInscricao tipoInscricao : tipoInscricoes) {
					if (tipoInscricao.getId() == 1 || tipoInscricao.getId() == 5)
						tipoInscricoesReprovados.add(tipoInscricao);
				}
				mCliente.setTipoInscricoes(tipoInscricoesReprovados);
				mCliente.setAnoCurricular(1);
				c.setCodigo(ResponseCode.values()[0].getDescricao());
				c.setResultado(mCliente);
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
			List<DisciplinaMatricula> disp = dMatriculas;
			List<BaldeDisciplinas> b = balds;
			
			List<BaldeDisciplinas> maps =new ArrayList<BaldeDisciplinas>();
			BaldeDisciplinas map;
			Integer disc1 ;
			Integer disc2 ;
			int dp1=0;
			int dp2=0;
			int dp3=0;
			int dp4=0;
			int dp5=0;
			
			for (BaldeDisciplinas bls : b) {
				if(bls.getAnoCurricular()==1) {
					dp1++;
				}
				if(bls.getAnoCurricular()==2) {
					dp2++;
				}
				if(bls.getAnoCurricular()==3) {
					dp3++;
				}
				if(bls.getAnoCurricular()==4) {
					dp4++;
				}
				
				if(bls.getAnoCurricular()==5) {
					dp5++;
				}
				
				
				map =new BaldeDisciplinas();
				map.setAnoCurricular(bls.getAnoCurricular());
				map.setId(bls.getId());
				for (DisciplinaMatricula po : dMatriculas) {
					disc1 = bls.getId();
					disc2 = po.getId();
					if(disc1.equals(disc2)) {						
						map.setAprovada(true);
						
					}
				}
				maps.add(map);
			}
		
			int o  =0;
			int ap1=0;
			int ap2=0;
			int ap3=0;
			int ap4=0;
			int ap5=0;
			
			
			for (BaldeDisciplinas bls : maps) {
				if(bls.getAnoCurricular()==1 && bls.isAprovada()) {
					ap1++;
				}
				
				if(bls.getAnoCurricular()==2 && bls.isAprovada()) {
					ap2++;
				}
				
				if(bls.getAnoCurricular()==3 && bls.isAprovada()) {
					ap3++;
				}
				
				if(bls.getAnoCurricular()==4 && bls.isAprovada()) {
					ap4++;
				}
				
				if(bls.getAnoCurricular()==5 && bls.isAprovada()) {
					ap5++;
				}
				
			}
			
			int r1=dp1-ap1;
			int r2=dp2-ap2;
			int r3=dp3-ap3;
			int r4=dp4-ap4;
			int r5=dp5-ap5;

			
			//1º ANO
			List<TipoInscricao> tipoCerto = tipoInscricoes;
			 
            List<TipoInscricao>  tipoInscrever=new ArrayList<TipoInscricao>();
            
            
		    //validar
			if(aluno.getCurso().getGrau().equals(Grau.MESTRADO)) {
				if(r1>0) {
					//SE REPROVOU NO PRIMEIRO ANO, DEVE APENAS SE INSCREVER POR DISCIPLINA.
					if(ap1>0) {
						for (TipoInscricao tipoInscricao : tipoCerto) {
							if(tipoInscricao.getId()==4 || tipoInscricao.getId()==5) {						
								tipoInscrever.add(tipoInscricao);	
							}
						}
						mCliente.setTipoInscricoes(tipoInscrever);	
					}else {
						mCliente.setTipoInscricoes(tipoInscricoes);	
					}
					
					mCliente.setAnoCurricular(1);
					c.setCodigo(ResponseCode.values()[0].getDescricao());
					c.setResultado(mCliente);
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
				
				
				if(r1+r2>0) {
					//NOVA FORMA DE VER AS INSCRIÇÕES
					if(ap2>0) {
						for (TipoInscricao tipoInscricao : tipoCerto) {
							if(tipoInscricao.getId()==4 || tipoInscricao.getId()==5) {						
								tipoInscrever.add(tipoInscricao);	
							}
						}
						mCliente.setTipoInscricoes(tipoInscrever);
					}else {
						mCliente.setTipoInscricoes(tipoInscricoes);	
					}
					mCliente.setAnoCurricular(2);
					c.setCodigo(ResponseCode.values()[0].getDescricao());
					c.setResultado(mCliente);
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
				
				
				//3º ANO
				if(r1+r2+r3>0) {
					//NOVA FORMA DE VER AS INSCRIÇÕES
					if(ap3>0) {
						for (TipoInscricao tipoInscricao : tipoCerto) {
							if(tipoInscricao.getId()==4 || tipoInscricao.getId()==5) {						
								tipoInscrever.add(tipoInscricao);	
							}
						}
						mCliente.setTipoInscricoes(tipoInscrever);	
					}else {
						mCliente.setTipoInscricoes(tipoInscricoes);	
					}
					
					//mCliente.setTipoInscricoes(tipoInscricoes);
					mCliente.setAnoCurricular(3);
					c.setCodigo(ResponseCode.values()[0].getDescricao());
					c.setResultado(mCliente);
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
				
				
				
				//4º ANO
				if(r1+r2+r3+r4>0) {
					//NOVA FORMA DE VER AS INSCRIÇÕES
					if(ap4>0) {
						for (TipoInscricao tipoInscricao : tipoCerto) {
							if(tipoInscricao.getId()==4 || tipoInscricao.getId()==5) {						
								tipoInscrever.add(tipoInscricao);	
							}
						}
						mCliente.setTipoInscricoes(tipoInscrever);	
					}else {
						mCliente.setTipoInscricoes(tipoInscricoes);	
					}
					//mCliente.setTipoInscricoes(tipoInscricoes);
					mCliente.setAnoCurricular(4);
					c.setCodigo(ResponseCode.values()[0].getDescricao());
					c.setResultado(mCliente);
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
				
				mCliente.setTipoInscricoes(tipoInscricoes);
				mCliente.setAnoCurricular(2);
				c.setCodigo(ResponseCode.values()[0].getDescricao());
				c.setResultado(mCliente);
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}else {
				
				if(r1>3) {
					//SE REPROVOU NO PRIMEIRO ANO, DEVE APENAS SE INSCREVER POR DISCIPLINA.
					if(ap1>0) {
						for (TipoInscricao tipoInscricao : tipoCerto) {
							if(tipoInscricao.getId()==4 || tipoInscricao.getId()==5) {						
								tipoInscrever.add(tipoInscricao);	
							}
						}
						mCliente.setTipoInscricoes(tipoInscrever);	
					}else {
						mCliente.setTipoInscricoes(tipoInscricoes);	
					}
					
					mCliente.setAnoCurricular(1);
					c.setCodigo(ResponseCode.values()[0].getDescricao());
					c.setResultado(mCliente);
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
				
				
				
				if(r1+r2>3) {
					//NOVA FORMA DE VER AS INSCRIÇÕES
					if(ap2>0) {
						for (TipoInscricao tipoInscricao : tipoCerto) {
							if(tipoInscricao.getId()==4 || tipoInscricao.getId()==5) {						
								tipoInscrever.add(tipoInscricao);	
							}
						}
						mCliente.setTipoInscricoes(tipoInscrever);
					}else {
						mCliente.setTipoInscricoes(tipoInscricoes);	
					}
					mCliente.setAnoCurricular(2);
					c.setCodigo(ResponseCode.values()[0].getDescricao());
					c.setResultado(mCliente);
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
				
				
				//3º ANO
				if(r1+r2+r3>3) {
					//NOVA FORMA DE VER AS INSCRIÇÕES
					if(ap3>0) {
						for (TipoInscricao tipoInscricao : tipoCerto) {
							if(tipoInscricao.getId()==4 || tipoInscricao.getId()==5) {						
								tipoInscrever.add(tipoInscricao);	
							}
						}
						mCliente.setTipoInscricoes(tipoInscrever);	
					}else {
						mCliente.setTipoInscricoes(tipoInscricoes);	
					}
					
					//mCliente.setTipoInscricoes(tipoInscricoes);
					mCliente.setAnoCurricular(3);
					c.setCodigo(ResponseCode.values()[0].getDescricao());
					c.setResultado(mCliente);
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
				
				
				
				//4º ANO
				if(r1+r2+r3+r4>3) {
					//NOVA FORMA DE VER AS INSCRIÇÕES
					if(ap4>0) {
						for (TipoInscricao tipoInscricao : tipoCerto) {
							if(tipoInscricao.getId()==4 || tipoInscricao.getId()==5) {						
								tipoInscrever.add(tipoInscricao);	
							}
						}
						mCliente.setTipoInscricoes(tipoInscrever);	
					}else {
						mCliente.setTipoInscricoes(tipoInscricoes);	
					}
					//mCliente.setTipoInscricoes(tipoInscricoes);
					mCliente.setAnoCurricular(4);
					c.setCodigo(ResponseCode.values()[0].getDescricao());
					c.setResultado(mCliente);
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
				
				
				//5º ANO
				if(duracaoCurso==5) {
					if(r1+r2+r3+r4+r5>3) {
						//NOVA FORMA DE VER AS INSCRIÇÕES
						if(ap5>0) {
							for (TipoInscricao tipoInscricao : tipoCerto) {
								if(tipoInscricao.getId()==4 || tipoInscricao.getId()==5) {						
									tipoInscrever.add(tipoInscricao);	
								}
							}
							mCliente.setTipoInscricoes(tipoInscrever);	
						}else {
							mCliente.setTipoInscricoes(tipoInscricoes);	
						}
						//mCliente.setTipoInscricoes(tipoInscricoes);
						mCliente.setAnoCurricular(5);
						c.setCodigo(ResponseCode.values()[0].getDescricao());
						c.setResultado(mCliente);
						return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
					}					
				}
				
				mCliente.setTipoInscricoes(tipoInscricoes);
				mCliente.setAnoCurricular(4);
				c.setCodigo(ResponseCode.values()[0].getDescricao());
				c.setResultado(mCliente);
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
		} else {
			c.setMensagem("O aluno já está inscrito neste ano.");
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

	}


	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/buscarDisciplinasForMatricula", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarDisciplinasForMatricula(@RequestBody PreparacaoMatricula2Passo prepare) {
		ResponseCliente c = new ResponseCliente();
		TipoInscricao tipoInscricao = this.tipoDeInscricaoRepository.findOne(prepare.getTipoInscricao());	
		// buscar o aluno em retrato.
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(prepare.getNumeroDeAluno().toString());
		DisciplinasTurmasCliente dtc = new DisciplinasTurmasCliente();
		// preparar as disciplinas
		List<DisciplinaCliente> disciplinas = new ArrayList<DisciplinaCliente>();
		DisciplinaCliente disciplina;
		
		// busca todas as disciplinas do curso do aluno.
		List<Disciplina> disciplinasCurso = this.disciplinaRepository.findByCursoAndStatus(aluno.getCurso(), true);

		for (Disciplina ds : disciplinasCurso) {
			disciplina = new DisciplinaCliente();
			disciplina.setAnoCurricular(ds.getAnoCorricular());
			disciplina.setDescricao(ds.getDescricao());
			disciplina.setId(ds.getId());
			disciplina.setSigla(ds.getSigla());
			disciplina.setTipoDisciplina(ds.getTipo());
			// BUSCA OS HISTORICO DA DISCIPLINA SE NÃO ACHAR VAI A PROXIMA
			List<HistoricoAluno> historicoA = this.historicoAlunoRepository.findByDisciplinaAndAluno(ds, aluno);

			//PALA
			
			if (!historicoA.isEmpty()) {
				for (HistoricoAluno ha : historicoA) {
					if (ha.isAprovado()) {
						disciplina.setConcluida(true);
						disciplina.setMatricula(false);
					} else {
						disciplina.setMatricula(true);
					}
				}
			} else {
				// BUSCA AS EQUIVALENCIAS DE UM ALUNO SE NÃO ACHAR VAI A PROXIMA
				List<HistoricoEquivalencia> equivalencias = this.equivalenciaHistoricoRepository.findByAlunoAndDisciplinaDestino(aluno, ds);
				if (!equivalencias.isEmpty()) {
					for (HistoricoEquivalencia eq : equivalencias) {
						if (eq.getNotaOrigem() >= 10) {
							disciplina.setConcluida(true);
							disciplina.setMatricula(false);
						}else {
							disciplina.setConcluida(false);
							disciplina.setMatricula(true);
						}
					}
				} else {
					if (prepare.getAnoCurricular() >= ds.getAnoCorricular()) {
						disciplina.setMatricula(true);
					} else {
						disciplina.setMatricula(false);
					}
				}
			}

			
			if(tipoInscricao.getId()==4 && !disciplina.isConcluida()) {
				disciplina.setMatricula(true);
			}
			
			setarTurma(aluno, disciplina, prepare.getTurno().getDescricao());
			disciplinas.add(disciplina);
		}
		// ADICIONAR AO OBJECTO DE RETORNO.
		dtc.setDisciplinas(disciplinas);

		// preparar as turmas abertas para essse curso.
		List<TurmaCliente> turmas = new ArrayList<TurmaCliente>();
		TurmaCliente turma;
		List<TurmaDisponivel> turmasCurso = this.turmaDisponivel.findByCursoAndAceitaInscricoes(aluno.getCurso(), true);

		for (TurmaDisponivel turmaDisponivel : turmasCurso) {
			turma = new TurmaCliente();
			turma.setIdTurma(turmaDisponivel.getTurma().getId());
			turma.setTurma(turmaDisponivel.getTurma().getTurma());
			turma.setTurno(turmaDisponivel.getTurma().getTurno());
			turmas.add(turma);
		}
		dtc.setTurmas(turmas);

		// recebe a turma base da inscrição...
		
		
		
		Turma tb = this.buscaTurmaBase(aluno, prepare.getAnoCurricular(), prepare.getTurno().getDescricao());
		if(tb==null) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Não existe turma aberta neste turno.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		dtc.setTurmaBase(tb.getId());
		dtc.setTurmaBaseStr(tb.getTurma());
		
		Integer duracaoCurso = this.cursoRepository.findById(aluno.getCurso().getId()).getDuracao();
		dtc.setDuracaoCurso(duracaoCurso);
		dtc.setAnoCorricular(prepare.getAnoCurricular());
		dtc.setTipoInscricao(prepare.getTipoInscricao());

		List<AnoLectivo> anoLectivos = this.anoLectivoRepository.findByStatus(true);
		for (AnoLectivo anoLectivo : anoLectivos) {
			dtc.setAnoLectivo(anoLectivo.getId());
		}

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(dtc);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	
	
	@RequestMapping(value = "/buscarDisciplinasForMatriculaColegio", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarDisciplinasForMatriculaColegio(@RequestBody PreparacaoMatricula2Passo prepare) {
		ResponseCliente c = new ResponseCliente();
		DisciplinasTurmasCliente dtc = new DisciplinasTurmasCliente();
		//Aluno aluno = this.alunoRepository.findByNumeroDeAluno(prepare.getNumeroDeAluno().toString());
		Turma turma = this.turmaRepository.findOne(prepare.getTurma());
		
		DisciplinaCliente dCurso;
		List<DisciplinaCliente> dsCurso=new ArrayList<DisciplinaCliente>();
	    List<Disciplina> displinas = this.disciplinaRepository.findByCurso(this.cursoRepository.findById(Integer.parseInt(prepare.getCurso())));
		for (Disciplina o : displinas) {
			dCurso=new DisciplinaCliente();
			
			dCurso.setAdicionar(true);
			dCurso.setConcluida(false);
			dCurso.setMatriculaCorrente(true);
			dCurso.setDisciplinaChecada(true);
			dCurso.setDescricao(o.getDescricao());
			dCurso.setTurma(turma.getTurma());
			dCurso.setSigla(o.getSigla());
			dCurso.setTipoDisciplina(o.getTipo());
			dCurso.setAnoCurricular(1);
			
			
			dsCurso.add(dCurso);
		}
		dtc.setDisciplinas(dsCurso);
		
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(dtc);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	

	//preparacaoMatricula/buscarDisciplinasForEdicao
	@RequestMapping(value = "/buscarDisciplinasForEdicao", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarDisciplinasForEdicao(@RequestBody PassoDeEdicao prepare) {
		ResponseCliente c = new ResponseCliente();
		
		
		
		Matricula matricula = this.matriculaRepository.findOne(prepare.getMatricula());
		
		
		
		if(matricula==null) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Verifique a matricula");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);	
		}
		
		List<DisciplinaCliente> disciplinas = new ArrayList<DisciplinaCliente>();
		DisciplinaCliente disciplina;

		List<Disciplina> disciplinasCurso = this.disciplinaRepository.findByCursoAndStatus(matricula.getCurso(), true);
		
		for (Disciplina ds : disciplinasCurso) {
			disciplina = new DisciplinaCliente();
			disciplina.setAnoCurricular(ds.getAnoCorricular());
			disciplina.setDescricao(ds.getDescricao());
			disciplina.setId(ds.getId());
			disciplina.setSigla(ds.getSigla());
			disciplina.setTipoDisciplina(ds.getTipo());
			// BUSCA OS HISTORICO DA DISCIPLINA SE NÃO ACHAR VAI A PROXIMA
			List<HistoricoAluno> historicoA = this.historicoAlunoRepository.findByDisciplinaAndAluno(ds, matricula.getAluno());

			//PALA
			
			if (!historicoA.isEmpty()) {
				for (HistoricoAluno ha : historicoA) {
					disciplina.setIdHistorico(ha.getId());
					if (ha.isAprovado()) {
						
						disciplina.setConcluida(true);
						disciplina.setMatricula(false);
					} else {
						disciplina.setMatricula(true);
					}
					if(ha.getTurma()!=null)
					disciplina.setTurma(ha.getTurma().getTurma()); 
					
                    if(ha.getMatricula()!=null) {
                   
                    	//System.out.println("TESTE 2"+ha.getMatricula().getId());
                    	
    					if(prepare.getMatricula()==ha.getMatricula().getId()) {
    						disciplina.setMatriculaCorrente(true);
    					}                    	
                    }

				}
			} else {
				// BUSCA AS EQUIVALENCIAS DE UM ALUNO SE NÃO ACHAR VAI A PROXIMA
				List<HistoricoEquivalencia> equivalencias = this.equivalenciaHistoricoRepository.findByAlunoAndDisciplinaDestino(matricula.getAluno(), ds);
				if (!equivalencias.isEmpty()) {
					for (HistoricoEquivalencia eq : equivalencias) {
						//disciplina.setEquivalencia(true);
						if (eq.getNotaOrigem() >= 10) {
							disciplina.setConcluida(true);
							disciplina.setMatricula(false);
						}else {
							disciplina.setConcluida(false);
							disciplina.setMatricula(true);
						}
					}
				} else {
					if (matricula.getAnoCurricular() >= ds.getAnoCorricular()) {
						disciplina.setMatricula(true);
					} else {
						disciplina.setMatricula(false);
					}
				}
			}
			if(matricula.getTipoInscricao().getId()==4 && !disciplina.isConcluida()) {
				disciplina.setMatricula(true);
			}
			
			
			disciplinas.add(disciplina);
		}
		
		
		EdicaoMatriculaNova retorno=new EdicaoMatriculaNova();
		// ADICIONAR AO OBJECTO DE RETORNO.
		//dtc.setDisciplinas(disciplinas);
		retorno.setDisciplinas(disciplinas);
		
		retorno.setAnoCurricular(matricula.getAnoCurricular());
		retorno.setAnoLectivo(matricula.getAnoLectivo().getAnoLectivo());
		retorno.setNome(matricula.getAluno().getNome());
		retorno.setCurso(matricula.getCurso().getDescricao());
		retorno.setDuracaoCurso(matricula.getAluno().getCurso().getDuracao());
		retorno.setCursoCodigo(matricula.getCurso().getId());
		retorno.setIdMatricula(matricula.getId());
		
		
		//TIPOS DE INSCRIÇÕES...
		List<TipoInscricaoEdicao> tipoInscricoes=new ArrayList<TipoInscricaoEdicao>();
		TipoInscricaoEdicao tipoInscricao;
		List<TipoInscricao> inscricoesTipo = this.tipoDeInscricaoRepository.findAll();
		for (TipoInscricao is : inscricoesTipo) {
			tipoInscricao=new TipoInscricaoEdicao();
			if(is.getId()==matricula.getTipoInscricao().getId()) {
				tipoInscricao.setDescricao(is.getDescricao());
				tipoInscricao.setId(is.getId());
				tipoInscricao.setInscrito(true);
			}else {
				tipoInscricao.setDescricao(is.getDescricao());
				tipoInscricao.setId(is.getId());
				tipoInscricao.setInscrito(false);
			}
			tipoInscricoes.add(tipoInscricao);
		}
		
		
		//EMPRESAS CONVENI...
		List<BolseiroMatriculaEdicao> empBolseiro =new ArrayList<BolseiroMatriculaEdicao>();
		BolseiroMatriculaEdicao empresaBolseiro;
		Iterable<EmpresaConvenio> empresasConvenio = this.empresaConvenio.findAll();
		for (EmpresaConvenio ec : empresasConvenio) {
			empresaBolseiro=new BolseiroMatriculaEdicao();
			
			if(matricula.getEmpresaConvenio()!=null && ec.getId()==matricula.getEmpresaConvenio().getId()) {
				empresaBolseiro.setEmpresaDescricao(ec.getDesignacao());
				empresaBolseiro.setId(ec.getId());
				empresaBolseiro.setInscrito(true);
				empBolseiro.add(empresaBolseiro);				
			}else {
				
				empresaBolseiro.setEmpresaDescricao(ec.getDesignacao());
				empresaBolseiro.setId(ec.getId());
				empresaBolseiro.setInscrito(false);
				empBolseiro.add(empresaBolseiro);
			}
		}
		retorno.setEmpresasConevenio(empBolseiro);
		
		
		//PLANO DE PAGAMENTO
		List<PlanoPagamentoEdicao> planosDePagamento=new ArrayList<PlanoPagamentoEdicao>();
		PlanoPagamentoEdicao planoDePagamento;
		
		Iterable<PlanoPagamento> planos = this.planoDePagamento.findAll();
		for (PlanoPagamento o : planos) {
			planoDePagamento=new PlanoPagamentoEdicao();
			if(matricula.getPlanoPagamento().getId()!=null && o.getId()==matricula.getPlanoPagamento().getId()) {
				planoDePagamento.setDescricao(o.getDescricao());
				planoDePagamento.setId(o.getId());
				planoDePagamento.setInscrito(true);
				planosDePagamento.add(planoDePagamento);
			}else {
				planoDePagamento.setDescricao(o.getDescricao());
				planoDePagamento.setId(o.getId());
				planoDePagamento.setInscrito(false);
				
				planosDePagamento.add(planoDePagamento);
			}
			
		}
		
		
		retorno.setPercentagemDesconto(matricula.getPercentagemDesconto());
		retorno.setPlanosDePagamento(planosDePagamento);
		retorno.setTurmaBaseDescricao(matricula.getTurmaBase().getTurma());
		retorno.setTurmaBaseId(matricula.getTurmaBase().getId());
		retorno.setNumeroDeAluno(Integer.parseInt(matricula.getNumeroDeAluno()));
		retorno.setTipoInscricoes(tipoInscricoes);
		
		if(matricula.getEmpresaConvenio()!=null) {			
			retorno.setBolseiro(true);
		}
		
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(retorno);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	
	
	//ativar turmma metódo...
	private void setarTurma(Aluno aluno, DisciplinaCliente disciplina, String turno) {
		List<TurmaDisponivel> turmaTurno = this.turmaDisponivel.findByCursoAndAnoAndAceitaInscricoes(aluno.getCurso(),
				disciplina.getAnoCurricular(), true);
		if (!turmaTurno.isEmpty()) {
			for (TurmaDisponivel td : turmaTurno) {
				if (td.getTurma().getTurno().getDescricao() == turno) {
					disciplina.setIdTurma(td.getTurma().getId());
					disciplina.setTurma(td.getTurma().getTurma());
				}
			}
		}
	}

	//buscar turma base
	private Turma buscaTurmaBase(Aluno aluno, Integer anoCurricular, String turno) {
		Turma turma = null;
		List<TurmaDisponivel> turmas = this.turmaDisponivel.findByCursoAndAnoAndAceitaInscricoes(aluno.getCurso(),
				anoCurricular, true);
		for (TurmaDisponivel td : turmas) {
			if (td.getTurma().getTurno().getDescricao() == turno) {
				turma = new Turma();
				turma = td.getTurma();
			}
		}
		return turma;
	}
}
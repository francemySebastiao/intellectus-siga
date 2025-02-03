package ao.co.intellectus.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.AlunoResumoSituacao;
import ao.co.intellectus.DTO.AlunoRetorno;
import ao.co.intellectus.DTO.DisciplinaConcluidaCliente;
import ao.co.intellectus.DTO.DisciplinaCursandoCliente;
import ao.co.intellectus.DTO.GuiaLiquidadaCliente;
import ao.co.intellectus.DTO.InscricaoPorAno;
import ao.co.intellectus.DTO.SituacaoAcademicaCliente;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AlunoAnexo;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaEmAbertoCliente;
import ao.co.intellectus.model.HistoricoAluno;
import ao.co.intellectus.model.HistoricoEquivalencia;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.PermissaoCurso;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoAnexoRepository;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.DisciplinaRepository;
import ao.co.intellectus.repository.EquivalenciaHistoricoRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.HistoricoAlunoRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.repository.PermissaoCursoRepository;

@RestController
@RequestMapping("/gestao-aluno")
public class ControllerAlunoGestao {
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private HistoricoAlunoRepository historicoAlunoRepository;
	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private GuiaPagamentoRepository guiaRepository;
	@Autowired
	private AlunoAnexoRepository alunoAnexoRepository;
	@Autowired
	private EquivalenciaHistoricoRepository equivalenciaHistoricoRepository;
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private PermissaoCursoRepository permissaoCursoRepository;

	/*
	 * DESENVOLVEDOR: Ernesto Tadeu T. Sambongo ATUALIZAÇÃO: 19-06-2018
	 * 
	 */
	// /gestao-aluno/situacaoAcademicaAluno/{numero}/{userName}
	@RequestMapping(value = "/situacaoAcademicaAluno/{numero}/{userName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscar(@PathVariable String numero, @PathVariable String userName) {

		// AlunoAnexo
		ResponseCliente c = new ResponseCliente();
		try {
			Aluno aluno = numero != null ? this.alunoRepository.findByNumeroDeAluno(numero) : null;

			
			
			//BUSCA POR UM FACTOR ALÉM DO NÚMERO DE ALUNO
			if(aluno==null) {
				List<Aluno> retornoAluno = this.alunoRepository.findByNumeroDocumento(numero);
				for (Aluno a : retornoAluno) {
					aluno=a;
				}				
			}

			if (aluno == null) {
				c.setMensagem("Nenhum aluno encontrado!");
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

			PermissaoCurso up = this.permissaoCursoRepository.findByCursoAndUsuarioUserName(aluno.getCurso(), userName);

			List<Matricula> matriculas = this.matriculaRepository.findByAlunoNumeroDeAluno(aluno.getNumeroDeAluno());

			if (matriculas.isEmpty()) {
				c.setMensagem("Nenhuma matricula encontrada para este aluno!");
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
			SituacaoAcademicaCliente situacao = new SituacaoAcademicaCliente();

			AlunoAnexo fotoAluno = this.alunoAnexoRepository.findByAlunoNumeroDeAluno(aluno.getNumeroDeAluno());

			// DADOS DO ALUNO
			AlunoResumoSituacao sAluno = new AlunoResumoSituacao();

			sAluno.setNome(aluno.getNome());
			sAluno.setCurso(aluno.getCurso().getPlano());
			sAluno.setCodigoCurso(aluno.getCurso().getId());
			sAluno.setNumero(Integer.parseInt(aluno.getNumeroDeAluno()));
			sAluno.setEmail(aluno.getEmail());
			sAluno.setTelefone(aluno.getTelefone());
			sAluno.setContencioso(aluno.isContencioso());
			sAluno.setAnulado(aluno.isInactivo());
			sAluno.setFimCurso(aluno.isFimCurso());
			sAluno.setFoto(fotoAluno.getFoto());
			sAluno.setGrau(aluno.getCurso().getGrau().getDescricao());

			situacao.setAluno(sAluno);
			situacao.setObservacao(aluno.getObservacao());
			// DADOS DAS MATRICULAS FEITAS.
			InscricaoPorAno inscricao;
			List<InscricaoPorAno> inscricoes = new ArrayList<InscricaoPorAno>();

			// TODAS AS MATRICULAS FEITAS.
			for (Matricula matricula : matriculas) {
				inscricao = new InscricaoPorAno();

				inscricao.setId(matricula.getId());

				inscricao.setAnoCurricular(matricula.getAnoCurricular());
				inscricao.setAnoLectivo(matricula.getAnoLectivo().getAnoLectivo());
				inscricao.setCurso(matricula.getCurso().getPlano());
				inscricao.setDataInscricao(matricula.getData());
				inscricao.setDesconto(matricula.getPercentagemDesconto());

				if (matricula.getTurmaBase() != null)
					inscricao.setTurma(matricula.getTurmaBase().getTurma());

				if (matricula.getUsuarioInscreveu() != null)
					inscricao.setUsuario(matricula.getUsuarioInscreveu().getUserName());

				if (matricula.getPlanoPagamento() != null)
					inscricao.setPlanoPagamento(matricula.getPlanoPagamento().getDescricao());
				if (matricula.getTipoInscricao() != null)
					inscricao.setTipoInscricao(matricula.getTipoInscricao().getDescricao());

				inscricao.setSigla(matricula.getCurso().getSigla());
				inscricao.setAnulado(matricula.isAnulado());
				inscricao.setDataAnulamento(matricula.getDataAnulamento());
				inscricoes.add(inscricao);
			}
			situacao.setInscricaoPorAno(inscricoes);

			// INICIAR OS DADOS DE HISTORICO
			List<DisciplinaCursandoCliente> disciplinasHistorico = new ArrayList<DisciplinaCursandoCliente>();
			DisciplinaCursandoCliente disciplinaHistorico;

			// HISTORICO DE INSCRIÇÕES POR ANO LECTIVO
			List<HistoricoAluno> historicoAluno = this.historicoAlunoRepository
					.findByAlunoNumeroDeAluno(aluno.getNumeroDeAluno());

			for (HistoricoAluno haluno : historicoAluno) {
				disciplinaHistorico = new DisciplinaCursandoCliente();	

				disciplinaHistorico.setIdMatricula(haluno.getMatricula().getId());
				disciplinaHistorico.setDisciplina(haluno.getDisciplina().getDescricao());
				disciplinaHistorico.setAnoLectivo(haluno.getAnoLectivo().getAnoLectivo());
				disciplinaHistorico.setAnoCurricular(haluno.getDisciplina().getAnoCorricular());
				disciplinaHistorico.setTipo(haluno.getDisciplina().getTipo());
				disciplinaHistorico.setTurma(haluno.getTurma().getTurma());
				disciplinaHistorico.setValidado(haluno.isValidada());
				// disciplinaHistorico.setValidado(haluno.is);

				// FREQUENCIAS
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
			situacao.setDisciplinasInscritas(disciplinasHistorico);

			// GUIAS LIQUIDADAS
			GuiaLiquidadaCliente guiaLiquidada = new GuiaLiquidadaCliente();
			List<GuiaLiquidadaCliente> guiaLiquidadas = new ArrayList<GuiaLiquidadaCliente>();

			guiaLiquidadas.add(guiaLiquidada);
			situacao.setGuiasLiquidadas(guiaLiquidadas);
			// GUIAS LIQUIDADAS
			GuiaEmAbertoCliente guiaAberta = new GuiaEmAbertoCliente();
			List<GuiaEmAbertoCliente> guiaAbertas = new ArrayList<GuiaEmAbertoCliente>();
			guiaAbertas.add(guiaAberta);
			situacao.setGuiasAbertas(guiaAbertas);

			// DISCIPLINAS CONCLUIDAS NORMAIS
			DisciplinaConcluidaCliente disciplinasConcluida;
			List<DisciplinaConcluidaCliente> disciplinasConcluidas = new ArrayList<DisciplinaConcluidaCliente>();
			// List<HistoricoAluno> aprovadas =
			// this.historicoAlunoRepository.findByAprovadoAndAlunoNumeroDeAluno(true,
			// aluno.getNumeroDeAluno());

			List<Disciplina> disciplinasCurso = this.disciplinaRepository.findByCursoAndStatus(aluno.getCurso(), true);

			List<HistoricoAluno> aprovadas = this.historicoAlunoRepository
					.findByAlunoAndCursoAndAprovadoAndDisciplinaStatus(aluno, aluno.getCurso(), true, true);

			// AQUI TRATAMOS AS DISCIPLINAS QUE FORAM CONCLUIDAS... TANTO A EQUIVALENCIA
			// QUANTO A FREQUENCIA NORMAL
			Map<Integer, HistoricoAluno> mapaA = new HashMap<Integer, HistoricoAluno>();
			Map<Integer, HistoricoAluno> mapaAB = new HashMap<Integer, HistoricoAluno>();

			for (HistoricoAluno o : aprovadas) {
				if (!mapaA.containsKey(o.getDisciplina().getId())) {
					mapaA.put(o.getDisciplina().getId(), o);
					mapaAB.put(o.getDisciplina().getId(), o);
				}
			}

			// VER DADOS DO HISTORICO
			// HistoricoAluno mapaHa;
			for (HistoricoAluno o : aprovadas) {
				disciplinasConcluida = new DisciplinaConcluidaCliente();

				boolean containsKey = mapaA.containsKey(o.getDisciplina().getId());
				if (containsKey) {
					disciplinasConcluida.setAnoCurricular(o.getDisciplina().getAnoCorricular());
					disciplinasConcluida.setAnoLectivo(o.getAnoLectivo().getAnoLectivo().toString());
					disciplinasConcluida.setDisciplina(o.getDisciplina().getDescricao());

					disciplinasConcluida.setNota(o.getNotaFinal());

					disciplinasConcluida.setSigla(o.getDisciplina().getSigla());
					disciplinasConcluida.setTipo(o.getDisciplina().getTipo());
					disciplinasConcluida.setTotalCurso(disciplinasCurso.size());

					// ATRIBUIR O VALOR AO ARRAY DE DISCIPLINAS
					disciplinasConcluidas.add(disciplinasConcluida);

					mapaA.remove(o.getDisciplina().getId());
				}
			}

			Map<Integer, HistoricoEquivalencia> mapaB = new HashMap<Integer, HistoricoEquivalencia>();

			// VER DADOS DE EQUIVALENCIA
			// BUSCA O HISTORICO DE EQUIVALENCIAS

			List<HistoricoEquivalencia> equivalencia = this.equivalenciaHistoricoRepository
					.findByAlunoNumeroDeAlunoAndEstadoAndDisciplinaDestinoCurso(aluno.getNumeroDeAluno(), true,
							aluno.getCurso());
			for (HistoricoEquivalencia o : equivalencia) {
				if (!mapaA.containsKey(o.getDisciplinaDestino().getId())) {
					mapaB.put(o.getDisciplinaDestino().getId(), o);
				}
			}

			for (HistoricoEquivalencia o : equivalencia) {
				disciplinasConcluida = new DisciplinaConcluidaCliente();
				// VERIFICA SE EXISTE NO MAPA A

				// mapaA.get(o.getDisciplinaDestino().getId());

				boolean containsKey = mapaAB.containsKey(o.getDisciplinaDestino().getId());

				if (!containsKey) {
					boolean containsKeyB = mapaB.containsKey(o.getDisciplinaDestino().getId());

					if (containsKeyB) {
						disciplinasConcluida.setAnoCurricular(o.getDisciplinaDestino().getAnoCorricular());
						disciplinasConcluida
								.setAnoLectivo(o.getEquivalencia().getAnoLectivo().getAnoLectivo().toString());
						disciplinasConcluida.setDisciplina(o.getDisciplinaDestino().getDescricao());
						disciplinasConcluida.setNota(o.getNotaOrigem());
						disciplinasConcluida.setSigla(o.getDisciplinaDestino().getSigla());
						disciplinasConcluida.setTipo(o.getDisciplinaDestino().getTipo());
						disciplinasConcluida.setEquivalencia(true);

						// ATRIBUIR O VALOR AO ARRAY DE DISCIPLINAS
						disciplinasConcluidas.add(disciplinasConcluida);
						mapaB.remove(o.getDisciplinaDestino().getId());
					}
				}
			}

			situacao.setDisciplinasConcluidas(disciplinasConcluidas);

			// DISCIPLINAS CONCLUIDAS
			DisciplinaCursandoCliente disciplinaCursando = new DisciplinaCursandoCliente();
			List<DisciplinaCursandoCliente> disciplinasCursando = new ArrayList<DisciplinaCursandoCliente>();

			disciplinasCursando.add(disciplinaCursando);
			// GUIAS EM ABERTA
			List<Guia> guias = this.guiaRepository
					.findByAlunoNumeroDeAlunoAndLiquidadaAndAnulada(aluno.getNumeroDeAluno(), false, false);
			List<GuiaEmAbertoCliente> guiasNaoLiquidadas = new ArrayList<GuiaEmAbertoCliente>();
			GuiaEmAbertoCliente guiaNaoLiquidada;
			for (Guia guia : guias) {

				guiaNaoLiquidada = new GuiaEmAbertoCliente();

				guiaNaoLiquidada.setId(guia.getId());
				guiaNaoLiquidada.setAnoLectivo(guia.getAnoLectivo().getAnoLectivo());
				guiaNaoLiquidada.setDataEmissao(guia.getDataEmicao());
				guiaNaoLiquidada.setDataVencimento(guia.getDataVencimento());
				// guiaNaoLiquidada.setMoeda(guia.getMoeda().getDesignacao());
				guiaNaoLiquidada.setNumeroGuia(guia.getNumeroGuia());
				guiaNaoLiquidada.setValor(guia.getValor());

				guiasNaoLiquidadas.add(guiaNaoLiquidada);
			}
			situacao.setGuiasAbertas(guiasNaoLiquidadas);

			List<Guia> recidos = this.guiaRepository
					.findByAlunoNumeroDeAlunoAndLiquidadaAndAnulada(aluno.getNumeroDeAluno(), true, false);
			List<GuiaLiquidadaCliente> liquidadas = new ArrayList<GuiaLiquidadaCliente>();
			GuiaLiquidadaCliente liquidada;
			for (Guia guia : recidos) {
				liquidada = new GuiaLiquidadaCliente();

				liquidada.setId(guia.getId());
				liquidada.setAnoLectivo(guia.getAnoLectivo().getAnoLectivo());
				liquidada.setDataEmissao(guia.getDataEmicao());
				liquidada.setDataLiquidada(guia.getDataLiquidacao());
				liquidada.setNumeroGuia(guia.getNumeroGuia());
				liquidada.setValor(guia.getValor());

				liquidadas.add(liquidada);
			}
			situacao.setGuiasLiquidadas(liquidadas);

			List<Disciplina> disciplina = this.disciplinaRepository.findByCursoAndStatus(aluno.getCurso(), true);

			int dCurso = disciplina.size();
			situacao.setDisciplinasCurso(dCurso);
			if (up != null) {
				c.setPermissao(up.getPermissao());
			}
			c.setResultado(situacao);
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} catch (Exception e) {
			c.setCodigo(ResponseCode.values()[1].getDescricao());
			c.setMensagem(e.getLocalizedMessage());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/consultarNumerosSobreBi/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> consultarNumerosSobreBi(@PathVariable String numero) { 	
		ResponseCliente c=new ResponseCliente();
		
		List<AlunoRetorno> alunos=new ArrayList<AlunoRetorno>();
		AlunoRetorno       aluno;
        
		List<Aluno> alunodPego = this.alunoRepository.findByNumeroDocumento(numero);
		
		
		
        for (Aluno a : alunodPego) {
			
			aluno=new AlunoRetorno();
			
			aluno.setNumeroDeAluno(Integer.parseInt(a.getNumeroDeAluno()));

			alunos.add(aluno);
		}
        
        if(alunodPego.isEmpty()) {
        	aluno=new AlunoRetorno();
        	
        	Aluno alunoPego = this.alunoRepository.findByNumeroDeAluno(numero);
        	
        	aluno.setNumeroDeAluno(Integer.parseInt(alunoPego.getNumeroDeAluno()));
        	
        	alunos.add(aluno);
        }
        
       	c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(alunos);
		c.setMensagem("Com qual número deseja entrar ?");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

}

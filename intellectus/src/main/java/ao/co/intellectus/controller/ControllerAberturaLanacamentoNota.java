package ao.co.intellectus.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.AutorizacaoCliente;
import ao.co.intellectus.DTO.AutorizacaoEmCursoCliente;
import ao.co.intellectus.DTO.AutorizacaoLancamentoEspecialCliente;
import ao.co.intellectus.DTO.AutorizacaoRegistroInscricaoCliente;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.AutorizacaoLancamentoEspecial;
import ao.co.intellectus.model.AutorizacaoLancamentoNota;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.Docente;
import ao.co.intellectus.model.Faculdade;
import ao.co.intellectus.model.LancamentoFaculdade;
import ao.co.intellectus.model.Permissao;
import ao.co.intellectus.model.Prova;
import ao.co.intellectus.model.Turma;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.AutorizacaoLancamentoEspecialRepository;
import ao.co.intellectus.repository.AutorizacaoLancamentoNrepository;
import ao.co.intellectus.repository.DisciplinaRepository;
import ao.co.intellectus.repository.DocenteRepository;
import ao.co.intellectus.repository.FaculdadeRepository;
import ao.co.intellectus.repository.LancamentoFaculdadeRepository;
import ao.co.intellectus.repository.ProvaRepository;
import ao.co.intellectus.repository.TurmaRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;

@RestController
@RequestMapping("/autorizacaoLancamentoNota")
public class ControllerAberturaLanacamentoNota {
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private ProvaRepository provaRepository;
	@Autowired
	private AutorizacaoLancamentoNrepository autorizacaoRepository;
	@Autowired
	private AutorizacaoLancamentoEspecialRepository autorizacaoLancamentoEspecialRepository;
	@Autowired
	private TurmaRepository turmaRepository;
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private DocenteRepository docenteRepository;
	@Autowired
	private LancamentoFaculdadeRepository lancamentoFaculdadeRepository;
	@Autowired
	private FaculdadeRepository faculdadeRepository;

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@RequestBody AutorizacaoRegistroInscricaoCliente Pautorizacao) {
		ResponseCliente c = new ResponseCliente();

		AutorizacaoLancamentoNota autorizacao = new AutorizacaoLancamentoNota();

		Prova prova = this.provaRepository.findOne(Pautorizacao.getProva());

		List<AutorizacaoLancamentoNota> autorizacoes = this.autorizacaoRepository.findByEmCursoAndProvaAndTipoDisciplina(true, prova,Pautorizacao.getTipoDisciplina());

		
		
		if (autorizacoes.isEmpty()) {
			AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(Pautorizacao.getAnoLectivo());

			BeanUtils.copyProperties(Pautorizacao, autorizacao, "prova", "anoLectivo");
			autorizacao.setProva(prova);
			autorizacao.setAnoLectivo(anoLectivo);
			autorizacao.setEmCurso(true);
			autorizacao.setDataRegistro(new Date());
			this.autorizacaoRepository.save(autorizacao);
			
			Iterable<Faculdade> faculdades = this.faculdadeRepository.findAll();
			LancamentoFaculdade lFaculdade;
			
			List<LancamentoFaculdade> todosLancamentos = this.lancamentoFaculdadeRepository.findAll();
			
			if(todosLancamentos.isEmpty()) {
				for (Faculdade f : faculdades) {
					lFaculdade=new LancamentoFaculdade();
					lFaculdade.setProva(prova);
					lFaculdade.setFaculdade(f);
					lFaculdade.setPermissao(Permissao.GRAVAR);
					this.lancamentoFaculdadeRepository.save(lFaculdade);
				} 				
			}
			
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setMensagem("Autorizações para inscrição efetivada com sucesso!");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} else {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Já existe uma empoca de lançamento aberta.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/savarEspecial", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> savarEspecial(
			@RequestBody AutorizacaoLancamentoEspecialCliente autorizacao) {
		ResponseCliente c = new ResponseCliente();

		AnoLectivo anoLectivo = this.anoLectivoRepository.findByAnoLectivo(autorizacao.getAnoLectivo());

		if (anoLectivo == null) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Ano lectivo é obrigatório.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		Turma turma = this.turmaRepository.findOne(autorizacao.getTurma());
		if (turma == null) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Turma é obrigatória.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		Docente docente = this.docenteRepository.findOne(autorizacao.getDocente());
		if (docente == null) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Docente é obrigatório.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		Disciplina disciplina = this.disciplinaRepository.findOne(autorizacao.getDisciplina());
		if (disciplina == null) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Disciplina é obrigatória.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		Prova prova = this.provaRepository.findOne(autorizacao.getProva());
		if (prova == null) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Prova é obrigatório.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		List<AutorizacaoLancamentoEspecial> aberto = this.autorizacaoLancamentoEspecialRepository
				.findByDocenteAndDisciplinaAndAnoLectivoAndTurmaAndEmCursoAndProva(docente, disciplina, anoLectivo,
						turma, true, prova);

		if (!aberto.isEmpty()) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Já se encontra uma autorização em curso.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		AutorizacaoLancamentoEspecial aue = new AutorizacaoLancamentoEspecial();

		aue.setAnoLectivo(anoLectivo);
		aue.setTurma(turma);
		aue.setDocente(docente);
		aue.setDisciplina(disciplina);
		aue.setProva(prova);
		aue.setDataInicio(autorizacao.getDataInicio());
		aue.setDataFim(autorizacao.getDataFim());
		aue.setDataRegistro(new Date());
		aue.setEmCurso(true);

		this.autorizacaoLancamentoEspecialRepository.save(aue);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setMensagem("Lançamento efetivado com sucesso.");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/cancelarEspecial", method = RequestMethod.PUT)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> cancelarEspeciall(@RequestBody Integer id) {
		AutorizacaoLancamentoEspecial aue = this.autorizacaoLancamentoEspecialRepository.findOne(id);
		if (aue == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(aue, 2, "Solicitação não encontrada.");
		} else {
			aue.setEmCurso(false);
			this.autorizacaoLancamentoEspecialRepository.save(aue);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(aue, 0, "Autorização cancelada com sucesso.");
		}
	}
	
	// autorizacaoLancamentoNota/autorizacoesEspecialEmCurso

	@RequestMapping(value = "/autorizacoesEspecialEmCurso", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> autorizacoesEspecialEmCurso() {
		ResponseCliente c = new ResponseCliente();

		List<AutorizacaoLancamentoEspecial> emCurso = this.autorizacaoLancamentoEspecialRepository.findByEmCurso(true);
		List<AutorizacaoEmCursoCliente> autorizacoes = new ArrayList<AutorizacaoEmCursoCliente>();
		AutorizacaoEmCursoCliente autorizacao;

		for (AutorizacaoLancamentoEspecial aue : emCurso) {
			autorizacao = new AutorizacaoEmCursoCliente();

			autorizacao.setAnoLectivo(aue.getAnoLectivo().getAnoLectivo());
			autorizacao.setAnoLectivoDescricao(aue.getAnoLectivo().getAnoLectivoDescricao());
			autorizacao.setDataFim(aue.getDataFim());
			autorizacao.setDataInicio(aue.getDataInicio());
			autorizacao.setDisciplina(aue.getDisciplina().getDescricao());
			autorizacao.setDocente(aue.getDocente().getNome());
			autorizacao.setEmCurso(aue.isEmCurso());
			autorizacao.setId(aue.getId());
			autorizacao.setProva(aue.getProva().getDescricao());
			autorizacao.setTurma(aue.getTurma().getTurma());
			autorizacao.setCurso(aue.getDisciplina().getCurso().getPlano());

			autorizacoes.add(autorizacao);
		}

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(autorizacoes);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	/*
	 * RETORNAR OS PERIODOS DE LANÇAMENTO DE NOTAS ABERTOS DESENVOLVEDOR: ERNESTO
	 * SAMBONGO DATA: 10/09/2018
	 */

	// autorizacaoLancamentoNota/autorizacoesEmCurso
	@RequestMapping(value = "/autorizacoesEmCurso", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> autorizacoesEmCurso() {
		ResponseCliente c = new ResponseCliente();

		AutorizacaoCliente autoriza;
		List<AutorizacaoCliente> autorizas = new ArrayList<AutorizacaoCliente>();
		List<AutorizacaoLancamentoNota> todos = this.autorizacaoRepository.findByEmCursoAndProvaEstado(true,true);
		for (AutorizacaoLancamentoNota ar : todos) {
			autoriza = new AutorizacaoCliente();
			autoriza.setAnoLectivo(ar.getAnoLectivo().getAnoLectivo());
			autoriza.setDataFim(ar.getDataFim());
			autoriza.setDataInicio(ar.getDataInicio());
			autoriza.setProva(ar.getProva().getProva());
			autoriza.setTipoDisciplina(ar.getTipoDisciplina());
			autoriza.setEmCurso(ar.isEmCurso());

			autorizas.add(autoriza);
		}
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(autorizas);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	/*
	 * RETORNAR TODAS AS ÉPOCAS ABERTAS. DESENVOLVEDOR: ERNESTO SAMBONGO DATA:
	 * 10/09/2018
	 */
	@RequestMapping(value = "/autorizacoes", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> autorizacoes() {
		ResponseCliente c = new ResponseCliente();

		AutorizacaoCliente autoriza;
		List<AutorizacaoCliente> autorizas = new ArrayList<AutorizacaoCliente>();
		List<AutorizacaoLancamentoNota> todos = this.autorizacaoRepository.findAll();
		for (AutorizacaoLancamentoNota ar : todos) {
			autoriza = new AutorizacaoCliente();
			autoriza.setId(ar.getId());
			autoriza.setAnoLectivo(ar.getAnoLectivo().getAnoLectivo());
			autoriza.setAnoLectivoDescricao(ar.getAnoLectivo().getAnoLectivoDescricao());
			autoriza.setDataFim(ar.getDataFim());
			autoriza.setDataInicio(ar.getDataInicio());
			autoriza.setProva(ar.getProva().getProva());
			autoriza.setTipoDisciplina(ar.getTipoDisciplina());
			autoriza.setEmCurso(ar.isEmCurso());

			autorizas.add(autoriza);
		}
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(autorizas);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/fecharAutorizacao", method = RequestMethod.PUT)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> fecharAutorizacao(@RequestBody Integer id) {
		ResponseCliente c = new ResponseCliente();

		AutorizacaoLancamentoNota autorizacao = new AutorizacaoLancamentoNota();
		autorizacao = this.autorizacaoRepository.findOne(id);

		autorizacao.setEmCurso(false);

		this.autorizacaoRepository.save(autorizacao);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setMensagem("Autorização fechada com sucesso!");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/fecharTodasAutorizacao", method = RequestMethod.PUT)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> fecharTodasAutorizacao() {
		ResponseCliente c = new ResponseCliente();

		List<AutorizacaoLancamentoNota> autorizacao = new ArrayList<AutorizacaoLancamentoNota>();
		autorizacao = this.autorizacaoRepository.findByEmCurso(true);
		
		for (AutorizacaoLancamentoNota autorizacaoLancamentoNota : autorizacao) {
			
			autorizacaoLancamentoNota.setEmCurso(false);
			this.autorizacaoRepository.save(autorizacao);
		}

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setMensagem("Autorizações fechadas com sucesso!");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
}

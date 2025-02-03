package ao.co.intellectus.DTO.proxpay;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import ao.co.intellectus.DTO.GerarUmaReferencia;
import ao.co.intellectus.DTO.ProcessamentoGeral;
import ao.co.intellectus.DTO.ProcessamentoReferenciaCliente;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.CompensassaoProvisoria;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.model.GeracaoErrada;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaPagamentoHistorico;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.Mes;
import ao.co.intellectus.model.ProcessamentoReferencia;
import ao.co.intellectus.model.Turma;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.CompensassaoProvisoriaRepository;
import ao.co.intellectus.repository.CursoRepository;
import ao.co.intellectus.repository.EmolumentoRepository;
import ao.co.intellectus.repository.GeracaoErradaRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.HistoricoGuiaPagamentoRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.repository.MesRepository;
import ao.co.intellectus.repository.ProcessamentoReferenciaRepository;
import ao.co.intellectus.repository.TurmaRepository;
import ao.co.intellectus.servico.cafold.VerifiqueInternet;

@RestController
@RequestMapping("/proxpay")
public class ControllerProxPay {
	private RestTemplate rest;

	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private CursoRepository cursoRepository;
	@Autowired
	private TurmaRepository turmaRepository;
	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private GuiaPagamentoRepository guiaRepository;
	@Autowired
	private EmolumentoRepository emolumentoRepository;
	@Autowired
	private HistoricoGuiaPagamentoRepository guiaHistoricoRepository;
	@Autowired
	private MesRepository mesRepository;
	@Autowired
	private CompensassaoProvisoriaRepository compensassaoRepository;
	@Autowired
	private ProcessamentoReferenciaRepository pagamentoReferenciaRepository;
	@Autowired
	private ProcessamentoReferenciaRepository processamentoReferenciaRepository;
	@Autowired
	private GeracaoErradaRepository geracaoErradaRepository;

	@RequestMapping(value = "/buscarReferencias", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarReferencias() {
		this.rest = new RestTemplate();
		ResponseCliente c = new ResponseCliente();

		String uris = "https://api.proxypay.co.ao/events/payments";
		rest.getInterceptors().add(new BasicAuthorizationInterceptor("api", "un5kodf4euv5s13b6i22qf0bkks1mosn"));

		ResponseEntity<RetornoPagamentos> recebido = rest.getForEntity(uris, RetornoPagamentos.class);

		RetornoPagamentos pagamentos = recebido.getBody();

		c.setResultado(pagamentos);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// proxpay/criarReferencias
	@SuppressWarnings("unused")
	@RequestMapping(value = "/criarReferencias", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> criarReferencias() throws InterruptedException, UnirestException {
		ResponseCliente c = new ResponseCliente();

		boolean connect = VerifiqueInternet.connect("https://www.google.com/");
		Curso curso;
		AnoLectivo anoLectivo;
		Turma turma;
		Mes mes;
		Emolumento emolumento;
		Emolumento emolumentoPorDisciplina;

		String numeroGuia = null;
		double valorGuia = 0;

		List<Matricula> alunos;

		int contador = 0;
		if (connect) {
			curso = this.cursoRepository.findByIdAndStatus(18, true);
			anoLectivo = this.anoLectivoRepository.findOne(18);
			mes = this.mesRepository.findOne(10);

			// 18
			turma = this.turmaRepository.findOne(2);
			// 64--62--120
			// 119 = inscricao por disciplina propina setembro
			// 120 = inscricao por disciplina propina Outubro
			// 65 = propina mês de outubro

			/*
			 * emolumento = this.emolumentoRepository.findOne(65); emolumentoPorDisciplina =
			 * this.emolumentoRepository.findOne(120);
			 */
			emolumento = this.emolumentoRepository.findOne(65);
			emolumentoPorDisciplina = this.emolumentoRepository.findOne(120);

			List<GuiaPagamentoHistorico> emolumentos = null;
			List<GuiaPagamentoHistorico> emolumentosDs = null;

			double valor = 0;

			alunos = this.matriculaRepository.findByCursoAndAnoLectivoAndTurmaBaseAndAnoCurricular(curso, anoLectivo,
					turma, 4);

			for (Matricula matricula : alunos) {
				if (matricula.isAnulado() == false) {
					valor = 0;

					if (matricula.getTipoInscricao().getId() == 1) {
						emolumentos = this.guiaHistoricoRepository.findByEmolumentoAndAlunoAndAnoLectivo(emolumento,
								matricula.getAluno(), anoLectivo);
						for (GuiaPagamentoHistorico emols : emolumentos) {
							numeroGuia = emols.getGuia().getNumeroGuia();
							valorGuia = emols.getGuia().getValor();
							valor += emols.getValor();
						}
					}

					if (matricula.getTipoInscricao().getId() == 2) {
						emolumentos = this.guiaHistoricoRepository.findByEmolumentoAndAlunoAndAnoLectivo(emolumento,
								matricula.getAluno(), anoLectivo);
						for (GuiaPagamentoHistorico emols : emolumentos) {
							numeroGuia = emols.getGuia().getNumeroGuia();
							valorGuia = emols.getGuia().getValor();
							valor += emols.getValor();
						}
					}

					if (matricula.getTipoInscricao().getId() == 3) {

						// PRIMEIRO BUSCA
						emolumentos = this.guiaHistoricoRepository.findByEmolumentoAndAlunoAndAnoLectivo(emolumento,
								matricula.getAluno(), anoLectivo);
						for (GuiaPagamentoHistorico emols : emolumentos) {
							numeroGuia = emols.getGuia().getNumeroGuia();
							valorGuia = emols.getGuia().getValor();
							valor += emols.getValor();
						}

						// SEGUNDA BUSCA
						emolumentosDs = this.guiaHistoricoRepository.findByEmolumentoAndAlunoAndAnoLectivo(
								emolumentoPorDisciplina, matricula.getAluno(), anoLectivo);
						if (emolumentosDs != null) {
							for (GuiaPagamentoHistorico emols : emolumentosDs) {
								valor += emols.getValor();
								valorGuia = emols.getGuia().getValor();
							}
						}
						emolumentosDs = null;
					}

					if (matricula.getTipoInscricao().getId() == 4) {
						emolumentos = this.guiaHistoricoRepository.findByEmolumentoAndAlunoAndAnoLectivo(
								emolumentoPorDisciplina, matricula.getAluno(), anoLectivo);

						for (GuiaPagamentoHistorico emols : emolumentos) {
							numeroGuia = emols.getGuia().getNumeroGuia();
							valorGuia = emols.getGuia().getValor();
							valor += emols.getValor();
						}
					}

					if (!emolumentos.isEmpty() && matricula.getAluno().getNumeroDeAluno().equals("151051")) {
						contador++;
						Unidade unidade = new Unidade();
						// unidade.setExpiry_date(ano+"-"+0+mes+"-"+0+dia);

						// System.err.println("Valor guia gerada: "+emolumentos.getValor());
						unidade.setExpiry_date("2019-12-10");
						unidade.setAmount(valorGuia);

						// ------------------
						Filds custom_fields = new Filds();

						custom_fields.setDescription("Propina do mês de " + mes.getDescricao());

						custom_fields.setMobile(matricula.getAluno().getTelefone());
						custom_fields.setName(matricula.getAluno().getNome());
						custom_fields.setGuia(numeroGuia);

						unidade.setCustom_fields(custom_fields);

						// INVONCANDO API PROXPAY PARA GERAR A REFERENCIA
						// ----------------------------------------------
						Gson gson = new Gson();
						String json = gson.toJson(unidade);

						String payload = "{\"reference\":" + json + "}";

						/*
						 * connect = VerifiqueInternet.connect("https://www.google.com/");
						 * HttpResponse<JsonNode> jsonResponse =
						 * Unirest.post("https://api.proxypay.co.ao/references") .basicAuth("api",
						 * "un5kodf4euv5s13b6i22qf0bkks1mosn") .header("accept",
						 * "application/vnd.proxypay.v1+json") .header("Content-Type",
						 * "application/json") .body(payload) .asJson();
						 */
						// -----------------------------------

						// Integer statusCode = jsonResponse.getStatus();
						Integer statusCode = 200;
						if (statusCode == 200) {

							CompensassaoProvisoria compensassao = new CompensassaoProvisoria();
							Guia guia = this.guiaRepository.findByNumeroGuia(numeroGuia);
							compensassao.setNumeroGuia(guia.getNumeroGuia());
							compensassao.setDataCriacao(new Date());
							compensassao.setDataVencimento(guia.getDataVencimento());
							compensassao.setDescricao("Propina do mês de " + mes.getDescricao());
							compensassao.setNome(matricula.getAluno().getNome());
							compensassao.setNumero(Integer.parseInt(matricula.getAluno().getNumeroDeAluno()));
							compensassao.setTelefone(matricula.getAluno().getTelefone());
							// compensassao.setValor(guia.getValor());
							compensassao.setValor(valorGuia);
							// valorGuia
							this.compensassaoRepository.save(compensassao);

							/*
							 * PagamentoReferencia pagamento=new PagamentoReferencia(); Guia guia =
							 * this.guiaRepository.findByNumeroGuia(numeroGuia); pagamento.setGuia(guia);
							 * pagamento.setNumeroAluno(Integer.parseInt(matricula.getNumeroDeAluno()));
							 * pagamento.setDataCriacao(new Date()); pagamento.setValor(valor);
							 * pagamento.setMes(mes); pagamento.setAnoLectivo(anoLectivo);
							 * pagamento.setDataVencimento(guia.getDataVencimento());
							 * pagamento.setDescricao("Propina do mês de "+mes.getDescricao());
							 * this.pagamentoReferenciaRepository.save(pagamento);
							 */
							// System.out.println("StatusCode 200 " + jsonResponse.getBody().toString());
						} else {
							// System.err.println("StatusCode " + statusCode.toString() + " " +
							// jsonResponse.getBody().toString());
						}

					}
				}

			}

		

			c.setMensagem("Total de guais geradas: " + contador);
			c.setCodigo(200);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);

		} else {
			
			c.setMensagem("Verifique a conexão de internet");
			c.setCodigo(200);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/processarReferencias", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> processarReferencias(@RequestBody ProcessamentoGeral processamento)
			throws InterruptedException, UnirestException {
		ResponseCliente c = new ResponseCliente();

		boolean connect = VerifiqueInternet.connect("https://www.google.com/");

		AnoLectivo anoLectivo;
		Emolumento emolumento;
		Emolumento emolumentoPorDisciplina;
		String numeroGuia = null;
		double valorGuia = 0;
		List<Matricula> alunos;
		int contador = 0;
		if (connect) {
			// OK, FICA PARA SEGUIR

			

			anoLectivo = this.anoLectivoRepository.findOne(processamento.getAnoLectivo());

			emolumento = this.emolumentoRepository.findOne(processamento.getEmolumentoNormal());
			emolumentoPorDisciplina = this.emolumentoRepository
					.findOne(processamento.getEmolumentoInscricaoDisciplina());

			List<GuiaPagamentoHistorico> emolumentos = null;
			List<GuiaPagamentoHistorico> emolumentosDs = null;

			@SuppressWarnings("unused")
			double valor = 0;

			alunos = this.matriculaRepository.findByAnoLectivoAndAnuladoAndProcessamentoReferencia(anoLectivo, false,
					false);
			// System.err.println("Total de registro processados: " + alunos.size());

			for (Matricula matricula : alunos) {
				if (matricula.isAnulado() == false && matricula.getCurso().getGrau().getDescricao() != "Mestrado"
						&& matricula.getAluno().isContencioso() == false) {
					valor = 0;

					if (matricula.getTipoInscricao().getId() == 1) {
						emolumentos = this.guiaHistoricoRepository.findByEmolumentoAndAlunoAndAnoLectivo(emolumento,
								matricula.getAluno(), anoLectivo);
						for (GuiaPagamentoHistorico emols : emolumentos) {
							numeroGuia = emols.getGuia().getNumeroGuia();
							valorGuia = emols.getGuia().getValor();
							valor += emols.getValor();
						}
					}

					if (matricula.getTipoInscricao().getId() == 2) {
						emolumentos = this.guiaHistoricoRepository.findByEmolumentoAndAlunoAndAnoLectivo(emolumento,
								matricula.getAluno(), anoLectivo);
						for (GuiaPagamentoHistorico emols : emolumentos) {
							numeroGuia = emols.getGuia().getNumeroGuia();
							valorGuia = emols.getGuia().getValor();
							valor += emols.getValor();
						}
					}

					if (matricula.getTipoInscricao().getId() == 3) {

						// PRIMEIRO BUSCA
						emolumentos = this.guiaHistoricoRepository.findByEmolumentoAndAlunoAndAnoLectivo(emolumento,
								matricula.getAluno(), anoLectivo);
						for (GuiaPagamentoHistorico emols : emolumentos) {
							numeroGuia = emols.getGuia().getNumeroGuia();
							valorGuia = emols.getGuia().getValor();
							valor += emols.getValor();
						}

						// SEGUNDA BUSCA
						emolumentosDs = this.guiaHistoricoRepository.findByEmolumentoAndAlunoAndAnoLectivo(
								emolumentoPorDisciplina, matricula.getAluno(), anoLectivo);
						if (emolumentosDs != null) {
							for (GuiaPagamentoHistorico emols : emolumentosDs) {
								valor += emols.getValor();
								valorGuia = emols.getGuia().getValor();
							}
						}
						emolumentosDs = null;
					}

					if (matricula.getTipoInscricao().getId() == 4) {
						emolumentos = this.guiaHistoricoRepository.findByEmolumentoAndAlunoAndAnoLectivo(
								emolumentoPorDisciplina, matricula.getAluno(), anoLectivo);

						for (GuiaPagamentoHistorico emols : emolumentos) {
							numeroGuia = emols.getGuia().getNumeroGuia();
							valorGuia = emols.getGuia().getValor();
							valor += emols.getValor();
						}
					}

					// &&
					// !emolumentos.get(0).getGuia().getAluno().getNumeroDeAluno().equals("170159")

					List<CompensassaoProvisoria> revalida = this.compensassaoRepository.findByNumeroGuia(numeroGuia);
					if (!emolumentos.isEmpty() && revalida.isEmpty()) {
						contador++;
						Unidade unidade = new Unidade();

						unidade.setExpiry_date("2019-12-10");
						unidade.setAmount(valorGuia);

						// ------------------
						Filds custom_fields = new Filds();
						int tel1;
						String fone;

						custom_fields.setDescription("Propina do mês de " + processamento.getMes());
						custom_fields.setMobile(matricula.getAluno().getTelefone());
						custom_fields.setName(matricula.getAluno().getNome());
						custom_fields.setGuia(numeroGuia);

						if (matricula.getAluno().getEmail() != null)
							custom_fields.setEmail(matricula.getAluno().getEmail());

						if (matricula.getAluno().getTelefone() != null) {
							tel1 = matricula.getAluno().getTelefone().length();
							if (tel1 >= 9) {
								fone = matricula.getAluno().getTelefone().replaceAll(" ", "");
								custom_fields.setMobile(fone);
							} else if (matricula.getAluno().getTelefone1() != null) {
								fone = matricula.getAluno().getTelefone1().replaceAll(" ", "");
								custom_fields.setMobile(fone);
							} else {
								fone = matricula.getAluno().getTelefone2();
								custom_fields.setMobile(matricula.getAluno().getTelefone2());
							}
						} else {
							if (matricula.getAluno().getTelefone1() != null) {
								fone = matricula.getAluno().getTelefone1().replaceAll(" ", "");
								custom_fields.setMobile(fone);
							} else {
								fone = matricula.getAluno().getTelefone2();
								custom_fields.setMobile(matricula.getAluno().getTelefone2());
							}
						}

						unidade.setCustom_fields(custom_fields);

						// INVONCANDO API PROXPAY PARA GERAR A REFERENCIA
						// ----------------------------------------------
						Gson gson = new Gson();
						String json = gson.toJson(unidade);

						String payload = "{\"reference\":" + json + "}";

						// HttpResponse<JsonNode> jsonResponse=null;
						List<CompensassaoProvisoria> guiaRef = null;
						if (!emolumentos.get(0).getGuia().isLiquidada() && !emolumentos.get(0).getGuia().isAnulada()
								&& fone != null && emolumentos.get(0).getGuia().getAluno().isContencioso() == false) {
							guiaRef = this.compensassaoRepository
									.findByNumeroGuia(emolumentos.get(0).getGuia().getNumeroGuia());
							if (guiaRef.isEmpty()) {
								Unirest.post("https://api.proxypay.co.ao/references")
										.basicAuth("api", "un5kodf4euv5s13b6i22qf0bkks1mosn")
										.header("accept", "application/vnd.proxypay.v1+json")
										.header("Content-Type", "application/json").body(payload).asJson();
							}

						}

						// Integer statusCode = jsonResponse.getStatus();

						Integer statusCode = 200;
						if (statusCode == 200) {
							if (!emolumentos.get(0).getGuia().isLiquidada() && !emolumentos.get(0).getGuia().isAnulada()
									&& fone != null) {
								if (guiaRef.isEmpty()) {
									CompensassaoProvisoria compensassao = new CompensassaoProvisoria();
									Guia guia = this.guiaRepository.findByNumeroGuia(numeroGuia);
									compensassao.setNumeroGuia(guia.getNumeroGuia());
									compensassao.setDataCriacao(new Date());
									compensassao.setDataVencimento(guia.getDataVencimento());
									compensassao.setDescricao("Propina do mês de " + processamento.getMes());
									compensassao.setNome(matricula.getAluno().getNome());
									compensassao.setNumero(Integer.parseInt(matricula.getAluno().getNumeroDeAluno()));
									compensassao.setTelefone(fone);
									compensassao.setValor(valorGuia);
									// providi
									this.compensassaoRepository.save(compensassao);

									// ERNESTO SAMBONGO
									matricula.setProcessamentoReferencia(true);

									this.matriculaRepository.save(matricula);
								}

							}

							// System.out.println("StatusCode 200 " + jsonResponse.getBody().toString());
						} else {
							// System.err.println("StatusCode " + statusCode.toString() + " " +
							// jsonResponse.getBody().toString());
						}

					}
				}

			}

			ProcessamentoReferencia rProcesso = new ProcessamentoReferencia();

			rProcesso.setAnoLectivo(anoLectivo);
			rProcesso.setDataProcessamento(new Date());
			rProcesso.setDataVencimento(new Date());
			rProcesso.setMes(processamento.getMes());
			rProcesso.setProcessado(true);
			rProcesso.setTotalGuias(contador);

			this.processamentoReferenciaRepository.save(rProcesso);

		

			c.setMensagem("Total de guais geradas: " + contador);
			c.setCodigo(200);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} else {
			
			c.setMensagem("Verifique a conexão de internet");
			c.setCodigo(200);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	
	@RequestMapping(value = "/ProcessamentoPorAno/{codigo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listaOrdenadaAnoLectivo(@PathVariable Integer codigo) {
		ResponseCliente c = new ResponseCliente();

		AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(codigo);
		List<ProcessamentoReferencia> processados = this.pagamentoReferenciaRepository.findByAnoLectivo(anoLectivo);
		List<ProcessamentoReferenciaCliente> psCliente = new ArrayList<ProcessamentoReferenciaCliente>();

		ProcessamentoReferenciaCliente pCliente;

		for (ProcessamentoReferencia pcs : processados) {
			pCliente = new ProcessamentoReferenciaCliente();

			BeanUtils.copyProperties(pcs, pCliente, "anoLectivo");
			pCliente.setAnoLectivo(pcs.getAnoLectivo().getAnoLectivo());

			psCliente.add(pCliente);
		}
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(psCliente);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/referencia-guia", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> refererenciaGuia(@RequestBody GerarUmaReferencia processamento)
			throws InterruptedException, UnirestException {
		ResponseCliente c = new ResponseCliente();

		boolean connect = VerifiqueInternet.connect("https://www.google.com/");

		if (connect) {
			Guia guia = this.guiaRepository.findOne(processamento.getNumero());

			// Date data = guia.getDataVencimento();
			// Locale local = new Locale("pt", "BR");
			// DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

			// String vencimento = formato.format(data);

			List<GuiaPagamentoHistorico> historico = this.guiaHistoricoRepository.findByGuia(guia);

			Unidade unidade = new Unidade();

			// System.err.println("Data vencimento formatada: "+vencimento);

			unidade.setExpiry_date("2019-12-10");

			unidade.setAmount(guia.getValor());

			// ------------------
			Filds custom_fields = new Filds();

			custom_fields.setMobile(processamento.getTelefone());
			custom_fields.setDescription("Guias para recurso...");
			custom_fields.setDescription(historico.get(0).getObs());
			// custom_fields.setMobile("932632897");
			custom_fields.setName(guia.getAluno().getNome());
			custom_fields.setGuia(guia.getNumeroGuia());

			unidade.setCustom_fields(custom_fields);

			// INVONCANDO API PROXPAY PARA GERAR A REFERENCIA
			// ----------------------------------------------
			Gson gson = new Gson();
			String json = gson.toJson(unidade);

			String payload = "{\"reference\":" + json + "}";

			// HttpResponse<JsonNode> jsonResponse=null;

			if (!guia.isLiquidada() && !guia.isAnulada()) {
				Unirest.post("https://api.proxypay.co.ao/references")
						.basicAuth("api", "un5kodf4euv5s13b6i22qf0bkks1mosn")
						.header("accept", "application/vnd.proxypay.v1+json").header("Content-Type", "application/json")
						.body(payload).asJson();
				c.setMensagem("Guia foi gerada com sucesso.");
			} else {
				c.setMensagem("Erro na geração da referencia.");
			}

			c.setCodigo(200);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} else {
			
			c.setMensagem("Verifique a conexão de internet");
			c.setCodigo(200);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/deletetar-referencia", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> deletarReferencia() throws InterruptedException, UnirestException {
		ResponseCliente c = new ResponseCliente();

		boolean connect = VerifiqueInternet.connect("https://www.google.com/");
		List<Referencias> totalReferencias=new ArrayList<>();
		// http://127.0.0.1:8810/intellectus/proxpay/deletetar-referencia
		if (connect) {
			this.rest = new RestTemplate();

			// String uris = "https://api.proxypay.co.ao/references";
			// 22697

			
			List<GeracaoErrada> guiaGeradas = this.geracaoErradaRepository.findByLiquidada(0);
			Map<String, Integer> mapa=new HashedMap<String,Integer>();

			
			for (GeracaoErrada o : guiaGeradas) {
				mapa.put(o.getNumeroGuia(), o.getNumeroAluno());
			}
			
			
			
			
			//2468
		
			String uris="";
			
			rest.getInterceptors()
			.add(new BasicAuthorizationInterceptor("api", "un5kodf4euv5s13b6i22qf0bkks1mosn"));
			
			
			//2990
			//2990
			for(int i=0;i<2;i++){
		    
				uris = "https://api.proxypay.co.ao/references?offset="+i;

				//"https://api.proxypay.co.ao/references?offset="+i
				// https://api.proxypay.co.ao/references?offset=0
				
				//https://api.proxypay.co.ao/references
				// String uris = "https://api.proxypay.co.ao/references?offset=20";

				

				ResponseEntity<Retorno> recebido = rest.getForEntity(uris, Retorno.class);

				Retorno pagamentos = recebido.getBody();

				
				
				
				List<Referencias> references = pagamentos.getReferences();
				

				
				for (Referencias o : references) {
					//if (o.getCustom_fields().getGuia().equals("2019235901")) {
                      
						//System.err.println("PEGUEI O VALOR E ESTOU PRONTO A DELETAR: " + o.getAmount());
						//String delete = "https://api.proxypay.co.ao/references/" + o.getId();
						//rest.delete(delete);
						
                      Integer guiaPega = mapa.get(o.getCustom_fields().getGuia());
                      
                      if(guiaPega!=null ) {
                    	  
                    	  
                    	  
                      }
                      
                      
                      //Mon Jul 29 07:53:13 WAT 2019
                      
                      o.getCreated_at();
                      
                      
                      
						totalReferencias.add(o);
					//}
				}
			}

			
			c.setMensagem("TOTAL DE REGISTRO ENCONTRADO LIQUIDADOS: "+guiaGeradas.size());
			c.setCodigo(200);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} else {
		
			c.setMensagem("Verifique a conexão de internet");
			c.setCodigo(200);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}
}

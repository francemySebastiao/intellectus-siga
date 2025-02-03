package ao.co.intellectus.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.icu.text.DateFormat;

import ao.co.intellectus.DTO.AlunoCreditoCliente;
import ao.co.intellectus.DTO.AlunoDadosSimplesCliente;
import ao.co.intellectus.DTO.AlunoEmolumento;
import ao.co.intellectus.DTO.AlunoGuia;
import ao.co.intellectus.DTO.AlunoResumo;
import ao.co.intellectus.DTO.AnosInscricoes;
import ao.co.intellectus.DTO.EmolumentoCliente;
import ao.co.intellectus.DTO.EmolumentoDescCliente;
import ao.co.intellectus.DTO.EmolumentoGuiaCliente;
import ao.co.intellectus.DTO.EntidadePadrao;
import ao.co.intellectus.DTO.GuiaAcordoPagamentoCliente;
import ao.co.intellectus.DTO.GuiaCliente;
import ao.co.intellectus.DTO.GuiaEmolumentoCliente;
import ao.co.intellectus.DTO.GuiaLiquidacao;
import ao.co.intellectus.DTO.InscricoesAluno;
import ao.co.intellectus.DTO.ProcessamentoGuiaCliente;
import ao.co.intellectus.DTO.VerificarPropina;
import ao.co.intellectus.DTO.proxpay.LiquidacaoProxyPayDTO;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AlunoAnexo;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Banco;
import ao.co.intellectus.model.Bordero;
import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.CompensassaoProvisoria;
import ao.co.intellectus.model.ContaCorrenteAluno;
import ao.co.intellectus.model.ContaCorrenteCandidato;
import ao.co.intellectus.model.Contador;
import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.model.EmolumentoHistorico;
import ao.co.intellectus.model.Grau;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaAudit;
import ao.co.intellectus.model.GuiaCandidatura;
import ao.co.intellectus.model.GuiaPagamentoHistorico;
import ao.co.intellectus.model.HistoricoAluno;
import ao.co.intellectus.model.HistoricoCredito;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.Moeda;
import ao.co.intellectus.model.Multa;
import ao.co.intellectus.model.NotaCredito;
import ao.co.intellectus.model.NumeroGerado;
import ao.co.intellectus.model.ProcessamentoMensalControle;
import ao.co.intellectus.model.RegistroDocumentos;
import ao.co.intellectus.model.StatusCredito;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.enumeracao.TipoDoc;
import ao.co.intellectus.model.enumeracao.TipoFatura;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoAnexoRepository;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.BancoRepository;
import ao.co.intellectus.repository.BorderoRepository;
import ao.co.intellectus.repository.CandidatoRepository;
import ao.co.intellectus.repository.CompensassaoEfetivaRepository;
import ao.co.intellectus.repository.CompensassaoProvisoriaRepository;
import ao.co.intellectus.repository.ContaCorrenteCandidatoRepository;
import ao.co.intellectus.repository.ContaCorrenteRepository;
import ao.co.intellectus.repository.ContadorRepository;
import ao.co.intellectus.repository.EmolumentoHistoricoRepository;
import ao.co.intellectus.repository.EmolumentoRepository;
import ao.co.intellectus.repository.GuiaAuditRepository;
import ao.co.intellectus.repository.GuiaCandidaturaRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.HistoricoAlunoRepository;
import ao.co.intellectus.repository.HistoricoCreditoRepository;
import ao.co.intellectus.repository.HistoricoGuiaPagamentoRepository;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.repository.MoedaRepository;
import ao.co.intellectus.repository.MultaRepository;
import ao.co.intellectus.repository.NotaCreditoRepository;
import ao.co.intellectus.repository.NumeroGeradoRepository;
import ao.co.intellectus.repository.ProcessamentoMensalControleRepository;
import ao.co.intellectus.repository.RegistroDocumentoRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.servico.GeradorDeArquivo;
import ao.co.intellectus.servico.GerarNumeroDocumento;
import ao.co.intellectus.servico.cafold.AlunoService;
import ao.co.intellectus.servico.cafold.Conexao;
import ao.co.intellectus.servico.cafold.DataService;
import ao.co.intellectus.servico.cafold.GuiaService;
import ao.co.intellectus.util.FormataData;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/guia")
public class ControllerGuia {
	//
	@Autowired
	private GuiaPagamentoRepository repository;
	@Autowired
	private MatriculaRepository matriculaRepositoy;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private GuiaCandidaturaRepository guiaCandidaturaRepository;
	@Autowired
	private CandidatoRepository candidatoRepository;
	@Autowired
	private EmolumentoRepository emolumentoRepository;
	@Autowired
	private ContaCorrenteRepository contaConrrenteRepository;
	@Autowired
	private EmolumentoHistoricoRepository emolumentoHistoricoRepository;
	@Autowired
	private HistoricoGuiaPagamentoRepository historicoGuiaRepository;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private BorderoRepository borderoRepository;
	@Autowired
	private BancoRepository bancoRepository;
	@Autowired
	private MoedaRepository moedaRepository;
	@Autowired
	private MultaRepository multaRepository;
	@Autowired
	private AlunoAnexoRepository alunoAnexo;
	@Autowired
	private NumeroGeradoRepository numeroGeradoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private HistoricoCreditoRepository historicoCreditoRepository;
	@Autowired
	private ContaCorrenteCandidatoRepository correnteCandidatoRepository;
	@Autowired
	private HistoricoAlunoRepository historicoAlunoRepository;
	@Autowired
	private ProcessamentoMensalControleRepository processamentoMensalControleRepository;
	@Autowired
	private ContadorRepository contadorRepository;
	@Autowired
	private GuiaAuditRepository guiaAuditRepository;
	@Autowired
	private CompensassaoProvisoriaRepository compensassaoProvisoriaRepository;
	@Autowired
	private CompensassaoEfetivaRepository compensassaoEfetivaRepository;
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private GuiaService guiaService;
	@Autowired
	private DataService dataService;
	@Autowired
	private RegistroDocumentoRepository registroDocumentoRepo;
	@Autowired
	private NotaCreditoRepository notaCreditoRepo;
	@Autowired
	private GerarNumeroDocumento gerarNumeroDocService;
	@Autowired
	private GeradorDeArquivo gerarDocService;

	private String numeroGuia;
	//private String numeroFacturaProforma;

	public static Connection conectar() {
		Conexao con = new Conexao();
		return con.getConn();
	}

	@RequestMapping(value = "/todas", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public Iterable<Guia> guias() {
		return repository.findAll();
	}

	// http://srvapli:8810/intellectus/guia/liquidacao-proxypay
	@RequestMapping(value = "/liquidacao-proxypay", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> liquidarProxyPay(@RequestBody LiquidacaoProxyPayDTO liquidacaoProxypay) {
		ResponseCliente c = new ResponseCliente();

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setMensagem("Guia Liquidada com sucesso!");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/processamento", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	@Transactional
	public ResponseEntity<ResponseCliente> processamentoMensal(@RequestBody ProcessamentoGuiaCliente processamento) {
		ResponseCliente c = new ResponseCliente();

		boolean podeExecutar = false;
		if (!podeExecutar) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Foi desativada essa funcionalidade.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		if (processamento.getAnoLectivo() == null || processamento.getDataVencimento() == null
				|| processamento.getMes() == null) {

			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setMensagem("Informações Inválidas, verifique os dados!");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(processamento.getAnoLectivo());

		// EMOLUMENTO NORMAL
		Emolumento eNormal = this.emolumentoRepository.findOne(processamento.getEmolumentoNormal());

		// EMOLUMENTO POR DISCIPLINA
		Emolumento eDisciplina = this.emolumentoRepository.findOne(processamento.getEmolumentoInscricaoDisciplina());

		// EMOLUMENTO ANUIDADE
		Emolumento eAnual = this.emolumentoRepository.findOne(80);

		Instituicao instituicao = this.instituicaoRepository.findOne(2);

		Moeda moeda = this.moedaRepository.findOne(3);

		ProcessamentoMensalControle controle;
		if (this.processamentoMensalControleRepository.findByAnoLectivoAndMes(anoLectivo.getAnoLectivo(),
				processamento.getMes()) == null) {
			controle = new ProcessamentoMensalControle();
			controle.setAnoLectivo(anoLectivo.getAnoLectivo());
			controle.setMes(processamento.getMes());
			controle.setTotalAnoCompleto(0);
			controle.setTotalAnoCompletoComFreq(0);
			controle.setTotalAnoCompletoSemFrq(0);
			controle.setTotalPorDisciplina(0);

			this.processamentoMensalControleRepository.save(controle);
		} else {
			controle = this.processamentoMensalControleRepository.findByAnoLectivoAndMes(anoLectivo.getAnoLectivo(),
					processamento.getMes());
		}

		if (processamento.getTipoInscricao() == 1) {
			List<Matricula> m1 = this.matriculaRepositoy.buscarAlunoAnoCompletoManha(anoLectivo.getId(),
					processamento.getTipoInscricao(), processamento.getTurno(), processamento.getGrau().getDescricao());
			ProcessamentoCompleto(processamento, anoLectivo, eNormal, instituicao, moeda, m1, eAnual, controle);
		} else if (processamento.getTipoInscricao() == 2) {
			List<Matricula> m2 = this.matriculaRepositoy.buscarAlunoAnoCompletoManha(anoLectivo.getId(),
					processamento.getTipoInscricao(), processamento.getTurno(), processamento.getGrau().getDescricao());
			ProcessamentoCompletoSemFrequencia(processamento, anoLectivo, eNormal, instituicao, moeda, m2, eAnual,
					controle);
		} else if (processamento.getTipoInscricao() == 3) {
			List<Matricula> m3 = this.matriculaRepositoy.buscarAlunoAnoCompletoManha(anoLectivo.getId(),
					processamento.getTipoInscricao(), processamento.getTurno(), processamento.getGrau().getDescricao());
			processamentoExameComFrequencia(processamento, anoLectivo, eNormal, eDisciplina, eAnual, instituicao, moeda,
					m3, controle);
		} else if (processamento.getTipoInscricao() == 4) {
			List<Matricula> m4 = this.matriculaRepositoy.buscarAlunoAnoCompletoManha(anoLectivo.getId(),
					processamento.getTipoInscricao(), processamento.getTurno(), processamento.getGrau().getDescricao());
			processamentoPorDisciplina(processamento, anoLectivo, eDisciplina, eAnual, instituicao, moeda, m4,
					controle);
		}

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setMensagem("Guias emitidas com sucesso!");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@Transactional
	private void ProcessamentoCompleto(ProcessamentoGuiaCliente processamento, AnoLectivo anoLectivo,
			Emolumento eNormal, Instituicao instituicao, Moeda moeda, List<Matricula> m1, Emolumento eAnual,
			ProcessamentoMensalControle controle) {

		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);

		Usuario usuario = this.usuarioRepository.findOne(48);

		double desconto = 0;
		double valorComIva = 0;
		double valorImposto = 0;
		double novoValorguia = 0;
		float valorPercentagem = 0;
		double valorTotalIvaDesconto = 0;
		double valorCalculadoPercentagem = 0;

		// Guia guia;
		int eT1 = 0;
		for (Matricula matricula : m1) {

			if (matricula.getPlanoPagamento().getId() == 1) {
				List<GuiaPagamentoHistorico> gExiste = this.historicoGuiaRepository
						.findByEmolumentoAndAlunoAndAnoLectivoAndGuiaAnulada(eNormal, matricula.getAluno(), anoLectivo,
								false);

				List<GuiaPagamentoHistorico> aExiste = this.historicoGuiaRepository
						.findByEmolumentoAndAlunoAndAnoLectivoAndGuiaAnulada(eAnual, matricula.getAluno(), anoLectivo,
								false);

				if (gExiste.isEmpty() && aExiste.isEmpty()) {

					List<Matricula> alunoManha = matriculaRepositoy.buscarAlunoAnoCompletoManha(
							processamento.getAnoLectivo(), processamento.getTipoInscricao(), processamento.getTurno(),
							processamento.getGrau().getDescricao());
					System.out.println("Estamos aqui " + alunoManha.size());
					for (Matricula matriculado : alunoManha) {

						EmolumentoHistorico emolumentoHist = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(
								eNormal, matriculado.getCurso(), anoLectivo);

						if (emolumentoHist != null) {

							Guia guia = new Guia();

							guia.setAluno(matriculado.getAluno());
							guia.setNumeroDeAluno(matriculado.getAluno().getNumeroDeAluno());

							// guia.setValor(FormataData.formatarValor((double)emolumentoHist.getValor()));
							guia.setDataVencimento(processamento.getDataVencimento());
							guia.setDataEmicao(new Date());
							guia.setAutomaticamente(false);
							guia.setAnoLectivo(anoLectivo);
							guia.setAlterada(false);
							guia.setAutomaticamente(true);
							guia.setInstituicao(instituicao);
							guia.setMoeda(moeda);
							guia.setUsuarioEmitiu(usuario);
							guia.setUltimaModificacao(new Date());
							guia.setLiquidacaoAGT(false);
							guia.setDataSistema(dataSistema);
							guia.setLiquidacaoCredito(false);
							guia.setAcordo(false);
							guia.setAnulada(false);
							guia.setAlterada(false);
							guia.setLiquidada(false);

							Guia saveGuia = this.repository.save(guia);

							// PREPARA O HISTÓRICO DA GUIA.
							GuiaPagamentoHistorico gHistorico = new GuiaPagamentoHistorico();

							gHistorico.setAluno(matriculado.getAluno());
							gHistorico.setAnoLectivo(matriculado.getAnoLectivo());
							if (emolumentoHist != null)
								gHistorico.setEmolumento(emolumentoHist.getEmolumento());
							gHistorico.setGuia(saveGuia);
							gHistorico.setObs(emolumentoHist.getEmolumento().getEmolumento());
							gHistorico.setValor(FormataData.formatarValor((double) emolumentoHist.getValor()));

							gHistorico.setNumeroDeAluno(matriculado.getNumeroDeAluno());
							gHistorico.setCodigoIva(emolumentoHist.getEmolumento().getCodigoIva());
							gHistorico.setPercentagemIva(emolumentoHist.getEmolumento().getPercentagemIva().toString());
							gHistorico.setQuantidade("1");

							valorImposto = (emolumentoHist.getValor()
									* emolumentoHist.getEmolumento().getPercentagemIva()) / 100;
							valorComIva = emolumentoHist.getValor() + valorImposto;

							valorTotalIvaDesconto = (valorComIva - (emolumentoHist.getValor()
									* emolumentoHist.getEmolumento().getPercentagemDesconto()) / 100);
							desconto = (emolumentoHist.getValor()
									* emolumentoHist.getEmolumento().getPercentagemDesconto()) / 100;

							if (matriculado.getPercentagemDesconto() > 0) {
								valorPercentagem = (float) matriculado.getPercentagemDesconto() / 100;
								valorCalculadoPercentagem = (emolumentoHist.getValor() * valorPercentagem) + desconto;
								gHistorico.setDesconto(valorCalculadoPercentagem);
							}

							gHistorico.setDesconto(desconto);
							gHistorico.setValorImposto(FormataData.formatarValor(valorImposto));
							gHistorico.setValorTotal(FormataData.formatarValor(valorTotalIvaDesconto));

							novoValorguia = valorTotalIvaDesconto;
							saveGuia.setValor(FormataData.formatarValor(novoValorguia));

							Guia guardar = repository.save(saveGuia);

							this.historicoGuiaRepository.save(gHistorico);
							this.guiaService.gerarHistoricoAudit(gHistorico);

							verificarGerarNumeroGuia(anoLectivo, guardar);
						}
					}
					eT1++;
				}
			}
		}
		controle.setAnoCompleto("Ano Completo");
		controle.setProcAnoCompleto(true);

		Integer totalACompleto = controle.getTotalAnoCompleto() + eT1;
		controle.setTotalAnoCompleto(totalACompleto);
		this.processamentoMensalControleRepository.save(controle);
	}

	// PROCESSAMENTO ANO COMPLETO EXAME SEM FREQUENCIA
	@Transactional
	private void ProcessamentoCompletoSemFrequencia(ProcessamentoGuiaCliente processamento, AnoLectivo anoLectivo,
			Emolumento eNormal, Instituicao instituicao, Moeda moeda, List<Matricula> m2, Emolumento eAnual,
			ProcessamentoMensalControle controle) {

		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);

		Usuario usuario = this.usuarioRepository.findOne(48);

		double desconto = 0;
		double valorComIva = 0;
		double valorImposto = 0;
		double novoValorguia = 0;
		float valorPercentagem = 0;
		double valorTotalIvaDesconto = 0;
		double valorCalculadoPercentagem = 0;

		int eT1 = 0;
		for (Matricula matricula : m2) {

			if (matricula.getPlanoPagamento().getId() == 1) {
				List<GuiaPagamentoHistorico> gExiste = this.historicoGuiaRepository
						.findByEmolumentoAndAlunoAndAnoLectivoAndGuiaAnulada(eNormal, matricula.getAluno(), anoLectivo,
								false);

				List<GuiaPagamentoHistorico> aExiste = this.historicoGuiaRepository
						.findByEmolumentoAndAlunoAndAnoLectivoAndGuiaAnulada(eAnual, matricula.getAluno(), anoLectivo,
								false);

				if (gExiste.isEmpty() && aExiste.isEmpty()) {

					List<Matricula> alunoManha = matriculaRepositoy.buscarAlunoAnoCompletoManha(
							processamento.getAnoLectivo(), processamento.getTipoInscricao(), processamento.getTurno(),
							processamento.getGrau().getDescricao());

					for (Matricula matriculado : alunoManha) {

						EmolumentoHistorico emolumentoHist = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(
								eNormal, matriculado.getCurso(), anoLectivo);

						if (emolumentoHist != null) {

							Guia guia = new Guia();

							guia.setAluno(matriculado.getAluno());
							guia.setNumeroDeAluno(matriculado.getAluno().getNumeroDeAluno());
							guia.setDataVencimento(processamento.getDataVencimento());
							guia.setDataEmicao(new Date());
							guia.setAutomaticamente(false);
							guia.setAnoLectivo(anoLectivo);
							guia.setAlterada(false);
							guia.setAutomaticamente(true);
							guia.setInstituicao(instituicao);
							guia.setMoeda(moeda);
							guia.setUsuarioEmitiu(usuario);
							guia.setUltimaModificacao(new Date());
							guia.setLiquidacaoAGT(false);
							guia.setDataSistema(dataSistema);
							guia.setLiquidacaoCredito(false);
							guia.setAcordo(false);
							guia.setAnulada(false);
							guia.setAlterada(false);
							guia.setLiquidada(false);

							Guia saveGuia = this.repository.save(guia);

							// PREPARA O HISTÓRICO DA GUIA.
							GuiaPagamentoHistorico gHistorico = new GuiaPagamentoHistorico();

							gHistorico.setAluno(matriculado.getAluno());
							gHistorico.setAnoLectivo(matriculado.getAnoLectivo());
							if (emolumentoHist != null)
								gHistorico.setEmolumento(emolumentoHist.getEmolumento());
							gHistorico.setGuia(saveGuia);
							gHistorico.setObs(emolumentoHist.getEmolumento().getEmolumento());
							gHistorico.setValor(FormataData.formatarValor((double) emolumentoHist.getValor()));

							gHistorico.setNumeroDeAluno(matriculado.getNumeroDeAluno());
							gHistorico.setCodigoIva(emolumentoHist.getEmolumento().getCodigoIva());
							gHistorico.setPercentagemIva(emolumentoHist.getEmolumento().getPercentagemIva().toString());
							gHistorico.setQuantidade("1");

							valorImposto = (emolumentoHist.getValor()
									* emolumentoHist.getEmolumento().getPercentagemIva()) / 100;
							valorComIva = emolumentoHist.getValor() + valorImposto;

							valorTotalIvaDesconto = (valorComIva - (emolumentoHist.getValor()
									* emolumentoHist.getEmolumento().getPercentagemDesconto()) / 100);
							desconto = (emolumentoHist.getValor()
									* emolumentoHist.getEmolumento().getPercentagemDesconto()) / 100;

							if (matriculado.getPercentagemDesconto() > 0) {
								valorPercentagem = (float) matriculado.getPercentagemDesconto() / 100;
								valorCalculadoPercentagem = (emolumentoHist.getValor() * valorPercentagem) + desconto;
								gHistorico.setDesconto(valorCalculadoPercentagem);
							}

							gHistorico.setDesconto(desconto);
							gHistorico.setValorImposto(FormataData.formatarValor(valorImposto));
							gHistorico.setValorTotal(FormataData.formatarValor(valorTotalIvaDesconto));

							novoValorguia = valorTotalIvaDesconto;
							saveGuia.setValor(FormataData.formatarValor(novoValorguia));

							Guia guardar = repository.save(saveGuia);

							this.historicoGuiaRepository.save(gHistorico);
							this.guiaService.gerarHistoricoAudit(gHistorico);

							verificarGerarNumeroGuia(anoLectivo, guardar);
						}
					}
					eT1++;
				}
			}
		}
		controle.setAnoCompletoSemF("Ano Completo Exame sem Frequência");
		controle.setProcAnoCompletoSemF(true);

		Integer totalACompleto = controle.getTotalAnoCompletoSemFrq() + eT1;
		controle.setTotalAnoCompletoSemFrq(totalACompleto);
		this.processamentoMensalControleRepository.save(controle);
	}

	// ANO COMPLETO EXAME COM FREQUENCIA
	@Transactional
	private void processamentoExameComFrequencia(ProcessamentoGuiaCliente processamento, AnoLectivo anoLectivo,
			Emolumento eNormal, Emolumento eDisciplina, Emolumento eAnual, Instituicao instituicao, Moeda moeda,
			List<Matricula> m3, ProcessamentoMensalControle controle) {

		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);

		Usuario usuario = this.usuarioRepository.findOne(48);

		double desconto = 0;
		double valorComIva = 0;
		double valorImposto = 0;
		double novoValorguia = 0;
		float valorPercentagem = 0;
		double valorTotalIvaDesconto = 0;
		double valorCalculadoPercentagem = 0;

		int eT1 = 0;
		for (Matricula matricula : m3) {
			if (matricula.getPlanoPagamento().getId() == 1) {

				List<GuiaPagamentoHistorico> gExiste = this.historicoGuiaRepository
						.findByEmolumentoAndAlunoAndAnoLectivoAndGuiaAnulada(eNormal, matricula.getAluno(), anoLectivo,
								false);

				List<GuiaPagamentoHistorico> aExiste = this.historicoGuiaRepository
						.findByEmolumentoAndAlunoAndAnoLectivoAndGuiaAnulada(eAnual, matricula.getAluno(), anoLectivo,
								false);

				if (gExiste.isEmpty() && aExiste.isEmpty()) {

					EmolumentoHistorico emolumentoHist = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(
							eNormal, matricula.getCurso(), anoLectivo);

					EmolumentoHistorico eHistoricoDisciplina = this.emolumentoHistoricoRepository
							.findByEmolumentoAndCursoAndAnoLectivo(
									eDisciplina, matricula.getCurso(), anoLectivo);

					if (emolumentoHist != null) {

						Guia guia = new Guia();

						guia.setAluno(matricula.getAluno());
						guia.setNumeroDeAluno(matricula.getAluno().getNumeroDeAluno());

						guia.setValor(FormataData.formatarValor((double) emolumentoHist.getValor()));
						guia.setDataVencimento(processamento.getDataVencimento());
						guia.setDataEmicao(new Date());
						guia.setAutomaticamente(false);
						guia.setAnoLectivo(anoLectivo);
						guia.setAlterada(false);
						guia.setAutomaticamente(true);
						guia.setInstituicao(instituicao);
						guia.setMoeda(moeda);
						guia.setUsuarioEmitiu(usuario);
						guia.setUltimaModificacao(new Date());
						guia.setLiquidacaoAGT(false);
						guia.setDataSistema(dataSistema);
						guia.setLiquidacaoCredito(false);
						guia.setAcordo(false);
						guia.setAnulada(false);
						guia.setAlterada(false);
						guia.setLiquidada(false);

						Guia saveGuia = this.repository.save(guia);

						// PREPARA O HISTÓRICO DA GUIA.
						GuiaPagamentoHistorico gHistorico = new GuiaPagamentoHistorico();

						gHistorico.setAluno(matricula.getAluno());
						gHistorico.setAnoLectivo(matricula.getAnoLectivo());
						if (emolumentoHist != null)
							gHistorico.setEmolumento(emolumentoHist.getEmolumento());
						gHistorico.setGuia(saveGuia);
						gHistorico.setObs(emolumentoHist.getEmolumento().getEmolumento());
						gHistorico.setValor(FormataData.formatarValor((double) emolumentoHist.getValor()));

						gHistorico.setNumeroDeAluno(matricula.getNumeroDeAluno());
						gHistorico.setCodigoIva(emolumentoHist.getEmolumento().getCodigoIva());
						gHistorico.setDesconto(0);
						gHistorico.setPercentagemIva(emolumentoHist.getEmolumento().getPercentagemIva().toString());
						gHistorico.setQuantidade("1");

						valorImposto = (emolumentoHist.getValor() * emolumentoHist.getEmolumento().getPercentagemIva())
								/ 100;
						valorComIva = emolumentoHist.getValor() + valorImposto;

						valorTotalIvaDesconto = (valorComIva
								- (emolumentoHist.getValor() * emolumentoHist.getEmolumento().getPercentagemDesconto())
										/ 100);
						desconto = (emolumentoHist.getValor() * emolumentoHist.getEmolumento().getPercentagemDesconto())
								/ 100;

						if (matricula.getPercentagemDesconto() > 0) {
							valorPercentagem = (float) matricula.getPercentagemDesconto() / 100;
							valorCalculadoPercentagem = (emolumentoHist.getValor() * valorPercentagem) + desconto;
							gHistorico.setDesconto(valorCalculadoPercentagem);
						}

						gHistorico.setDesconto(desconto);
						gHistorico.setValorImposto(FormataData.formatarValor(valorImposto));
						gHistorico.setValorTotal(FormataData.formatarValor(valorTotalIvaDesconto));

						this.historicoGuiaRepository.save(gHistorico);
						this.guiaService.gerarHistoricoAudit(gHistorico);

						// CALCULAR AS DISCIPLINAS DIFERENTES DO ANO CORRICULAR
						List<HistoricoAluno> historico = this.historicoAlunoRepository
								.historicoAlunoSemestre(matricula.getId(), processamento.getSemestre());
						if (historico.isEmpty()) {
							continue;
						}

						novoValorguia = saveGuia.getValor();

						for (HistoricoAluno o : historico) {

							gHistorico = new GuiaPagamentoHistorico();
							gHistorico.setAluno(matricula.getAluno());
							gHistorico.setAnoLectivo(matricula.getAnoLectivo());
							gHistorico.setEmolumento(eHistoricoDisciplina.getEmolumento());
							gHistorico.setGuia(saveGuia);
							gHistorico.setObs(o.getDisciplina().getDescricao());
							gHistorico.setValor(FormataData.formatarValor((double) eHistoricoDisciplina.getValor()));
							gHistorico.setNumeroDeAluno(matricula.getNumeroDeAluno());

							gHistorico.setCodigoIva(eHistoricoDisciplina.getEmolumento().getCodigoIva());
							gHistorico.setDesconto(0);
							gHistorico.setPercentagemIva(
									eHistoricoDisciplina.getEmolumento().getPercentagemIva().toString());
							gHistorico.setQuantidade("1");

							valorImposto = (eHistoricoDisciplina.getValor()
									* eHistoricoDisciplina.getEmolumento().getPercentagemIva()) / 100;
							valorComIva = eHistoricoDisciplina.getValor() + valorImposto;

							valorTotalIvaDesconto = (valorComIva - (emolumentoHist.getValor()
									* emolumentoHist.getEmolumento().getPercentagemDesconto()) / 100);
							desconto = (emolumentoHist.getValor()
									* emolumentoHist.getEmolumento().getPercentagemDesconto()) / 100;

							gHistorico.setDesconto(FormataData.formatarValor(desconto));
							gHistorico.setValorImposto(FormataData.formatarValor(valorImposto));
							gHistorico.setValorTotal(FormataData.formatarValor(valorTotalIvaDesconto));

							this.historicoGuiaRepository.save(gHistorico);
							this.guiaService.gerarHistoricoAudit(gHistorico);
							novoValorguia += eHistoricoDisciplina.getValor();
						}

						saveGuia.setValor(FormataData.formatarValor(novoValorguia));
						saveGuia.setUltimaModificacao(new Date());
						Guia guardade = this.repository.save(saveGuia);

						verificarGerarNumeroGuia(anoLectivo, guardade);
					}

					eT1++;
				}
			}
		}
		controle.setAnoCompletoComF("Ano Completo Exame Com Frequência");
		controle.setProcAnoCompletoComF(true);
		Integer totalACompleto = controle.getTotalAnoCompletoComFreq() + eT1;
		controle.setTotalAnoCompletoComFreq(totalACompleto);
		this.processamentoMensalControleRepository.save(controle);
	}

	// PROCESSAMENTO POR DISCIPLINA
	@Transactional
	private void processamentoPorDisciplina(ProcessamentoGuiaCliente processamento, AnoLectivo anoLectivo,
			Emolumento eDisciplina, Emolumento eAnual, Instituicao instituicao, Moeda moeda, List<Matricula> m4,
			ProcessamentoMensalControle controle) {

		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);

		Usuario usuario = this.usuarioRepository.findOne(48);

		double desconto = 0;
		//int totalCadeiras = 0;
		double valorComIva = 0;
		double valorImposto = 0;
		//double valorCadeiras = 0;
		double valorTotalIvaDesconto = 0;
		
		System.out.println("TOTAL DE MATRICULA " + m4.size());

		int eT1 = 0;
		for (Matricula matricula : m4) {

			// BUSCA A GUIA EMOLUMENTO A SER PROCESSADO CASO EXISTA
			List<GuiaPagamentoHistorico> gExiste = this.historicoGuiaRepository
					.findByEmolumentoAndAlunoAndAnoLectivoAndGuiaAnulada(eDisciplina, matricula.getAluno(), anoLectivo,
							false);

			List<GuiaPagamentoHistorico> aExiste = this.historicoGuiaRepository
					.findByEmolumentoAndAlunoAndAnoLectivoAndGuiaAnulada(eAnual, matricula.getAluno(), anoLectivo,
							false);

			if (gExiste.isEmpty() && aExiste.isEmpty()) {
				System.out.println("ENTREI AQUI AGORA");
				double novoValorguia = 0;

				List<HistoricoAluno> historico = this.historicoAlunoRepository.historicoAlunoSemestre(matricula.getId(),
						processamento.getSemestre());
				
				System.out.println("DADOS MATRICULA " + matricula.getId());
				System.out.println("DADOS SEMESTRE " + processamento.getSemestre());
				
				System.out.println("HISTORICO " + historico.size());
				
				if (historico.isEmpty()) {
					System.out.println("ESTOU LIMPO");
					continue;
				}

				/*
				 * for (HistoricoAluno o : historico) { if (o.getDisciplina().getTipo() !=
				 * TipoDisciplina.SEGUNDO_SEMESTRE) { totalCadeiras++; } }
				 */

				EmolumentoHistorico historicoEmolumento = this.emolumentoHistoricoRepository
						.findByEmolumentoAndCursoAndAnoLectivo(
								eDisciplina, matricula.getCurso(), anoLectivo);

				if (historicoEmolumento != null) {

					System.out.println("PASSEI DE FASE");
					
					Guia guia = new Guia();
					guia.setAluno(matricula.getAluno());
					guia.setNumeroDeAluno(matricula.getAluno().getNumeroDeAluno());
					guia.setDataVencimento(processamento.getDataVencimento());
					guia.setDataEmicao(new Date());
					guia.setAutomaticamente(false);
					guia.setAnoLectivo(anoLectivo);
					guia.setAutomaticamente(true);
					guia.setAlterada(false);
					guia.setInstituicao(instituicao);
					guia.setMoeda(moeda);
					guia.setUsuarioEmitiu(usuario);
					guia.setUltimaModificacao(new Date());
					guia.setLiquidacaoAGT(false);
					guia.setDataSistema(dataSistema);
					guia.setLiquidacaoCredito(false);
					guia.setAcordo(false);
					guia.setAnulada(false);
					guia.setAlterada(false);
					guia.setLiquidada(false);
					Guia saveGuia = this.repository.save(guia);

					GuiaPagamentoHistorico gHistorico = new GuiaPagamentoHistorico();

					// MOMENTO DE SALVAR O HISTÓRICO DA GUIA DE PAGAMENTO.
					for (HistoricoAluno o : historico) {

						gHistorico = new GuiaPagamentoHistorico();
						gHistorico.setAluno(matricula.getAluno());
						gHistorico.setAnoLectivo(matricula.getAnoLectivo());
						gHistorico.setEmolumento(historicoEmolumento.getEmolumento());
						gHistorico.setGuia(saveGuia);
						gHistorico.setObs(o.getDisciplina().getDescricao());
						gHistorico.setValor(historicoEmolumento.getValor());
						gHistorico.setNumeroDeAluno(matricula.getNumeroDeAluno());

						gHistorico.setCodigoIva(historicoEmolumento.getEmolumento().getCodigoIva());

						gHistorico
								.setPercentagemIva(historicoEmolumento.getEmolumento().getPercentagemIva().toString());
						gHistorico.setQuantidade("1");

						valorImposto = (historicoEmolumento.getValor()
								* historicoEmolumento.getEmolumento().getPercentagemIva()) / 100;
						valorComIva = historicoEmolumento.getValor() + valorImposto;

						valorTotalIvaDesconto = (valorComIva - (historicoEmolumento.getValor()
								* historicoEmolumento.getEmolumento().getPercentagemDesconto()) / 100);
						desconto = (historicoEmolumento.getValor()
								* historicoEmolumento.getEmolumento().getPercentagemDesconto()) / 100;

						gHistorico.setDesconto(desconto);
						gHistorico.setValorImposto(FormataData.formatarValor(valorImposto));
						gHistorico.setValorTotal(FormataData.formatarValor(valorTotalIvaDesconto));

						novoValorguia += valorTotalIvaDesconto;

						this.historicoGuiaRepository.save(gHistorico);
						this.guiaService.gerarHistoricoAudit(gHistorico);
					}

					saveGuia.setValor(FormataData.formatarValor(novoValorguia));
					Guia guardar = repository.save(saveGuia);

					verificarGerarNumeroGuia(anoLectivo, guardar);
				}
				eT1++;
			}
		}
		controle.setPorDisciplina("Por Disciplina");
		controle.setProcPorDisciplina(true);

		Integer totalACompleto = controle.getTotalPorDisciplina() + eT1;
		controle.setTotalPorDisciplina(totalACompleto);
		this.processamentoMensalControleRepository.save(controle);
	}

	@Transactional
	private void verificarGerarNumeroGuia(AnoLectivo anoLectivo, Guia saveGuia) {
		// AUTOMAÇÃO PARA A GERAÇÃO DO NUMERO DA GUIA
		String definitivo = "";

		// anoInscricao
		Integer lectivo = anoLectivo.getAnoLectivo();
		NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(3);
		Long proximoNumeroInteiro = numeroGerado.getProximoNumero();

		// metódo gerar numero de guia chamado
		definitivo = gerarNumeroGuia(definitivo, lectivo, proximoNumeroInteiro);

		Guia guiaExistente = this.repository.findByNumeroGuia(definitivo);
		if (guiaExistente != null) {
			do {
				proximoNumeroInteiro++;
				// metódo gerar numero de guia chamado
				definitivo = gerarNumeroGuia(definitivo, lectivo, proximoNumeroInteiro);
				guiaExistente = this.repository.findByNumeroGuia(definitivo);
			} while (guiaExistente != null);
		}

		String numero = "";

		AnoLectivo anoActivo = anoLectivoRepository.buscarPorEstado();
		String ano = String.valueOf(anoActivo.getAnoLectivo());
		String anoSubstring = ano.substring(2, 4);
		Integer anoLimpo = Integer.parseInt(anoSubstring);

		NumeroGerado numeroGeradoFP = this.numeroGeradoRepository.findOne(6);
		Long proximoNumeroFP = numeroGeradoFP.getProximoNumero();
		System.out.println("Pagar proximo número " + proximoNumeroFP);
		// String numero = gerarNumeroDocService.geracaoNumero();
		numero = gerarNumeroDocService.gerarNumeroFacturaProforma(numero, anoLimpo, proximoNumeroFP);

		Guia proformaExisteFP = this.repository.findProforma(numero);
		GuiaCandidatura proformaCandExist = guiaCandidaturaRepository.buscarProforma(numero);
		if (proformaExisteFP != null || proformaCandExist != null) {
			do {
				proximoNumeroFP++;

				numero = gerarNumeroDocService.gerarNumeroFacturaProforma(numero, anoLimpo, proximoNumeroFP);
				proformaExisteFP = this.repository.findProforma(numero);
				proformaCandExist = guiaCandidaturaRepository.buscarProforma(numero);
			} while (proformaExisteFP != null || proformaCandExist != null);
		}

		// setar o valor da guia
		saveGuia.setNumeroGuia(definitivo);
		saveGuia.setNumeroFacturaProforma(numero);
		saveGuia.setUltimaModificacao(new Date());

		Guia guiaGuardada = this.repository.save(saveGuia);

		this.gerarDocService.gerarFileProformaAluno(guiaGuardada);

		numeroGerado.setUltimoNumero(proximoNumeroInteiro);
		numeroGerado.setProximoNumero(proximoNumeroInteiro + 1);
		this.numeroGeradoRepository.save(numeroGerado);

		numeroGeradoFP.setUltimoNumero(proximoNumeroFP);
		numeroGeradoFP.setProximoNumero(proximoNumeroFP + 1);
		this.numeroGeradoRepository.save(numeroGeradoFP);
		guiaGuardada.setValor(saveGuia.getValor());
		guiaGuardada.setTipoFactura(TipoFatura.FACTURA_PROFORMA);

		// SALVA NOVAMENTE A GUIA COM SEU NÚMERO
		this.repository.save(guiaGuardada);
	}

	@RequestMapping(value = "/todas/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> todas(@PathVariable String numero) {
		ResponseCliente c = new ResponseCliente();

		Aluno alunoEncontrado = this.alunoRepository.findByNumeroDeAluno(numero);
		Moeda moeda = this.moedaRepository.findByStatus(true);

		if (alunoEncontrado == null) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Aluno não existe.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		ContaCorrenteAluno contaAluno = this.contaConrrenteRepository.findByAluno(alunoEncontrado);
		List<Guia> guiasEncontradas = this.repository.findByAlunoOrderByAnoLectivoDesc(alunoEncontrado);

		List<GuiaCliente> gClinetes = new ArrayList<GuiaCliente>();

		GuiaCliente gCliente;
		GuiaAlunos guiaAlunos = new GuiaAlunos();

		int a = guiasEncontradas.size();
		int b = guiasEncontradas.size();

		boolean pegar;
		for (Guia guia : guiasEncontradas) {
			gCliente = new GuiaCliente();
			gCliente.setLiquidada(guia.isLiquidada());
			BeanUtils.copyProperties(guia, gCliente, "Aluno", "AnoLectivo");

			gCliente.setAnulada(guia.isAnulada());
			gCliente.setAcordo(guia.isAcordo());
			gCliente.setLiquidada(guia.isLiquidada());
			gCliente.setAlterada(guia.isAlterada());
			gCliente.setAnoLectivo(guia.getAnoLectivo().getAnoLectivo());
			gCliente.setAnoLectivoDescricao(guia.getAnoLectivo().getAnoLectivoDescricao());
			gCliente.setNumero(guia.getNumeroGuia());
			gCliente.setNumeroProforma(guia.getNumeroFacturaProforma());
			gCliente.setNumeroFacturaRecibo(guia.getNumeroFacturaRecibo());
			gCliente.setMotivoAnulacaoGuia(guia.getMotivoAnulacaoGuia());
			gCliente.setMotivoAnulacaoRecibo(guia.getMotivoAnulacaoRecibo());

			List<RegistroDocumentos> registroDocumento = this.registroDocumentoRepo.findByGuiaPagamento(guia);
			if (alunoEncontrado.getCurso().getGrau() == Grau.MESTRADO) {
				for (RegistroDocumentos rg : registroDocumento) {
					rg.getTipoDeclaracao();
					System.out.println(rg.getTipoDeclaracao().getDescricao());

					if (!registroDocumento.isEmpty()) {
						gCliente.setRequerimento(true);
					}
				}

			}

			// DADOS DE EMISSÃO E REGISTRO DE PAGAMENTO
			if (guia.getUsuarioEmitiu() != null) {
				Usuario usuarioEmitiu = this.usuarioRepository.findOne(guia.getUsuarioEmitiu().getId());
				gCliente.setEmitidoPor(usuarioEmitiu.getName());
			}
			if (guia.isLiquidada()) {
				List<Bordero> bordero = this.borderoRepository.findByGuiaAndMoeda(guia, moeda);

				gCliente.setDataRegistro(guia.getDataLiquidacao());

				if (guia.getUsuarioLiquidou() != null) {
					Usuario usuario = this.usuarioRepository.findOne(guia.getUsuarioLiquidou().getId());
					gCliente.setRegistradoPor(usuario.getName());
				}

				if (!bordero.isEmpty()) {
					gCliente.setDataRegistro(bordero.get(0).getDataRegistro());
					gCliente.setNumeroBordero(bordero.get(0).getNumero());
					gCliente.setDataDeposito(bordero.get(0).getDataDeposito());
					if (bordero.get(0).getBanco() != null)
						gCliente.setBanco(bordero.get(0).getBanco().getBanco());

				}
			}
			// gCliente.s
			Multa gg = this.multaRepository.findByGuia(guia);
			if (gg != null) {
				gCliente.setMulta(gg.getValorMulta());
			}
			pegar = a == b ? true : false;
			a--;
			gCliente.setLiquidar(pegar);

			gClinetes.add(gCliente);
		}

		AlunoAnexo alunoFoto = this.alunoAnexo.findByAluno(alunoEncontrado);

		guiaAlunos.setNome(alunoEncontrado.getNome());
		guiaAlunos.setNumeroAluno(alunoEncontrado.getId());
		// TODOAS AS GUIAS JÁ EMITIDAS.
		guiaAlunos.setGuiasAlunos(gClinetes);
		guiaAlunos.setCredito(contaAluno.getValor());
		guiaAlunos.setContencioso(alunoEncontrado.isContencioso());
		guiaAlunos.setFimCurso(alunoEncontrado.isFimCurso());
		guiaAlunos.setInativo(alunoEncontrado.isInactivo());
		guiaAlunos.setFoto(alunoFoto.getFoto());
		guiaAlunos.setCurso(alunoEncontrado.getCurso().getPlano());
		guiaAlunos.setGrau(alunoEncontrado.getCurso().getGrau().getDescricao());

		// contaCorrente

		AnosInscricoes anoInscricao;
		List<AnosInscricoes> anosInscricoes = new ArrayList<AnosInscricoes>();
		List<Matricula> matriculas = this.matriculaRepositoy.findByAlunoOrderByAnoLectivoDesc(alunoEncontrado);
		for (Matricula matricula : matriculas) {
			anoInscricao = new AnosInscricoes();
			anoInscricao.setAnoLectivo(matricula.getAnoLectivo().getAnoLectivo());
			anoInscricao.setAnoLectivoDescricao(matricula.getAnoLectivo().getAnoLectivoDescricao());

			anoInscricao.setBolseiro(matricula.getEmpresaConvenio() != null ? true : false);
			if (matricula.getEmpresaConvenio() != null)
				anoInscricao.setEmpresaConvenio(matricula.getEmpresaConvenio().getDesignacao());
			anosInscricoes.add(anoInscricao);

		}
		// Collections.sort(null);
		guiaAlunos.setAnosInscricoes(anosInscricoes);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(guiaAlunos);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// PARA TRABALHAR COM O ANDERSON.
	@RequestMapping(value = "/alunoDadosSimples", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> alunoDadosSimples(@RequestBody AlunoResumo aluno) {
		ResponseCliente c = new ResponseCliente();
		Aluno alunoEncontrado = this.alunoRepository.findByNumeroDeAluno(aluno.getBuscarId().toString());
		ContaCorrenteAluno contaAluno = this.contaConrrenteRepository.findByAluno(alunoEncontrado);

		List<AnoLectivo> anoLectivo = this.anoLectivoRepository.findByStatus(true);

		Matricula mm = this.matriculaRepositoy.findByAlunoAndAnoLectivoAndAnulado(alunoEncontrado, anoLectivo.get(0),false);

		/*if (mm == null) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Aluno sem matricula neste ano lectivo.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}*/

		if (alunoEncontrado == null) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Número do aluno não existe.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		// List<Guia> guiasEncontradas =
		// this.repository.findByAlunoAndLiquidadaAndAnulada(alunoEncontrado,
		// false,false);
		AlunoDadosSimplesCliente guiaAlunos = new AlunoDadosSimplesCliente();

		AlunoAnexo alunoFoto = this.alunoAnexo.findByAluno(alunoEncontrado);
		if (alunoFoto == null) {
			AlunoAnexo alunoAnexo = new AlunoAnexo();

			alunoAnexo.setAluno(alunoEncontrado);
			alunoAnexo.setNumeroDeAluno(alunoEncontrado.getNumeroDeAluno());
			alunoAnexo.setFoto(null);
			// salvar os dados da foto
			alunoFoto = this.alunoAnexo.save(alunoAnexo);
		}

		guiaAlunos.setNome(alunoEncontrado.getNome());
		guiaAlunos.setNumeroAluno(Integer.parseInt(alunoEncontrado.getNumeroDeAluno()));
		guiaAlunos.setCurso(alunoEncontrado.getCurso().getPlano());
		guiaAlunos.setCredito(contaAluno.getValor());
		guiaAlunos.setContencioso(alunoEncontrado.isContencioso());
		guiaAlunos.setFimCurso(alunoEncontrado.isFimCurso());
		guiaAlunos.setInativo(alunoEncontrado.isInactivo());
		guiaAlunos.setFoto(alunoFoto.getFoto());
		guiaAlunos.setGrau(alunoEncontrado.getCurso().getGrau().getDescricao());
		

		if (mm != null) {
			if (mm.getEmpresaConvenio() != null) {
				guiaAlunos.setBolseiro(true);
				guiaAlunos.setEmpresaConvenio(mm.getEmpresaConvenio().getDesignacao());
			}
		}

		// private List<InscricoesAluno> inscricoes;
		List<Matricula> matriculas = this.matriculaRepositoy.findByAluno(alunoEncontrado);
		InscricoesAluno alunoInscricao;
		List<InscricoesAluno> alunoInscricoes = new ArrayList<InscricoesAluno>();
		for (Matricula matricula : matriculas) {
			alunoInscricao = new InscricoesAluno();
			alunoInscricao.setAnoLectivo(matricula.getAnoLectivo().getAnoLectivo());
			alunoInscricao.setCodigoAnoLectivo(matricula.getAnoLectivo().getId());
			alunoInscricoes.add(alunoInscricao);

			if (matricula.getAnoLectivo().getAnoLectivo() == LocalDate.now().getYear()) {
				guiaAlunos.setPercentagemDesconto(matricula.getPercentagemDesconto());
			}
		}
		guiaAlunos.setInscricoes(alunoInscricoes);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(guiaAlunos);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarGuiaPorAluno", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> guiaPorAluno(@RequestBody AlunoResumo aluno) {
		ResponseCliente c = new ResponseCliente();
		if (aluno.isCandidatura()) {
			List<Candidato> candidato = this.candidatoRepository.findByNumeroCandidato(aluno.getBuscarId());

			if (candidato.isEmpty()) {
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				c.setMensagem("Não existe condidato com número especificado.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
			GuiaCandidatura guiaCandidatura = this.guiaCandidaturaRepository.findByCandidato(candidato.get(0));

			if (guiaCandidatura == null) {
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				c.setMensagem("Não existe guia por liquidar.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
			GuiaCliente gCliente = new GuiaCliente();
			gCliente.setId(Integer.parseInt(guiaCandidatura.getNumeroGuia()));
			gCliente.setNumero(guiaCandidatura.getNumeroGuia());
			gCliente.setAnoLectivo(guiaCandidatura.getAnoLectivo().getAnoLectivo());
			gCliente.setAnoLectivoDescricao(guiaCandidatura.getAnoLectivo().getAnoLectivoDescricao());
			gCliente.setDataEmicao(guiaCandidatura.getDataEmicao());
			gCliente.setDataVencimento(guiaCandidatura.getDataVencimento());
			gCliente.setValor(guiaCandidatura.getValor());

			List<GuiaCliente> gClinetes = new ArrayList<GuiaCliente>();
			gClinetes.add(gCliente);
			GuiaAlunos guiaAlunos = new GuiaAlunos();
			guiaAlunos.setGuiasAlunos(gClinetes);

			guiaAlunos.setNumeroAluno(candidato.get(0).getId());
			guiaAlunos.setNome(candidato.get(0).getNome());
			c.setResultado(guiaAlunos);
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);

		} else {
			Aluno alunoEncontrado = this.alunoRepository.findByNumeroDeAluno(aluno.getBuscarId().toString());
			ContaCorrenteAluno contaAluno = this.contaConrrenteRepository.findByAluno(alunoEncontrado);

			if (contaAluno == null) {
				ContaCorrenteAluno conta = new ContaCorrenteAluno();
				conta.setAluno(alunoEncontrado);
				conta.setNumeroDeAluno(alunoEncontrado.getNumeroDeAluno());
				conta.setDataMovimento(new Date());
				conta.setAnoLectivo(this.anoLectivoRepository.findByStatus(true).get(0));
				conta.setInstituicao(this.instituicaoRepository.findOne(2));
				conta.setValor(0.0);
				conta.setValorAnterior(0.0);
				this.contaConrrenteRepository.save(conta);

			}

			if (alunoEncontrado == null) {
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				c.setMensagem("Número do aluno não existe.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
			List<Guia> guiasEncontradas = this.repository
					.findByAlunoAndLiquidadaAndAnuladaOrderByDataVencimento(alunoEncontrado, false, false);

			List<GuiaCliente> gClinetes = new ArrayList<GuiaCliente>();
			GuiaCliente gCliente;
			GuiaAlunos guiaAlunos = new GuiaAlunos();

			int a = guiasEncontradas.size();
			int b = guiasEncontradas.size();

			boolean pegar;

			// LOGICA ANTIGA PARA ORGANIZAR AS GUIAS...
			for (Guia guia : guiasEncontradas) {
				gCliente = new GuiaCliente();
				BeanUtils.copyProperties(guia, gCliente, "Aluno", "AnoLectivo");
				gCliente.setAnoLectivo(guia.getAnoLectivo().getAnoLectivo());
				gCliente.setAnoLectivoDescricao(guia.getAnoLectivo().getAnoLectivoDescricao());
				gCliente.setNumero(guia.getNumeroGuia());
				// gCliente.s
				// Multa gg = this.multaRepository.findByGuia(guia);
				// if (gg != null) {
				// gCliente.setMulta(gg.getValorMulta());
				// }
				List<RegistroDocumentos> registroDocumento = this.registroDocumentoRepo.findByGuiaPagamento(guia);
				if (alunoEncontrado.getCurso().getGrau() == Grau.MESTRADO) {
					for (RegistroDocumentos rg : registroDocumento) {
						rg.getTipoDeclaracao();
						System.out.println(rg.getTipoDeclaracao().getDescricao());

						if (!registroDocumento.isEmpty()) {
							gCliente.setRequerimento(true);
						}
					}
				}

				pegar = a == b ? true : false;
				a--;
				gCliente.setLiquidar(pegar);
				gCliente.setValor(FormataData.formatarValor(guia.getValor()));
				gClinetes.add(gCliente);
			}
			AlunoAnexo alunoFoto = this.alunoAnexo.findByAluno(alunoEncontrado);

			if (alunoFoto == null) {
				AlunoAnexo alunoAnexo = new AlunoAnexo();

				alunoAnexo.setAluno(alunoEncontrado);
				alunoAnexo.setNumeroDeAluno(alunoEncontrado.getNumeroDeAluno());
				alunoAnexo.setFoto(null);
				// salvar os dados da foto
				this.alunoAnexo.save(alunoAnexo);
			}

			List<AnoLectivo> anoMatricula = this.anoLectivoRepository.findByStatus(true);

			Matricula mmm = this.matriculaRepositoy.findByAlunoAndAnoLectivoAndAnulado(alunoEncontrado,
					anoMatricula.get(0), false);

			guiaAlunos.setNome(alunoEncontrado.getNome());
			guiaAlunos.setNumeroAluno(Integer.parseInt(alunoEncontrado.getNumeroDeAluno()));
			guiaAlunos.setGuiasAlunos(gClinetes);

			guiaAlunos.setCredito(FormataData.formatarValor(contaAluno.getValor()));

			guiaAlunos.setContencioso(alunoEncontrado.isContencioso());
			guiaAlunos.setFimCurso(alunoEncontrado.isFimCurso());
			guiaAlunos.setInativo(alunoEncontrado.isInactivo());
			guiaAlunos.setFoto(alunoFoto.getFoto());
			guiaAlunos.setCurso(alunoEncontrado.getCurso().getPlano());
			guiaAlunos.setGrau(alunoEncontrado.getCurso().getGrau().getDescricao());

			if (mmm != null) {
				guiaAlunos.setBolseiro(mmm.getEmpresaConvenio() != null ? true : false);
				if (mmm.getEmpresaConvenio() != null)
					guiaAlunos.setEmpresaConvenio(mmm.getEmpresaConvenio().getDesignacao());
			}
			AnosInscricoes anosInscricoes;
			List<AnosInscricoes> inscricoesAluno = new ArrayList<AnosInscricoes>();
			List<Matricula> matriculas = this.matriculaRepositoy.findByAluno(alunoEncontrado);

			for (Matricula matricula : matriculas) {
				anosInscricoes = new AnosInscricoes();
				anosInscricoes.setAnoLectivo(matricula.getAnoLectivo().getAnoLectivo());
				anosInscricoes.setAnoLectivoDescricao(matricula.getAnoLectivo().getAnoLectivoDescricao());
				anosInscricoes.setCodigoAnoLectivo(matricula.getAnoLectivo().getId());
				inscricoesAluno.add(anosInscricoes);
				if (matricula.getAnoLectivo().getAnoLectivo() == LocalDate.now().getYear()) {
					guiaAlunos.setPercentagemDesconto(matricula.getPercentagemDesconto());
				}
			}

			guiaAlunos.setAnosInscricoes(inscricoesAluno);
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setResultado(guiaAlunos);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/detalhesGuia/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> detalhesGuia(@PathVariable Integer id) {
		ResponseCliente c = new ResponseCliente();

		Guia guia = this.repository.findOne(id);

		List<EmolumentoGuiaCliente> guiasHist = new ArrayList<EmolumentoGuiaCliente>();
		EmolumentoGuiaCliente guiaHist;
		List<GuiaPagamentoHistorico> todas = this.historicoGuiaRepository.findByGuia(guia);

		for (GuiaPagamentoHistorico gph : todas) {
			guiaHist = new EmolumentoGuiaCliente();

			guiaHist.setValor(gph.getValor());
			guiaHist.setEmolumento(gph.getEmolumento().getEmolumento());
			guiaHist.setId(gph.getEmolumento().getId());
			guiaHist.setCodigo(gph.getEmolumento().getCodigo());
			guiaHist.setObs(gph.getObs());

			guiasHist.add(guiaHist);
		}
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(guiasHist);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/liquidacao", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	@Transactional
	public ResponseEntity<ResponseCliente> liquidacaoDeGuia(@RequestBody GuiaLiquidacao liquidacao) {
		ResponseCliente c = new ResponseCliente();

		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistemaFR = localDate.format(formatter);
		String dataSistemaPP = localDate.format(formatter);
		
		FormataData forma = new FormataData();

		try {

			// 000
			Usuario usuario = this.usuarioRepository
					.findByUserName(liquidacao.getUserName() != null ? liquidacao.getUserName() : null);
			// provisório...

			if (usuario == null)
				usuario = this.usuarioRepository.findOne(4);

			Emolumento emolumentoAnulacao = this.emolumentoRepository.findOne(36);

			Emolumento emolumentoCredito = this.emolumentoRepository.findOne(1);

			Emolumento emolumentoMulta = emolumentoRepository.findOne(9);

			List<AnoLectivo> anoLectivo = this.anoLectivoRepository.findByStatus(true);
			@SuppressWarnings("unused")
			GuiaPagamentoHistorico hGuia = null;
			Instituicao instituicao = this.instituicaoRepository.findOne(2);
			Moeda moeda = this.moedaRepository.findOne(3);
			// ERNESTO SAMBONGO

			if (liquidacao.getBanco() == null && !liquidacao.isLiquidacaoCredito()) {
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				c.setMensagem("Informe o banco desta operação.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

			if (liquidacao.getDataDeposito() == null) {
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				c.setMensagem("Informe a data desta operação.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

			// BUSCA O BORDERO PARA VERIFICAR SE O MESMO JÁ EXISTE NA BASE.
			// Bordero numero =
			// this.borderoRepository.findByNumeroAndBancoId(liquidacao.getNumeroBorderaux(),liquidacao.getBanco());

			// System.err.println("O BANCO RECEBIDO É: "+liquidacao.getBanco());

			if (!liquidacao.isLiquidacaoCredito()) {
				Bordero numero = this.borderoRepository.findByNumero(liquidacao.getNumeroBorderaux());
				if (numero != null && liquidacao.isLiquidacaoCredito() == false) {
					c.setCodigo(ResponseCode.values()[2].getDescricao());
					c.setMensagem("Este número de operação já foi usado");
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
			}

			if (liquidacao.getId() == null) {
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				c.setMensagem("É obrigátorio selecionar uma guia.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

			if (liquidacao.getBanco() != null) {
				Banco isProxypay = this.bancoRepository.findOne(liquidacao.getBanco());
				if (isProxypay != null) {
					if (isProxypay.getId() == 21) {
						List<CompensassaoProvisoria> provisoria = this.compensassaoProvisoriaRepository
								.findByNumeroGuia(numeroGuia);
						if (provisoria.isEmpty()) {
							List<CompensassaoProvisoria> efetiva = this.compensassaoEfetivaRepository
									.findByNumeroGuia(numeroGuia);
							if (efetiva.isEmpty()) {
								c.setCodigo(ResponseCode.values()[2].getDescricao());
								c.setMensagem("Está guia não é um pagamento por Ref.");
								return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
							}
						}
					}
				}
			}

			if (liquidacao.isCandidatura()) {
				GuiaCandidatura guia = this.guiaCandidaturaRepository.findByNumeroGuia(liquidacao.getId().toString());
				if (guia.isLiquidada()) {
					c.setCodigo(ResponseCode.values()[2].getDescricao());
					c.setMensagem("Esta guia já foi liquidada antes.");
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
				// double valorLiquidacao=0;
				double valorPagar = 0;
				double valorResto = 0;
				if (liquidacao.isLiquidacaoCredito()) {
					ContaCorrenteCandidato cCandidato = this.correnteCandidatoRepository
							.findByCandidato(guia.getCandidato());
					if (cCandidato.getValor() >= guia.getValor()) {
						valorPagar = guia.getValor();
						valorResto = cCandidato.getValor() - guia.getValor();
						cCandidato.setValorAnterior(cCandidato.getValor());
						cCandidato.setValor(valorResto);
						this.correnteCandidatoRepository.save(cCandidato);
					}
				} else {
					valorPagar = liquidacao.getValorDeposito();
				}
				if (valorPagar == guia.getValor()) {
					if (liquidacao.isLiquidacaoCredito()) {
						Banco banco = this.bancoRepository.findOne(15);
						Bordero bordero = new Bordero();
						bordero.setBanco(banco);
						bordero.setDataRegistro(new Date());
						bordero.setDataDeposito(liquidacao.getDataDeposito());
						bordero.setValor(0);
						bordero.setNumero("0");
						bordero.setMoeda(moeda);
						this.borderoRepository.save(bordero);
					} else {
						Banco banco = this.bancoRepository.findOne(liquidacao.getBanco());
						Bordero bordero = new Bordero();
						bordero.setBanco(banco);
						bordero.setDataRegistro(new Date());
						bordero.setDataDeposito(liquidacao.getDataDeposito());
						bordero.setValor(liquidacao.getValorDeposito());
						bordero.setNumero(liquidacao.getNumeroBorderaux());
						bordero.setMoeda(moeda);
						this.borderoRepository.save(bordero);
					}

					String numero = "";

					/*AnoLectivo anoActivo = anoLectivoRepository.buscarPorEstado();
					String ano = String.valueOf(anoActivo.getAnoLectivo());
					String anoSubstring = ano.substring(2, 4);
					Integer anoLimpo = Integer.parseInt(anoSubstring);*/
					

					NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(7);
					Long proximoNumero = numeroGerado.getProximoNumero();

					// String numero = gerarNumeroDocService.geracaoNumero();
					numero = gerarNumeroDocService.gerarNumeroFacturaRecibo(numero, forma.anoLectivo(), proximoNumero);

					Guia FacturaReciboExiste = this.repository.findFacturaRecibo(numero);
					GuiaCandidatura faturaReciboCandidatura = this.guiaCandidaturaRepository.buscarRecibo(numero);
					if (FacturaReciboExiste != null || faturaReciboCandidatura != null) {
						do {
							proximoNumero++;

							numero = gerarNumeroDocService.gerarNumeroFacturaRecibo(numero, forma.anoLectivo(), proximoNumero);
							FacturaReciboExiste = this.repository.findFacturaRecibo(numero);
							faturaReciboCandidatura = this.guiaCandidaturaRepository.buscarRecibo(numero);
						} while (FacturaReciboExiste != null || faturaReciboCandidatura != null);
					}

					guia.setLiquidada(true);
					guia.setDataLiquidacao(new Date());
					guia.setBordero(liquidacao.getNumeroBorderaux());
					guia.setUsuarioLiquidou(usuario);
					guia.setDataSistemaFr(dataSistemaFR);
					guia.setNumeroFacturaRecibo(numero);
					guia.setDataEmissaoFr(new Date());
					guia.setTipoFactura(TipoFatura.FACTURA_RECIBO);

					// guia.set
					GuiaCandidatura guiaCand = this.guiaCandidaturaRepository.save(guia);
					this.gerarDocService.gerarFileFacturaReciboCandidato(guiaCand);

					numeroGerado.setUltimoNumero(proximoNumero);
					numeroGerado.setProximoNumero(proximoNumero + 1);
					this.numeroGeradoRepository.save(numeroGerado);

					c.setResultado(guiaCand.getNumeroFacturaRecibo());
					c.setCodigo(ResponseCode.values()[0].getDescricao());
					c.setMensagem("Proforma de candidatura liquidada com sucesso.");
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
					// CANDIDATURA RESOLVIDOOOOOOO....
				} else {
					c.setCodigo(ResponseCode.values()[2].getDescricao());
					c.setMensagem("Valor depósito deve ser igual ao da Guia.");
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
			} else {
				// BUSCAR A GUIA A SER LIQUIDADA.
				Guia guia = this.repository.findOne(liquidacao.getId());

				// Gerar numero FacturaRecibo
				String numeroFR = "";

				/*AnoLectivo anoActivoFR = anoLectivoRepository.buscarPorEstado();
				String anoFR = String.valueOf(anoActivoFR.getAnoLectivo());
				String anoSubstringFR = anoFR.substring(2, 4);
				Integer anoLimpoFR = Integer.parseInt(anoSubstringFR);*/

				NumeroGerado numeroGeradoFR = this.numeroGeradoRepository.findOne(7);
				Long proximoNumeroFR = numeroGeradoFR.getProximoNumero();

				// String numero = gerarNumeroDocService.geracaoNumero();
				numeroFR = gerarNumeroDocService.gerarNumeroFacturaRecibo(numeroFR, forma.anoLectivo(), proximoNumeroFR);

				Guia facturaReciboExiste = this.repository.findFacturaRecibo(numeroFR);
				GuiaCandidatura faturaReciboCandidatura = this.guiaCandidaturaRepository.buscarRecibo(numeroFR);
				if (facturaReciboExiste != null || faturaReciboCandidatura != null) {
					do {
						proximoNumeroFR++;

						numeroFR = gerarNumeroDocService.gerarNumeroFacturaRecibo(numeroFR, forma.anoLectivo(),
								proximoNumeroFR);
						facturaReciboExiste = this.repository.findFacturaRecibo(numeroFR);
						faturaReciboCandidatura = this.guiaCandidaturaRepository.buscarRecibo(numeroFR);
					} while (facturaReciboExiste != null || faturaReciboCandidatura != null);
				}
				// -----------------------------------------------------------------------------------------------------

				// Gerar numero proforma
				String numero = "";

				/*AnoLectivo anoActivo = anoLectivoRepository.buscarPorEstado();
				String ano = String.valueOf(anoActivo.getAnoLectivo());
				String anoSubstring = ano.substring(2, 4);
				Integer anoLimpo = Integer.parseInt(anoSubstring);*/

				NumeroGerado numeroGeradoFP = this.numeroGeradoRepository.findOne(6);
				Long proximoNumero = numeroGeradoFP.getProximoNumero();

				// String numero = gerarNumeroDocService.geracaoNumero();
				numero = gerarNumeroDocService.gerarNumeroFacturaProforma(numero, forma.anoLectivo(), proximoNumero);

				Guia proformaExiste = this.repository.findProforma(numero);
				GuiaCandidatura proformaCandidatura = this.guiaCandidaturaRepository.buscarProforma(numero);
				if (proformaExiste != null || proformaCandidatura != null) {
					do {
						proximoNumero++;

						numero = gerarNumeroDocService.gerarNumeroFacturaProforma(numero, forma.anoLectivo(), proximoNumero);
						proformaExiste = this.repository.findProforma(numero);
						proformaCandidatura = this.guiaCandidaturaRepository.buscarProforma(numero);
					} while (proformaExiste != null || proformaCandidatura != null);
				}
				
				/*String definitivoEx = "";

				AnoLectivo anoActivoEx = anoLectivoRepository.buscarPorEstado();
				Integer anoLimpoEx = anoActivoEx.getAnoLectivo();
				NumeroGerado numeroGeradoEx = this.numeroGeradoRepository.findOne(3);
				Long proximoNumeroEx = numeroGeradoEx.getProximoNumero();

				// String numero = gerarNumeroDocService.geracaoNumero();
				definitivoEx = gerarNumeroGuia(definitivoEx, anoLimpoEx, proximoNumeroEx);

				Guia borderouxExiste = this.repository.findByNumeroGuia(definitivoEx);
				
				if (borderouxExiste != null) {
					do {
						proximoNumeroEx++;

						definitivoEx = gerarNumeroGuia(definitivoEx, anoLimpoEx, proximoNumeroEx);
						borderouxExiste = this.repository.findByNumeroGuia(definitivoEx);
						
					} while (borderouxExiste != null);
				}*/
				

				List<GuiaPagamentoHistorico> aNMatricula = this.historicoGuiaRepository.findByGuiaAndEmolumento(guia,
						emolumentoAnulacao);

				Aluno aluno = guia.getAluno();
				//Double multaSimples = null;

				// verifica se a guia já havia sido liquidada antes...
				if (guia.isLiquidada()) {
					c.setCodigo(ResponseCode.values()[2].getDescricao());
					c.setMensagem("Esta guia já foi liquidada antes.");
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
				// BUSCA A CONTA CORRENTE DO ALUNO.
				ContaCorrenteAluno contaCorrente = this.contaConrrenteRepository.findByAluno(guia.getAluno());

				// PARA LIQUIDAR COM PARTE CREDITO
				// casou
				//double valorParte = contaCorrente.getValor() + liquidacao.getValorDeposito();

				// BUSCA O QUE A GUIA TEM DE SUPPLEMNTO
				double retornoMetodoMulta = getWorkingDaysBetweenTwoDates(guia.getDataVencimento(),
						liquidacao.getDataDeposito(), guia.getId());
				// ATRIBUI O SUPLEMENTO A VARIAVEL MULTA
				double multa = retornoMetodoMulta > 0 ? retornoMetodoMulta : 0;
				// DECLARAR A VARIAVEL DE MULTA COM O VALOR DA GUIA
				double valorMulta = FormataData.formatarValor(guia.getValor()) ;
				// multa acertada PUPILO
				// PERGUNTA SE PODE LIQUIDAR POR SUPLEMENTO... VALIDA O SUPLETO DA GUIA

				// 001
				// LIQUIDAÇÃO DE GUIAS POR CRÉDITO...
				if (liquidacao.isLiquidacaoCredito()) {
					// LIQUIDAR GUIA COM BASE NO CREDITO.
					if (FormataData.formatarValor(contaCorrente.getValor()) >= valorMulta) {

						double deducaoContaCorrente = contaCorrente.getValor() - valorMulta;

						contaCorrente.setValorAnterior(contaCorrente.getValor());
						contaCorrente.setValor(deducaoContaCorrente);
						// ATUALIZAR O VALOR DA CONTA CORRENTE.
						this.contaConrrenteRepository.save(contaCorrente);

						guia.setValor(0);
						guia.setGerouCredito(false);
						guia.setLiquidada(true);
						guia.setLiquidacaoAGT(true);
						guia.setDataLiquidacao(new Date());
						guia.setUsuarioLiquidou(usuario);
						guia.setDataEmissaoFr(new Date());
						guia.setUltimaModificacao(new Date());
						//guia.setNumeroGuia(definitivoEx);
						guia.setTipoFactura(TipoFatura.FACTURA_CREDITO);
						guia.setDataSistemaFr(dataSistemaFR);
						guia.setLiquidacaoCredito(true);
						Guia guiaSalva = this.repository.save(guia);

						//numeroGeradoEx.setUltimoNumero(proximoNumeroEx);
						//numeroGeradoEx.setProximoNumero(proximoNumeroEx + 1);
						//this.numeroGeradoRepository.save(numeroGeradoEx);

						Banco banco = this.bancoRepository.findByBanco("Valor em Crédito");

						Bordero bordero = new Bordero();
						// liquidacao
						bordero.setAluno(guia.getAluno());
						bordero.setBanco(banco);
						bordero.setDataRegistro(new Date());

						bordero.setDataDeposito(new Date());

						bordero.setValor(0);
						bordero.setNumero("CRT");
						bordero.setMoeda(moeda);
						bordero.setGuia(guia);
						this.borderoRepository.save(bordero);
						
						System.out.println("Primeiro");
						// REGISTAR O EMOLUMENTO DA LIQUIDAÇÃO COM CREDITO
						adicionarEmolumentoAGuia(liquidacao, emolumentoCredito, anoLectivo, guia, valorMulta);
						System.out.println("Segundo");
						// RETIR DO CONTENCIOSO
						contenciosoRetirar(aluno);
						// REGISTAR HISTÓRICO CREDITO.
						HistoricoCredito hCredito = new HistoricoCredito();
						// Aluno aluno = guia.getAluno();
						hCredito.setAluno(aluno);
						hCredito.setNumeroDeAluno(Integer.parseInt(aluno.getNumeroDeAluno()));
						hCredito.setAnoLectivo(anoLectivo.get(0));
						// hCredito.setBanco(banco);
						hCredito.setBorderoInterno("-" + guia.getNumeroGuia());
						hCredito.setBorderoExterno("-" + guia.getNumeroGuia());
						// hCredito.setBanco("");
						hCredito.setInstituicao(instituicao);
						hCredito.setMoeda(moeda);
						hCredito.setDataRegisto(new Date());
						hCredito.setDataDepositoExterno(liquidacao.getDataDeposito());

						float valor = (float) valorMulta * -1;
						hCredito.setValorDeposito(valor);
						hCredito.setUsuarioEmitiu(usuario);

						System.out.println("Terceiro");
						this.historicoCreditoRepository.save(hCredito);

						// RETIR DO CONTENCIOSO
						contenciosoRetirar(aluno);
						// ANULAR MATRICULA CASO SE TRATA DE UM EMOLUMENTO DE ANULAÇÃO DE MATRICULA
						anulacaoMatricula(usuario, guia.getAnoLectivo(), aNMatricula, aluno);

						// Gerar Historico da guia de pagamento
						gerarHistorico(guia);
						
						if(multa > 0) {
							
							GuiaPagamentoHistorico guiaHistorico = new GuiaPagamentoHistorico();
							Guia gMulta = new Guia();
							gMulta.setAluno(aluno);
							gMulta.setNumeroDeAluno(aluno.getNumeroDeAluno());
							gMulta.setDataEmicao(new Date());
							gMulta.setAutomaticamente(false);
							gMulta.setAnoLectivo(anoLectivo.get(0));
							gMulta.setInstituicao(instituicao);
							gMulta.setMoeda(moeda);
							gMulta.setUsuarioEmitiu(usuario);
							gMulta.setDataVencimento(new Date());
							gMulta.setUltimaModificacao(new Date());
							gMulta.setAcordo(false);
							gMulta.setParaAcordoPagamento(false);
							gMulta.setAnulada(false);
							gMulta.setLiquidada(false);
							gMulta.setGerouCredito(false);
							gMulta.setGeradaReferencia(false);
							gMulta.setGeradaOnline(false);
							gMulta.setNumeroFacturaProforma(numero);
							gMulta.setTipoFactura(TipoFatura.FACTURA_PROFORMA);
							gMulta.setDataSistema(dataSistemaPP);
							gMulta.setAlterada(false);
							gMulta.setLiquidacaoAGT(false);
							Guia guiaSava = this.repository.save(gMulta);

							this.gerarDocService.gerarFileProformaAluno(guiaSava);

							numeroGeradoFP.setUltimoNumero(proximoNumero);
							numeroGeradoFP.setProximoNumero(proximoNumero + 1);
							this.numeroGeradoRepository.save(numeroGeradoFP);

							// AUTOMAÇÃO PARA A GERAÇÃO DO NUMERO DA GUIA
							String definitivo = "";
							Integer lectivo = anoLectivo.get(0).getAnoLectivo();
							NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(3);
							Long proximoNumeroInteiro = numeroGerado.getProximoNumero();

							// metódo gerar numero de guia chamado
							definitivo = gerarNumeroGuia(definitivo, lectivo, proximoNumeroInteiro);

							Guia guiaExistente = this.repository.findByNumeroGuia(definitivo);
							if (guiaExistente != null) {
								do {
									proximoNumeroInteiro++;
									// metódo gerar numero de guia chamado
									definitivo = gerarNumeroGuia(definitivo, lectivo, proximoNumeroInteiro);
									guiaExistente = this.repository.findByNumeroGuia(definitivo);
								} while (guiaExistente != null);
							}
							// setar o valor da guia
							guiaSava.setNumeroGuia(definitivo);
							// atualizador os dados da ultima guia e a proxima guia
							numeroGerado.setUltimoNumero(proximoNumeroInteiro);
							numeroGerado.setProximoNumero(proximoNumeroInteiro + 1);
							this.numeroGeradoRepository.save(numeroGerado);

							guiaSava.setValor(multa);
							guiaSava.setUltimaModificacao(new Date());
							Guia salvaDefinitiva = this.repository.save(guiaSava);
							salvaDefinitiva.getNumeroGuia();

							// SETAR OS DADOS DO HISTORICO DA GUIA
							guiaHistorico.setAluno(aluno);
							guiaHistorico.setAnoLectivo(anoLectivo.get(0));
							guiaHistorico.setEmolumento(emolumentoMulta);
							guiaHistorico.setCodigoIva(emolumentoMulta.getCodigoIva());
							guiaHistorico.setPercentagemIva(emolumentoMulta.getPercentagemIva().toString());
							guiaHistorico.setQuantidade("1");
							guiaHistorico.setGuia(salvaDefinitiva);
							guiaHistorico.setNumeroDeAluno(aluno.getNumeroDeAluno());
							guiaHistorico.setValorImposto(0);
							guiaHistorico.setValorTotal(multa);
							guiaHistorico.setValor(multa);
							// PAVE
							guiaHistorico.setObs("Multa ref. ao recibo: " + guia.getNumeroGuia());

							this.historicoGuiaRepository.save(guiaHistorico);
							this.guiaService.gerarHistoricoAudit(guiaHistorico);
						}

						c.setResultado(guia.getNumeroGuia());
						c.setMensagem("Proforma Liquidada com sucesso.");
						c.setCodigo(ResponseCode.values()[0].getDescricao());
					} else {
						c.setMensagem("Valor de crédito insuficiente.");
						c.setCodigo(ResponseCode.values()[3].getDescricao());
					}
					// System.err.println("Liquidação com credito efetivada com sucesso...");
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				} else {
					// 002
					// LIQUIDAÇÃO NORMAL, VALOR NORMAL DEPOSITADO...

					if (liquidacao.getValorDeposito() == valorMulta) {

						Banco banco = this.bancoRepository.findOne(liquidacao.getBanco());

						Bordero bordero = new Bordero();
						// liquidacao
						bordero.setAluno(guia.getAluno());
						bordero.setBanco(banco);
						bordero.setDataRegistro(new Date());
						bordero.setDataDeposito(liquidacao.getDataDeposito());
						bordero.setValor(liquidacao.getValorDeposito());
						bordero.setNumero(liquidacao.getNumeroBorderaux());
						bordero.setMoeda(moeda);
						bordero.setGuia(guia);
						this.borderoRepository.save(bordero);

						
						guia.setGerouCredito(false);
						guia.setUsuarioLiquidou(usuario);
						guia.setLiquidada(true);
						guia.setLiquidacaoAGT(true);
						guia.setDataLiquidacao(new Date());
						guia.setDataEmissaoFr(new Date());
						guia.setBordero(liquidacao.getNumeroBorderaux());
						guia.setUltimaModificacao(new Date());
						guia.setNumeroFacturaRecibo(numeroFR);
						guia.setTipoFactura(TipoFatura.FACTURA_RECIBO);
						guia.setDataSistemaFr(dataSistemaFR);
						Guia guiaSalva = this.repository.save(guia);

						this.gerarDocService.gerarFileFacturaReciboAluno(guiaSalva);

						numeroGeradoFR.setUltimoNumero(proximoNumeroFR);
						numeroGeradoFR.setProximoNumero(proximoNumeroFR + 1);
						this.numeroGeradoRepository.save(numeroGeradoFR);
						

						// RETIR DO CONTENCIOSO
						contenciosoRetirar(aluno);
						// ANULAR MATRICULA CASO SE TRATA DE UM EMOLUMENTO DE ANULAÇÃO DE MATRICULA
						anulacaoMatricula(usuario, guia.getAnoLectivo(), aNMatricula, aluno);

						// Gerar Historico da guia de pagamento
						gerarHistorico(guia);
						
						if(multa > 0) {
							
							GuiaPagamentoHistorico guiaHistorico = new GuiaPagamentoHistorico();
							Guia gMulta = new Guia();
							gMulta.setAluno(aluno);
							gMulta.setNumeroDeAluno(aluno.getNumeroDeAluno());
							gMulta.setDataEmicao(new Date());
							gMulta.setAutomaticamente(false);
							gMulta.setAnoLectivo(anoLectivo.get(0));
							gMulta.setInstituicao(instituicao);
							gMulta.setMoeda(moeda);
							gMulta.setUsuarioEmitiu(usuario);
							gMulta.setDataVencimento(new Date());
							gMulta.setUltimaModificacao(new Date());
							gMulta.setAcordo(false);
							gMulta.setParaAcordoPagamento(false);
							gMulta.setAnulada(false);
							gMulta.setLiquidada(false);
							gMulta.setGerouCredito(false);
							gMulta.setGeradaReferencia(false);
							gMulta.setGeradaOnline(false);
							gMulta.setLiquidacaoCredito(false);
							gMulta.setNumeroFacturaProforma(numero);
							gMulta.setTipoFactura(TipoFatura.FACTURA_PROFORMA);
							gMulta.setDataSistema(dataSistemaPP);
							gMulta.setAlterada(false);
							gMulta.setLiquidacaoAGT(false);
							Guia guiaSava = this.repository.save(gMulta);

							this.gerarDocService.gerarFileProformaAluno(guiaSava);

							numeroGeradoFP.setUltimoNumero(proximoNumero);
							numeroGeradoFP.setProximoNumero(proximoNumero + 1);
							this.numeroGeradoRepository.save(numeroGeradoFP);

							// AUTOMAÇÃO PARA A GERAÇÃO DO NUMERO DA GUIA
							String definitivo = "";
							Integer lectivo = anoLectivo.get(0).getAnoLectivo();
							NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(3);
							Long proximoNumeroInteiro = numeroGerado.getProximoNumero();

							// metódo gerar numero de guia chamado
							definitivo = gerarNumeroGuia(definitivo, lectivo, proximoNumeroInteiro);

							Guia guiaExistente = this.repository.findByNumeroGuia(definitivo);
							if (guiaExistente != null) {
								do {
									proximoNumeroInteiro++;
									// metódo gerar numero de guia chamado
									definitivo = gerarNumeroGuia(definitivo, lectivo, proximoNumeroInteiro);
									guiaExistente = this.repository.findByNumeroGuia(definitivo);
								} while (guiaExistente != null);
							}
							// setar o valor da guia
							guiaSava.setNumeroGuia(definitivo);
							// atualizador os dados da ultima guia e a proxima guia
							numeroGerado.setUltimoNumero(proximoNumeroInteiro);
							numeroGerado.setProximoNumero(proximoNumeroInteiro + 1);
							this.numeroGeradoRepository.save(numeroGerado);

							guiaSava.setValor(multa);
							guiaSava.setUltimaModificacao(new Date());
							Guia salvaDefinitiva = this.repository.save(guiaSava);
							salvaDefinitiva.getNumeroGuia();

							// SETAR OS DADOS DO HISTORICO DA GUIA
							guiaHistorico.setAluno(aluno);
							guiaHistorico.setAnoLectivo(anoLectivo.get(0));
							guiaHistorico.setEmolumento(emolumentoMulta);
							guiaHistorico.setCodigoIva(emolumentoMulta.getCodigoIva());
							guiaHistorico.setPercentagemIva(emolumentoMulta.getPercentagemIva().toString());
							guiaHistorico.setQuantidade("1");
							guiaHistorico.setGuia(salvaDefinitiva);
							guiaHistorico.setNumeroDeAluno(aluno.getNumeroDeAluno());
							guiaHistorico.setValorImposto(0);
							guiaHistorico.setValorTotal(multa);
							guiaHistorico.setValor(multa);
							// PAVE
							guiaHistorico.setObs("Multa ref. ao recibo: " + guia.getNumeroGuia());

							this.historicoGuiaRepository.save(guiaHistorico);
							this.guiaService.gerarHistoricoAudit(guiaHistorico);
						}

						c.setResultado(guiaSalva.getNumeroFacturaRecibo());
						c.setMensagem("Proforma Liquidada com sucesso.");
						c.setCodigo(ResponseCode.values()[0].getDescricao());
						return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
					} else {
						c.setCodigo(ResponseCode.values()[2].getDescricao());
						c.setMensagem("Valor depósito deve ser igual ao da Guia.");
						return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
					}
				}
			}
		} catch (Exception ex) {
			c.setMensagem(ex.getMessage() + " Aqui");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	@SuppressWarnings("unused")
	public Date dataCredito(Aluno aluno, float valorGuia) {
		List<HistoricoCredito> credito = this.historicoCreditoRepository.findByAlunoAndAnuladoOrderByIdDesc(aluno,
				false);
		float somaValor = 0;
		float controleValor = 0;
		float valorUsadoRecolocado = 0;
		for (HistoricoCredito o : credito) {
			HistoricoCredito histCred = this.historicoCreditoRepository.findOne(o.getId());

			Date dataCredito = null;

			if (o.getValorDeposito() > o.getValorUsado()) {
				somaValor += o.getValorDeposito() - o.getValorUsado();

				controleValor = (float) (valorGuia - somaValor);

				if (controleValor > 0) {

					// valorUsadoRecolocado=o.getValorUsado()+somaValor;
					histCred.setStatus(StatusCredito.USADO);
					histCred.setValorUsado(o.getValorDeposito());

					this.historicoCreditoRepository.save(histCred);
					return null;
				}
				if (controleValor == 0) {
					histCred.setStatus(StatusCredito.USADO);
					histCred.setValorUsado(o.getValorDeposito());

					this.historicoCreditoRepository.save(histCred);

					return null;
				}
				if (controleValor < 0) {
					histCred.setStatus(StatusCredito.PARCIAL);
					histCred.setValorUsado(o.getValorDeposito());

					this.historicoCreditoRepository.save(histCred);

					return null;
				}
			}
		}
		return new Date();
	}

	@Transactional
	private void gerarHistorico(Guia guia) {
		GuiaAudit audit = new GuiaAudit();
		BeanUtils.copyProperties(guia, audit);
		this.guiaAuditRepository.save(audit);
	}

	private void anulacaoMatricula(Usuario usuario, AnoLectivo anoLectivo, List<GuiaPagamentoHistorico> aNMatricula,
			Aluno aluno) {
		if (!aNMatricula.isEmpty()) {

			Matricula matricula = this.matriculaRepositoy.findByAlunoAndAnoLectivoAndAnulado(aluno, anoLectivo, false);

			if (matricula != null) {
				matricula.setAnulado(true);
				matricula.setDataAnulamento(new Date());
				matricula.setUsuarioAnulou(usuario);

				this.matriculaRepositoy.save(matricula);

				aluno.setInscrito(false);
				aluno.setUltimaModificacao(new Date());
				this.alunoRepository.save(aluno);
				this.alunoService.gerarHistorico(aluno);
			}
		}
	}

	@Transactional
	private void contenciosoRetirar(Aluno aluno) {
		List<Guia> abertas = this.repository.findByAlunoAndLiquidadaAndAnulada(aluno, false, false);

		if (abertas.isEmpty()) {
			aluno.setContencioso(false);
			aluno.setUltimaModificacao(new Date());
			this.alunoRepository.save(aluno);
			this.alunoService.gerarHistorico(aluno);
		}

		for (Guia guia : abertas) {
			Integer numeroDeDias = this.dataService.diferenciaDeDias(guia.getDataVencimento());
			if (numeroDeDias < 0) {
				aluno.setContencioso(true);
				aluno.setUltimaModificacao(new Date());
				this.alunoRepository.save(aluno);
				this.alunoService.gerarHistorico(aluno);
			}
		}

	}

	@RequestMapping(value = "/passagemDeCredito", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> passagemDeCredito(@RequestBody GuiaLiquidacao liquidacao) {
		ResponseCliente c = new ResponseCliente();

		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarEmolumentoAndAluno", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarAlunAndEmolumento(@RequestBody AlunoGuia busca) {
		ResponseCliente c = new ResponseCliente();
		try {
			if (busca.getAluno() == null && busca.getEmolumento() == null) {
				c.setMensagem("Os dados não podem ser nulus.");
				c.setCodigo(ResponseCode.values()[1].getDescricao());
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
			Aluno aluno = this.alunoRepository.findByNumeroDeAluno(busca.getAluno().toString());
			Emolumento emolumento = this.emolumentoRepository.findByCodigoAndStatus(busca.getEmolumento(), true);
			List<AnoLectivo> anoLectivo = this.anoLectivoRepository.findByStatus(true);

			if (aluno == null) {
				c.setMensagem("número de aluno não existe.");
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

			if (emolumento == null) {
				c.setMensagem("número de emolumento não existe.");
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
			EmolumentoHistorico histEmolumento = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(
					emolumento, aluno.getCurso(), anoLectivo.get(0));

			AlunoCreditoCliente acc = new AlunoCreditoCliente();
			EmolumentoCliente emolumentoCliente = new EmolumentoCliente();

			ContaCorrenteAluno contaCorrente = this.contaConrrenteRepository.findByAluno(aluno);

			acc.setNome(aluno.getNome());
			acc.setCurso(aluno.getCurso().getDescricao());
			acc.setNumero(aluno.getNumeroDeAluno());
			acc.setCredito(contaCorrente.getValor());

			emolumentoCliente.setValor(FormataData.formatarValor((double) histEmolumento.getValor()));
			emolumentoCliente.setEmolumento(histEmolumento.getEmolumento().getEmolumento());
			emolumentoCliente.setCodigo(histEmolumento.getEmolumento().getCodigo());
			emolumentoCliente.setId(histEmolumento.getEmolumento().getId());
			emolumentoCliente.setPercentagemIva(histEmolumento.getEmolumento().getPercentagemIva());
			emolumentoCliente.setCodigoIva(histEmolumento.getEmolumento().getCodigoIva());

			AlunoEmolumento alunoEmolumento = new AlunoEmolumento();

			alunoEmolumento.setAluno(acc);
			alunoEmolumento.setEmolumento(emolumentoCliente);
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setMensagem("Emolumento adicionado com sucesso.");
			c.setResultado(alunoEmolumento);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} catch (Exception ex) {
			String mensagem = ex.getMessage();
			c.setMensagem(mensagem);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	// ESTE METODO EFETUA A EMISSÃO DE UMA GUIA DE ACORDO AOS PARAMETROS PASSADOS
	// PELO USUARIO DO SISTEMA
	@RequestMapping(value = "/emissao", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	@Transactional
	public ResponseEntity<ResponseCliente> salvar(@RequestBody GuiaEmolumentoCliente emissao) {
		ResponseCliente c = new ResponseCliente();

		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);

		Usuario usuario = this.usuarioRepository
				.findByUserName(emissao.getUserName() != null ? emissao.getUserName() : null);
		// List<AnoLectivo> anoLectivo = this.anoLectivoRepository.findByStatus(true);

		AnoLectivo anoInscricao = this.anoLectivoRepository.findOne(emissao.getAnoInscricao());

		if (anoInscricao == null)
			anoInscricao = this.anoLectivoRepository.findOne(19);

		Map<Integer, String> mapaAnoCompleto = new HashMap<Integer, String>();
		Map<Integer, String> mapaPorDisciplina = new HashMap<Integer, String>();

		List<Emolumento> mensalidade = this.emolumentoRepository.findByPodeMulta(true);
		for (Emolumento o : mensalidade) {
			if (o.getId() >= 52 && o.getId() <= 67) {
				mapaAnoCompleto.put(o.getId(), o.getEmolumento());
			}

			if (o.getId() >= 111 && o.getId() <= 122) {
				mapaPorDisciplina.put(o.getId(), o.getEmolumento());
			}
		}
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(emissao.getAluno().toString());
		Matricula matricula = this.matriculaRepositoy.findByAlunoAndAnoLectivoAndAnulado(aluno, anoInscricao, false);
		/*
		 * List<EmolumentoDescCliente> ePego = emissao.getEmolumento(); boolean
		 * sair=false;
		 * 
		 * sair = rejeitarEmolumento(c, mapaAnoCompleto, mapaPorDisciplina, ePego, sair,
		 * matricula); if(sair) { c.setCodigo(ResponseCode.values()[3].getDescricao());
		 * return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK); }
		 */

		if (anoInscricao == null) {
			c.setMensagem("É obrigatório seelcionar o ano lectivo");
			c.setCodigo(ResponseCode.values()[1].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		if (emissao.getAluno() == null || emissao.getEmolumento() == null) {
			c.setMensagem("Verifique todos os campos.");
			c.setCodigo(ResponseCode.values()[1].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} else {

			Instituicao instituicao = this.instituicaoRepository.findOne(2);
			Moeda moeda = moedaRepository.findOne(3);
			GuiaPagamentoHistorico guiaHistorico;
			Guia guia = new Guia();
			guia.setAluno(aluno);
			guia.setNumeroDeAluno(aluno.getNumeroDeAluno());
			guia.setDataEmicao(new Date());
			guia.setAutomaticamente(false);
			guia.setAnulada(false);
			guia.setAlterada(false);
			guia.setLiquidada(false);
			guia.setGerouCredito(false);
			guia.setGeradaReferencia(false);
			guia.setGeradaOnline(false);
			guia.setLiquidacaoAGT(false);
			guia.setDataSistema(dataSistema);
			guia.setLiquidacaoCredito(false);
			// ANO GUIA PAGAMENTO
			// guia.setAnoLectivo(anoLectivo.get(0));
			guia.setAnoLectivo(anoInscricao);

			guia.setInstituicao(instituicao);
			guia.setMoeda(moeda);
			// guia.setParaAcordoPagamento(emissao.);
			guia.setParaAcordoPagamento(emissao.isGuiaParaAcordoPagamento());
			guia.setAcordo(emissao.isGuiaAcordo());
			// guia.setusuario
			guia.setUsuarioEmitiu(usuario);
			guia.setDataVencimento(emissao.getDataVencimento() != null ? emissao.getDataVencimento() : new Date());

			boolean temPropina = false;

			List<EmolumentoDescCliente> cEmolumento = emissao.getEmolumento();
			guia.setUltimaModificacao(new Date());
			Guia guiaSava = this.repository.save(guia);

			double valorGuia = 0;
			double desconto = 0;
			double valorComIva = 0;
			double valorImposto = 0;
			double valorTotalIvaDesconto = 0;

			for (EmolumentoDescCliente emluCliente : cEmolumento) {
				guiaHistorico = new GuiaPagamentoHistorico();
				Emolumento emolumento = this.emolumentoRepository.findOne(emluCliente.getId());

				guiaHistorico.setEmolumento(emolumento);
				guiaHistorico.setGuia(guiaSava);
				guiaHistorico.setDesconto(emluCliente.getValorDesconto());

				valorImposto = (emluCliente.getValor() * emluCliente.getPercentagemIva()) / 100;
				valorComIva = emluCliente.getValor() + valorImposto;

				valorTotalIvaDesconto = (valorComIva
						- (emluCliente.getValor() * emolumento.getPercentagemDesconto()) / 100);
				desconto = (emluCliente.getValor() * emolumento.getPercentagemDesconto()) / 100;

				if (desconto > 0) {
					guiaHistorico.setDesconto(FormataData.formatarValor(desconto));
					guiaHistorico.setValorTotal(FormataData.formatarValor(valorTotalIvaDesconto));
				} else {

					valorTotalIvaDesconto = (valorComIva - emluCliente.getValorDesconto());
					guiaHistorico.setDesconto(FormataData.formatarValor(emluCliente.getValorDesconto()));
					guiaHistorico.setValorTotal(FormataData.formatarValor(valorTotalIvaDesconto));
				}

				guiaHistorico.setValor(emluCliente.getValor());
				guiaHistorico.setAluno(aluno);
				guiaHistorico.setNumeroDeAluno(aluno.getNumeroDeAluno());
				guiaHistorico.setAnoLectivo(anoInscricao);
				guiaHistorico.setCodigoIva(emluCliente.getCodigoIva());
				guiaHistorico.setQuantidade("1");
				guiaHistorico.setPercentagemIva(emluCliente.getPercentagemIva().toString());
				guiaHistorico.setValorImposto(FormataData.formatarValor(valorImposto));

				// valorGuia += emluCliente.getValor() - emluCliente.getValorDesconto();
				valorGuia += valorTotalIvaDesconto;
				this.historicoGuiaRepository.save(guiaHistorico);
				this.guiaService.gerarHistoricoAudit(guiaHistorico);
			}

			// AUTOMAÇÃO PARA A GERAÇÃO DO NUMERO DA GUIA
			String definitivo = "";

			// anoInscricao
			Integer lectivo = anoInscricao.getAnoLectivo();
			NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(3);
			Long proximoNumeroInteiro = numeroGerado.getProximoNumero();

			// metódo gerar numero de guia chamado
			definitivo = gerarNumeroGuia(definitivo, lectivo, proximoNumeroInteiro);

			Guia guiaExistente = this.repository.findByNumeroGuia(definitivo);
			if (guiaExistente != null) {
				do {
					proximoNumeroInteiro++;
					// metódo gerar numero de guia chamado
					definitivo = gerarNumeroGuia(definitivo, lectivo, proximoNumeroInteiro);
					guiaExistente = this.repository.findByNumeroGuia(definitivo);
				} while (guiaExistente != null);
			}

			String numero = "";

			
			/*AnoLectivo anoActivo = anoLectivoRepository.buscarPorEstado();
			String ano = String.valueOf(anoActivo.getAnoLectivo());
			String anoSubstring = ano.substring(2, 4);
			Integer anoLimpo = Integer.parseInt(anoSubstring);*/
			
			FormataData form = new FormataData();
			Integer anoLimpo = form.anoLectivo();

			NumeroGerado numeroGeradoFP = this.numeroGeradoRepository.findOne(6);
			Long proximoNumero = numeroGeradoFP.getProximoNumero();

			// String numero = gerarNumeroDocService.geracaoNumero();
			numero = gerarNumeroDocService.gerarNumeroFacturaProforma(numero, anoLimpo, proximoNumero);

			Guia proformaExiste = this.repository.findProforma(numero);
			GuiaCandidatura proformaCandidaturaExistente = guiaCandidaturaRepository.buscarProforma(numero);
			if (proformaExiste != null || proformaCandidaturaExistente != null) {
				do {
					proximoNumero++;

					numero = gerarNumeroDocService.gerarNumeroFacturaProforma(numero, anoLimpo, proximoNumero);
					proformaExiste = this.repository.findProforma(numero);
					proformaCandidaturaExistente = guiaCandidaturaRepository.buscarProforma(numero);
				} while (proformaExiste != null || proformaCandidaturaExistente != null);
			}

			// setar o valor da guia
			guiaSava.setNumeroGuia(definitivo);
			guiaSava.setNumeroFacturaProforma(numero);
			Guia guiaGuardada = this.repository.save(guiaSava);

			this.gerarDocService.gerarFileProformaAluno(guiaGuardada);

			numeroGeradoFP.setUltimoNumero(proximoNumero);
			numeroGeradoFP.setProximoNumero(proximoNumero + 1);
			this.numeroGeradoRepository.save(numeroGeradoFP);

			// atualizador os dados da ultima guia e a proxima guia
			numeroGerado.setUltimoNumero(proximoNumeroInteiro);
			numeroGerado.setProximoNumero(proximoNumeroInteiro + 1);
			this.numeroGeradoRepository.save(numeroGerado);

			guiaGuardada.setTipoFactura(TipoFatura.FACTURA_PROFORMA);

			guiaGuardada.setValor(valorGuia);
			guiaGuardada.setUltimaModificacao(new Date());
			Guia salvaDefinitiva = this.repository.save(guiaGuardada);

			numeroGuia = salvaDefinitiva.getNumeroGuia();
			// GERAR DESCONTO NA PROPINA CASO O ESTUDATE TENHA.

			if (matricula != null && temPropina) {
				Integer percentagem = matricula.getPercentagemDesconto();
				if (percentagem > 0) {
					float perc = (float) percentagem / 100;

					float valor = (float) salvaDefinitiva.getValor() * perc;

					guiaHistorico = new GuiaPagamentoHistorico();

					Emolumento emolumento = this.emolumentoRepository.findOne(3);
					guiaHistorico.setEmolumento(emolumento);
					guiaHistorico.setGuia(guiaSava);
					guiaHistorico.setValor(valor * -1);
					guiaHistorico.setAluno(aluno);
					guiaHistorico.setNumeroDeAluno(aluno.getNumeroDeAluno());
					guiaHistorico.setAnoLectivo(anoInscricao);
					this.historicoGuiaRepository.save(guiaHistorico);
					this.guiaService.gerarHistoricoAudit(guiaHistorico);
					float valorGuiaNovo = (float) salvaDefinitiva.getValor() - valor;
					salvaDefinitiva.setValor(valorGuiaNovo);
					salvaDefinitiva.setUltimaModificacao(new Date());
					this.repository.save(salvaDefinitiva);

				}
			}

			c.setResultado(salvaDefinitiva);
			c.setMensagem("Proforma emitida com sucesso. Nº: " + salvaDefinitiva.getNumeroFacturaProforma());
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/verificarPropina", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> verificarPropina(@RequestBody VerificarPropina verificarPropina) {

		ResponseCliente c = new ResponseCliente();
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(verificarPropina.getNumeroDealuno().toString());
		Emolumento emolumento = this.emolumentoRepository.findByCodigo(verificarPropina.getEmolumento());
		AnoLectivo anoLectivo = this.anoLectivoRepository.findByAnoLectivo(verificarPropina.getAnoLectivo());
		List<GuiaPagamentoHistorico> propina = this.historicoGuiaRepository
				.findByEmolumentoAndAlunoAndAnoLectivoAndGuiaAnulada(emolumento, aluno, anoLectivo, false);
		if (propina.isEmpty()) {
			c.setResultado(false);
		} else {
			c.setResultado(true);
		}
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	/*
	 * private boolean rejeitarEmolumento(ResponseCliente c, Map<Integer, String>
	 * mapaAnoCompleto, Map<Integer, String> mapaPorDisciplina,
	 * List<EmolumentoDescCliente> ePego, boolean sair, Matricula matricula) { if
	 * (matricula != null) { Integer tipoDeInscricao =
	 * matricula.getTipoInscricao().getId();
	 * 
	 * System.err.println("TIPPO DE INCRIÇÃO....: " + tipoDeInscricao);
	 * 
	 * if (!tipoDeInscricao.equals(4)) { for (EmolumentoDescCliente e : ePego) {//
	 * 77 boolean containsKey = mapaPorDisciplina.containsKey(e.getId()); if
	 * (containsKey) { c.setMensagem("Aluno inscrito por ano completo"); //return
	 * new ResponseEntity<ResponseCliente>(c, HttpStatus.OK); sair=true; } }
	 * 
	 * //this.verificarEmolumento88(mapaPorDisciplina, ePego, c);
	 * 
	 * } else { for (EmolumentoDescCliente e : ePego) {// 77 boolean containsKey =
	 * mapaAnoCompleto.containsKey(e.getId()); if (containsKey) {
	 * c.setMensagem("Aluno inscrito por disciplina");
	 * 
	 * //return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK); sair=true; }
	 * } } } return sair; }
	 */

	private String gerarNumeroGuia(String definitivo, Integer lectivo, Long proximoNumeroInteiro) {
		if (proximoNumeroInteiro >= 1 && proximoNumeroInteiro <= 9)
			definitivo = lectivo + "00000" + proximoNumeroInteiro;
		if (proximoNumeroInteiro >= 10 && proximoNumeroInteiro <= 99)
			definitivo = lectivo + "0000" + proximoNumeroInteiro;
		if (proximoNumeroInteiro >= 100 && proximoNumeroInteiro <= 999)
			definitivo = lectivo + "000" + proximoNumeroInteiro;
		if (proximoNumeroInteiro >= 1000 && proximoNumeroInteiro <= 9999)
			definitivo = lectivo + "00" + proximoNumeroInteiro;
		if (proximoNumeroInteiro >= 10000 && proximoNumeroInteiro <= 99999)
			definitivo = lectivo + "0" + proximoNumeroInteiro;
		if (proximoNumeroInteiro >= 100000 && proximoNumeroInteiro <= 999999)
			definitivo = lectivo + proximoNumeroInteiro.toString();
		return definitivo;
	}

	/*private String gerarNumeroBordereuxInterno(String definitivo, Integer lectivo, Long proximoNumeroInteiro) {
		if (proximoNumeroInteiro >= 1 && proximoNumeroInteiro <= 9)
			definitivo = "B" + lectivo + "00000" + proximoNumeroInteiro;
		if (proximoNumeroInteiro >= 10 && proximoNumeroInteiro <= 99)
			definitivo = "B" + lectivo + "0000" + proximoNumeroInteiro;
		if (proximoNumeroInteiro >= 100 && proximoNumeroInteiro <= 999)
			definitivo = "B" + lectivo + "000" + proximoNumeroInteiro;
		if (proximoNumeroInteiro >= 1000 && proximoNumeroInteiro <= 9999)
			definitivo = "B" + lectivo + "00" + proximoNumeroInteiro;
		if (proximoNumeroInteiro >= 10000 && proximoNumeroInteiro <= 99999)
			definitivo = "B" + lectivo + "0" + proximoNumeroInteiro;
		if (proximoNumeroInteiro >= 100000 && proximoNumeroInteiro <= 999999)
			definitivo = "B" + lectivo + proximoNumeroInteiro.toString();
		return definitivo;
	}*/

	/*
	 * private String gerarNumeroFacturaProforma(String definitivo, Integer lectivo,
	 * Long proximoNumeroInteiro) { if (proximoNumeroInteiro >= 1 &&
	 * proximoNumeroInteiro <= 999999) definitivo = "PP UGS" + lectivo + "/" +
	 * proximoNumeroInteiro; return definitivo; }
	 * 
	 * private String gerarNumeroFacturaRecibo(String definitivo, Integer lectivo,
	 * Long proximoNumeroInteiro) { if (proximoNumeroInteiro >= 1 &&
	 * proximoNumeroInteiro <= 999999) definitivo = "FR UGS" + lectivo + "/" +
	 * proximoNumeroInteiro; return definitivo; }
	 * 
	 * private String gerarNumeroNotaCredito(String definitivo, Integer lectivo,
	 * Long proximoNumeroInteiro) { if (proximoNumeroInteiro >= 1 &&
	 * proximoNumeroInteiro <= 999999) definitivo = "NC UGS" + lectivo + "/" +
	 * proximoNumeroInteiro; return definitivo; }
	 */

	@GetMapping("/relatorio")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioReciboPagamento(@RequestParam String userName) throws Exception {
		byte[] relatrio = servicoReciboPagamento(numeroGuia, userName);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoReciboPagamento(String codigoGuia, String userName) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("numero_guia", codigoGuia);
		paramets.put("nome", userName);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Guia.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		// DESCONECTAR ...

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/relatorioSegundaVia")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioReciboPagamento1(@RequestParam String numeroG, @RequestParam String userName)
			throws Exception {
		byte[] relatrio = servicoReciboPagamento1(numeroG, userName);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoReciboPagamento1(String codigoGuia, String userName) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("numero_guia", codigoGuia);
		paramets.put("nome", userName);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Guia.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/relatorio/fatura-recibo")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioFacturaRecibo(@RequestParam String n_faturaRecibo,
			@RequestParam String condicao) throws Exception {
		byte[] relatrio = servicoFacturaRecibo(n_faturaRecibo, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoFacturaRecibo(String n_faturaRecibo, String condicao) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("numero_factura_recibo", n_faturaRecibo);
		paramets.put("condicao", condicao);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Factura_Recibo.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		// DESCONECTAR ...

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/relatorio/faturaProForma")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioFacturaPro(@RequestParam String n_proForma, @RequestParam String condicao)
			throws Exception {
		byte[] relatrio = servicoFacturaPro(n_proForma, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoFacturaPro(String n_proForma, String condicao) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("numero_factura", n_proForma);
		paramets.put("condicao", condicao);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Factura_ProForma.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		// DESCONECTAR ...

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@RequestMapping(value = "/anular", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> anular(@RequestBody EntidadePadrao entidadePadrao) {
		ResponseCliente c = new ResponseCliente();

		Guia guia = this.repository.findOne(entidadePadrao.getCodigo());
		List<AnoLectivo> anoLectivo = this.anoLectivoRepository.findByStatusOrderByAnoLectivoDesc(true);
		Instituicao instituicao = this.instituicaoRepository.findOne(2);

		String mensagem = guia.isLiquidada() ? "Recibo anulado com sucesso." : "Guia Anulada com sucesso.";

		Moeda moeda = this.moedaRepository.findOne(3);
		Emolumento eCredito = this.emolumentoRepository.findOne(1);

		if (guia.isAnulada()) {
			c.setCodigo(300);
			c.setMensagem("Não é possivel anular guia que já havia sido anulada.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		Usuario usuario = this.usuarioRepository
				.findByUserName(entidadePadrao.getUserName() != null ? entidadePadrao.getUserName() : null);
		ContaCorrenteAluno contaCorrente;
		double valorCreditoPositivo = 0;

		if (guia.getUsuarioLiquidou() != null && guia.isLiquidada()) {
			if (guia.getUsuarioLiquidou().getId() == 47) {
				// Bordero bordero = this.borderoRepository.findByGuia(guia);
				// BUSCAR CONTA CORRENTE
				contaCorrente = this.contaConrrenteRepository.findByAluno(guia.getAluno());
				// if (bordero != null)
				// this.borderoRepository.delete(bordero);

				Usuario usuarioLiquidou = guia.getUsuarioLiquidou();
				// DESVALIDAR TORNAR O RECIBO EM GUIA.
				guia.setUsuarioAnulou(usuario);
				guia.setUsuarioLiquidou(null);
				guia.setBordero(null);
				guia.setDataLiquidacao(null);
				guia.setLiquidada(false);
				guia.setMotivoAnulacaoRecibo(entidadePadrao.getMotivoAnulacao());
				guia.setGerouCredito(false);
				guia.setUltimaModificacao(new Date());
				guia.setDataAnulacaoRecibo(new Date());
				guia.setDataLiquidacao(new Date());
				this.repository.save(guia);
				// VALOR
				Double valorAntigo = contaCorrente.getValor();
				double valorNovo = valorAntigo + guia.getValor();
				// ACERTO DE CONTA
				contaCorrente.setValorAnterior(valorAntigo);
				contaCorrente.setValor(valorNovo);
				contaCorrente.setDataMovimento(new Date());
				this.contaConrrenteRepository.save(contaCorrente);

				// FAZER HISTORICO DE ENTRADA
				HistoricoCredito historicoCredito = new HistoricoCredito();
				historicoCredito.setAluno(guia.getAluno());
				historicoCredito.setAnoLectivo(anoLectivo.get(0));
				historicoCredito.setUsuarioEmitiu(usuarioLiquidou);
				float fValor = (float) guia.getValor();
				historicoCredito.setValorDeposito(fValor);
				historicoCredito.setDataRegisto(new Date());
				historicoCredito.setBorderoInterno("PROXY" + guia.getNumeroGuia());
				historicoCredito.setBorderoExterno("PROXY" + guia.getNumeroGuia());
				historicoCredito.setMoeda(moeda);
				historicoCredito.setInstituicao(instituicao);
				historicoCredito.setNumeroDeAluno(Integer.parseInt(guia.getAluno().getNumeroDeAluno()));
				historicoCredito.setUsuarioEmitiu(usuarioLiquidou);
				historicoCredito.setDataDepositoExterno(guia.getDataLiquidacao());
				this.historicoCreditoRepository.save(historicoCredito);

				c.setCodigo(200);
				c.setMensagem(mensagem);
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
		}

		if (guia.isLiquidada()) {

			// BUSCAR CONTA CORRENTE
			contaCorrente = this.contaConrrenteRepository.findByAluno(guia.getAluno());

			// VERIFICAR A EXISTENCIA DE GERAÇÃO DE CRÉDITO
			List<GuiaPagamentoHistorico> valorGuiaCredito;
			if (guia.isGerouCredito()) {
				valorGuiaCredito = this.historicoGuiaRepository.findByGuiaAndEmolumento(guia, eCredito);
				for (GuiaPagamentoHistorico o : valorGuiaCredito) {
					valorCreditoPositivo += o.getValor();
				}

				if (valorCreditoPositivo > contaCorrente.getValor()) {
					c.setCodigo(300);
					c.setMensagem("crédito deve ser > ao crédito (+) da guia");
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
			}
			// busca os emolumentos de crédito desta guia
			List<GuiaPagamentoHistorico> gCreditos = this.historicoGuiaRepository.findByGuiaAndEmolumento(guia,
					eCredito);

			if (gCreditos.isEmpty()) {
				Bordero bordero = this.borderoRepository.findByGuia(guia);
				if (bordero != null)
					this.borderoRepository.delete(bordero);

				// DESVALIDAR TORNAR O RECIBO EM GUIA.
				guia.setUsuarioAnulou(usuario);
				guia.setUsuarioLiquidou(null);
				guia.setBordero(null);
				guia.setDataLiquidacao(null);
				guia.setLiquidada(false);
				guia.setDataAnulacaoRecibo(new Date());
				guia.setMotivoAnulacaoRecibo(entidadePadrao.getMotivoAnulacao());
				guia.setGerouCredito(false);
				guia.setUltimaModificacao(new Date());
				guia.setDataLiquidacao(new Date());
				this.repository.save(guia);

			} else {
				double valorSomarConta = 0;
				for (GuiaPagamentoHistorico gp : gCreditos) {
					if (gp.getValor() < 0) {
						valorSomarConta += gp.getValor() * -1;

						// LIMPAR DADOS DE BORDEREUX
						List<Bordero> bordero = this.borderoRepository.findByGuiaAndAluno(guia, guia.getAluno());
						for (Bordero bs : bordero) {
							Bordero br = this.borderoRepository.findOne(bs.getId());
							br.setValor(0);
							br.setNumero("0");
							br.setGuia(null);
							this.borderoRepository.save(bordero);
						}

					} else {
						valorSomarConta -= gp.getValor();

						// LIMPAR DADOS DE BORDEREUX
						List<Bordero> bordero = this.borderoRepository.findByGuiaAndAluno(guia, guia.getAluno());
						for (Bordero bs : bordero) {
							Bordero br = this.borderoRepository.findOne(bs.getId());
							br.setValor(0);
							br.setNumero("0");
							br.setGuia(null);
							this.borderoRepository.save(bordero);
						}
					}
					this.historicoGuiaRepository.delete(gp);
				}

				// AJUSTAR O VALOR DA GUIA DE ACORDO OS EMOLUMENTOS RESTANTES
				double valorIniciaGuia = 0;
				List<GuiaPagamentoHistorico> historico = this.historicoGuiaRepository.findByGuia(guia);
				for (GuiaPagamentoHistorico hist : historico) {
					valorIniciaGuia += hist.getValor();
				}

				guia.setValor(valorIniciaGuia);
				guia.setDataAnulacaoRecibo(new Date());
				guia.setUltimaModificacao(new Date());
				this.repository.save(guia);

				Double valorAntigo = contaCorrente.getValor();
				double valorNovo = valorAntigo + valorSomarConta;

				// ACERTO DE CONTA
				contaCorrente.setValorAnterior(valorAntigo);
				contaCorrente.setValor(valorNovo);
				contaCorrente.setDataMovimento(new Date());
				this.contaConrrenteRepository.save(contaCorrente);

				// FAZER HISTORICO DE ENTRADA
				HistoricoCredito historicoCredito = new HistoricoCredito();

				historicoCredito.setAluno(guia.getAluno());
				historicoCredito.setAnoLectivo(anoLectivo.get(0));
				historicoCredito.setUsuarioEmitiu(usuario);
				float fValor = (float) valorSomarConta;
				historicoCredito.setValorDeposito(fValor);
				historicoCredito.setDataRegisto(new Date());
				historicoCredito.setBorderoInterno("EXTORN" + guia.getNumeroGuia());
				historicoCredito.setBorderoExterno("EXTORN" + guia.getNumeroGuia());
				historicoCredito.setMoeda(moeda);
				historicoCredito.setInstituicao(instituicao);
				historicoCredito.setNumeroDeAluno(Integer.parseInt(guia.getAluno().getNumeroDeAluno()));
				historicoCredito.setUsuarioEmitiu(usuario);

				// DESVALIDAR TORNAR O RECIBO EM GUIA.
				guia.setUsuarioAnulou(usuario);
				guia.setUsuarioLiquidou(null);
				guia.setBordero(null);
				guia.setDataLiquidacao(null);
				guia.setLiquidada(false);
				guia.setMotivoAnulacaoRecibo(entidadePadrao.getMotivoAnulacao());
				guia.setGerouCredito(false);
				guia.setUltimaModificacao(new Date());
				guia.setDataAnulacaoRecibo(new Date());
				guia.setDataLiquidacao(new Date());
				this.repository.save(guia);

				this.historicoCreditoRepository.save(historicoCredito);

				GuiaPagamentoHistorico emMulta;
				List<GuiaPagamentoHistorico> multas = this.historicoGuiaRepository.findByGuiaAndEmolumentoId(guia, 9);
				for (GuiaPagamentoHistorico o : multas) {
					emMulta = this.historicoGuiaRepository.findOne(o.getId());
					this.historicoGuiaRepository.delete(emMulta);
				}

				double valorPorEmolumento = 0;
				List<GuiaPagamentoHistorico> findByGuia = this.historicoGuiaRepository.findByGuia(guia);
				for (GuiaPagamentoHistorico o : findByGuia) {
					valorPorEmolumento += o.getValor();
				}

				guia.setValor(valorPorEmolumento);
				guia.setUltimaModificacao(new Date());
				guia.setDataAnulacaoRecibo(new Date());
				guia.setDataLiquidacao(new Date());
				this.repository.save(guia);
			}
		} else {
			guia.setUsuarioAnulou(usuario);
			guia.setAnulada(true);
			guia.setDataAnulada(new Date());
			guia.setMotivoAnulacaoGuia(entidadePadrao.getMotivoAnulacao());
			guia.setUltimaModificacao(new Date());
			guia.setDataLiquidacao(new Date());
			this.repository.save(guia);
		}
		// RETIRA O ALUNO DO CONTENCIOSO CASO NÃO HOUVER GUIA (S) EM ABERTO
		// RETIR DO CONTENCIOSO
		contenciosoRetirar(guia.getAluno());

		// Gerar Historico da guia de pagamento
		gerarHistorico(guia);

		c.setCodigo(200);
		c.setMensagem(mensagem);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/anular2", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> anular2(@RequestBody EntidadePadrao entidadePadrao) {
		ResponseCliente c = new ResponseCliente();

		Guia guia = this.repository.findOne(entidadePadrao.getCodigo());
		List<AnoLectivo> anoLectivo = this.anoLectivoRepository.findByStatusOrderByAnoLectivoDesc(true);
		Instituicao instituicao = this.instituicaoRepository.findOne(2);

		String mensagem = guia.isLiquidada() ? "Recibo anulado com sucesso." : "Guia Anulada com sucesso.";

		Moeda moeda = this.moedaRepository.findOne(3);
		Emolumento eCredito = this.emolumentoRepository.findOne(1);

		if (guia.isAnulada()) {
			c.setCodigo(300);
			c.setMensagem("Não é possivel anular guia que já havia sido anulada.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		Usuario usuario = this.usuarioRepository
				.findByUserName(entidadePadrao.getUserName() != null ? entidadePadrao.getUserName() : null);
		ContaCorrenteAluno contaCorrente;
		double valorCreditoPositivo = 0;

		if (guia.isLiquidada()) {

			// BUSCAR CONTA CORRENTE
			contaCorrente = this.contaConrrenteRepository.findByAluno(guia.getAluno());

			// VERIFICAR A EXISTENCIA DE GERAÇÃO DE CRÉDITO
			List<GuiaPagamentoHistorico> valorGuiaCredito;
			if (guia.isGerouCredito()) {
				valorGuiaCredito = this.historicoGuiaRepository.findByGuiaAndEmolumento(guia, eCredito);
				for (GuiaPagamentoHistorico o : valorGuiaCredito) {
					valorCreditoPositivo += o.getValor();
				}

				if (valorCreditoPositivo > contaCorrente.getValor()) {
					c.setCodigo(300);
					c.setMensagem("crédito deve ser > ao crédito (+) da guia");
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
			}
			// busca os emolumentos de crédito desta guia
			List<GuiaPagamentoHistorico> gCreditos = this.historicoGuiaRepository.findByGuiaAndEmolumento(guia,
					eCredito);

			if (gCreditos.isEmpty()) {
				Bordero bordero = this.borderoRepository.findByGuia(guia);
				if (bordero != null)
					this.borderoRepository.delete(bordero);

				// DESVALIDAR TORNAR O RECIBO EM GUIA.
				guia.setUsuarioAnulou(usuario);
				guia.setUsuarioLiquidou(null);
				guia.setBordero(null);
				guia.setDataLiquidacao(null);
				guia.setLiquidada(false);

				guia.setMotivoAnulacaoRecibo(entidadePadrao.getMotivoAnulacao());
				guia.setGerouCredito(false);
				guia.setUltimaModificacao(new Date());
				this.repository.save(guia);

			} else {
				double valorSomarConta = 0;
				for (GuiaPagamentoHistorico gp : gCreditos) {

					// SE O VALOR DO CRÉDITO FOR NEGATIVO, QUER DIZER QUE DEVE SOMAR O VALOR A CONTA
					// CORRENTE.
					// SE O VALOR DO CRÉFITO FOR POSITIVO, QUER DIZER QUE DEVE RETIRAR VALOR A CONTA
					// CORRENTE.
					if (gp.getValor() < 0) {
						valorSomarConta += gp.getValor() * -1;

						// LIMPAR DADOS DE BORDEREUX
						List<Bordero> bordero = this.borderoRepository.findByGuiaAndAluno(guia, guia.getAluno());
						for (Bordero bs : bordero) {
							Bordero br = this.borderoRepository.findOne(bs.getId());
							br.setValor(0);
							br.setNumero("0");
							br.setGuia(null);
							this.borderoRepository.save(bordero);
						}

					} else {
						valorSomarConta -= gp.getValor();

						// LIMPAR DADOS DE BORDEREUX
						List<Bordero> bordero = this.borderoRepository.findByGuiaAndAluno(guia, guia.getAluno());
						for (Bordero bs : bordero) {
							Bordero br = this.borderoRepository.findOne(bs.getId());
							br.setValor(0);
							br.setNumero("0");
							br.setGuia(null);
							this.borderoRepository.save(bordero);
						}
					}
					this.historicoGuiaRepository.delete(gp);
				}

				// AJUSTAR O VALOR DA GUIA DE ACORDO OS EMOLUMENTOS RESTANTES
				double valorIniciaGuia = 0;
				List<GuiaPagamentoHistorico> historico = this.historicoGuiaRepository.findByGuia(guia);
				for (GuiaPagamentoHistorico hist : historico) {
					valorIniciaGuia += hist.getValor();
				}

				guia.setValor(valorIniciaGuia);
				guia.setUltimaModificacao(new Date());
				this.repository.save(guia);

				Double valorAntigo = contaCorrente.getValor();
				double valorNovo = valorAntigo + valorSomarConta;

				// ACERTO DE CONTA
				contaCorrente.setValorAnterior(valorAntigo);
				contaCorrente.setValor(valorNovo);
				contaCorrente.setDataMovimento(new Date());
				this.contaConrrenteRepository.save(contaCorrente);

				// FAZER HISTORICO DE ENTRADA
				HistoricoCredito historicoCredito = new HistoricoCredito();

				historicoCredito.setAluno(guia.getAluno());
				historicoCredito.setAnoLectivo(anoLectivo.get(0));
				historicoCredito.setUsuarioEmitiu(usuario);
				float fValor = (float) valorSomarConta;
				historicoCredito.setValorDeposito(fValor);
				historicoCredito.setDataRegisto(new Date());
				historicoCredito.setBorderoInterno("EXTORN" + guia.getNumeroGuia());
				historicoCredito.setBorderoExterno("EXTORN" + guia.getNumeroGuia());
				historicoCredito.setMoeda(moeda);
				historicoCredito.setInstituicao(instituicao);
				historicoCredito.setNumeroDeAluno(Integer.parseInt(guia.getAluno().getNumeroDeAluno()));
				historicoCredito.setUsuarioEmitiu(usuario);

				// DESVALIDAR TORNAR O RECIBO EM GUIA.
				guia.setUsuarioAnulou(usuario);
				guia.setUsuarioLiquidou(null);
				guia.setBordero(null);
				guia.setDataLiquidacao(null);
				guia.setLiquidada(false);
				guia.setMotivoAnulacaoRecibo(entidadePadrao.getMotivoAnulacao());
				guia.setGerouCredito(false);
				guia.setUltimaModificacao(new Date());
				this.repository.save(guia);

				this.historicoCreditoRepository.save(historicoCredito);
			}
		} else {
			guia.setUsuarioAnulou(usuario);
			guia.setAnulada(true);
			guia.setDataAnulada(new Date());
			guia.setMotivoAnulacaoGuia(entidadePadrao.getMotivoAnulacao());
			guia.setUltimaModificacao(new Date());
			this.repository.save(guia);
		}

		// RETIRA O ALUNO DO CONTENCIOSO CASO NÃO HOUVER GUIA (S) EM ABERTO
		// RETIR DO CONTENCIOSO
		contenciosoRetirar(guia.getAluno());

		c.setCodigo(200);
		c.setMensagem(mensagem);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/anularNovo", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> anularNovo(@RequestBody EntidadePadrao entidadePadrao) {
		ResponseCliente c = new ResponseCliente();

		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);
		
		FormataData forma = new FormataData();

		Usuario usuario = this.usuarioRepository
				.findByUserName(entidadePadrao.getUserName() != null ? entidadePadrao.getUserName() : null);
		ContaCorrenteAluno contaCorrente;
		
		String numero = "";

		NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(2006);
		Long proximoNumero = numeroGerado.getProximoNumero();

		numero = gerarNumeroDocService.gerarNumeroNotaCredito(numero, forma.anoLectivo(), proximoNumero);

		NotaCredito notaExiste = this.notaCreditoRepo.buscarNumeroNotaCredito(numero);
		if (notaExiste != null) {
			do {
				proximoNumero++;

				numero = gerarNumeroDocService.gerarNumeroNotaCredito(numero, forma.anoLectivo(), proximoNumero);
				notaExiste = this.notaCreditoRepo.buscarNumeroNotaCredito(numero);
			} while (notaExiste != null);
		}

		if (entidadePadrao.isCandidatura()) {
			GuiaCandidatura guiaCandidato = this.guiaCandidaturaRepository.findOne(entidadePadrao.getCodigo());
			if (guiaCandidato.isAnulada()) {
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				c.setMensagem("Esta guia já foi anulada.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

			guiaCandidato.setAnulada(true);
			guiaCandidato.setDataAnulada(new Date());
			guiaCandidato.setMotivoAnulacaoRecibo(entidadePadrao.getMotivoAnulacao());
			guiaCandidato.setUsuarioAnulou(usuario);
			guiaCandidato.setDataSistemaAnulacao(dataSistema);

			GuiaCandidatura gCandidatura = this.guiaCandidaturaRepository.save(guiaCandidato);
			
			NotaCredito notaCredito = new NotaCredito();

			notaCredito.setIdGuiaCandidatura(gCandidatura);
			notaCredito.setNumeroNotaCredito(numero);
			notaCredito.setTipoDoc(TipoDoc.FACTURA_RECIBO);
			notaCredito.setAlteracao(false);
			notaCredito.setValor(gCandidatura.getValor());
			notaCredito.setDataEmissao(new Date());
			notaCredito.setDataSistema(dataSistema);
			notaCredito.setUsuarioEmitiu(usuario);

			NotaCredito notaCreditoNC = notaCreditoRepo.save(notaCredito);

			this.gerarDocService.gerarFileNotaCredito(notaCreditoNC);

			numeroGerado.setUltimoNumero(proximoNumero);
			numeroGerado.setProximoNumero(proximoNumero + 1);
			this.numeroGeradoRepository.save(numeroGerado);

			c.setResultado(notaCreditoNC.getNumeroNotaCredito());
			c.setMensagem("Factura recibo anulada com sucesso.");
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		Guia guia = this.repository.findOne(entidadePadrao.getCodigo());
		List<AnoLectivo> anoLectivo = this.anoLectivoRepository.findByStatusOrderByAnoLectivoDesc(true);
		Instituicao instituicao = this.instituicaoRepository.findOne(2);

		String mensagem = guia.isLiquidada() ? "Factura-Recibo anulada com sucesso." : "Guia Anulada com sucesso.";

		Moeda moeda = this.moedaRepository.findOne(3);
		// Emolumento eCredito = this.emolumentoRepository.findOne(1);

		if (guia.isAnulada()) {
			c.setCodigo(300);
			c.setMensagem("Não é possivel anular guia que já havia sido anulada.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		if (guia.getUsuarioLiquidou() != null && guia.isLiquidada()) {

			if (guia.getUsuarioLiquidou().getId() == 47) {
				
				// BUSCAR CONTA CORRENTE
				contaCorrente = this.contaConrrenteRepository.findByAluno(guia.getAluno());

				Usuario usuarioLiquidou = guia.getUsuarioLiquidou();
				// DESVALIDAR TORNAR O RECIBO EM GUIA.
				guia.setUsuarioAnulou(usuario);
				guia.setBordero(null);
				guia.setAnulada(true);
				guia.setMotivoAnulacaoRecibo(entidadePadrao.getMotivoAnulacao());
				guia.setUltimaModificacao(new Date());
				guia.setDataAnulacaoRecibo(new Date());

				Guia gSalva = this.repository.save(guia);
				
				Bordero bordero = this.borderoRepository.findByGuia(guia);
				
				if (bordero != null)
					bordero.setNumero(null);
				bordero.setGuia(null);
				borderoRepository.save(bordero);
				
				NotaCredito notaCredito = new NotaCredito();

				notaCredito.setIdGuia(gSalva);
				//notaCredito.setIdFactura(facturaAnular);
				notaCredito.setValor(gSalva.getValor());
				notaCredito.setNumeroNotaCredito(numero);
				notaCredito.setTipoDoc(TipoDoc.FACTURA_RECIBO);
				notaCredito.setDataEmissao(new Date());
				notaCredito.setUsuarioEmitiu(usuario);
				notaCredito.setAlteracao(false);
				notaCredito.setDataSistema(dataSistema);

				NotaCredito salvo = this.notaCreditoRepo.save(notaCredito);

				this.gerarDocService.gerarFileNotaCredito(salvo);

				numeroGerado.setUltimoNumero(proximoNumero);
				numeroGerado.setProximoNumero(proximoNumero + 1);
				this.numeroGeradoRepository.save(numeroGerado);

				c.setCodigo(200);
				c.setMensagem(mensagem);
				c.setResultado(salvo.getNumeroNotaCredito());
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
		}

		if (guia.isLiquidada()) {

			// BUSCAR CONTA CORRENTE
			contaCorrente = this.contaConrrenteRepository.findByAluno(guia.getAluno());

			Usuario usuarioLiquidou = guia.getUsuarioLiquidou();

			// busca os emolumentos de crédito desta guia
			//REVER ESSA ABORDAGEM

			if (guia.isLiquidacaoCredito()) {

				List<GuiaPagamentoHistorico> gCreditos = this.historicoGuiaRepository.findByGuia(guia);
				double valorSomarConta = 0;
				for (GuiaPagamentoHistorico gp : gCreditos) {
					if (gp.getValor() < 0) {
						valorSomarConta += gp.getValor() * -1;

					} else {

						valorSomarConta += gp.getValor();
					}
				}

				Double valorAntigo = contaCorrente.getValor();
				double valorNovo = valorAntigo + valorSomarConta;

				// ACERTO DE CONTA
				contaCorrente.setValorAnterior(valorAntigo);
				contaCorrente.setValor(valorNovo);
				contaCorrente.setDataMovimento(new Date());
				this.contaConrrenteRepository.save(contaCorrente);

				// FAZER HISTORICO DE ENTRADA
				HistoricoCredito historicoCredito = new HistoricoCredito();

				historicoCredito.setAluno(guia.getAluno());
				historicoCredito.setAnoLectivo(anoLectivo.get(0));
				historicoCredito.setUsuarioEmitiu(usuario);
				float fValor = (float) valorSomarConta;
				historicoCredito.setValorDeposito(fValor);
				historicoCredito.setDataRegisto(new Date());
				historicoCredito.setBorderoInterno("EXTORN" + guia.getNumeroGuia());
				historicoCredito.setBorderoExterno("EXTORN" + guia.getNumeroGuia());
				historicoCredito.setMoeda(moeda);
				historicoCredito.setInstituicao(instituicao);
				historicoCredito.setNumeroDeAluno(Integer.parseInt(guia.getAluno().getNumeroDeAluno()));
				historicoCredito.setUsuarioEmitiu(usuario);

				// DESVALIDAR TORNAR O RECIBO EM GUIA.
				guia.setUsuarioAnulou(usuario);

				guia.setAnulada(true);
				guia.setMotivoAnulacaoRecibo(entidadePadrao.getMotivoAnulacao());
				guia.setUltimaModificacao(new Date());
				guia.setDataAnulacaoRecibo(new Date());
				this.repository.save(guia);
				this.historicoCreditoRepository.save(historicoCredito);

				GuiaPagamentoHistorico emMulta;
				List<GuiaPagamentoHistorico> multas = this.historicoGuiaRepository.findByGuiaAndEmolumentoId(guia, 9);
				for (GuiaPagamentoHistorico o : multas) {
					emMulta = this.historicoGuiaRepository.findOne(o.getId());
					this.historicoGuiaRepository.delete(emMulta);
				}

				double valorPorEmolumento = 0;
				List<GuiaPagamentoHistorico> findByGuia = this.historicoGuiaRepository.findByGuia(guia);
				for (GuiaPagamentoHistorico o : findByGuia) {
					valorPorEmolumento += o.getValor();
				}

				guia.setValor(valorPorEmolumento);
				guia.setUltimaModificacao(new Date());
				guia.setDataAnulacaoRecibo(new Date());
				guia.setDataLiquidacao(new Date());
				this.repository.save(guia);
			} else {
				guia.setUsuarioAnulou(usuario);

				guia.setAnulada(true);
				guia.setDataAnulacaoRecibo(new Date());
				guia.setMotivoAnulacaoRecibo(entidadePadrao.getMotivoAnulacao());
				guia.setUltimaModificacao(new Date());

				Guia gSalva = this.repository.save(guia);

				Bordero bordero = this.borderoRepository.findByGuia(guia);
				
				if (bordero != null)
					bordero.setNumero(null);
				bordero.setGuia(null);
				borderoRepository.save(bordero);
				
				NotaCredito notaCredito = new NotaCredito();

				notaCredito.setIdGuia(gSalva);
				//notaCredito.setIdFactura(facturaAnular);
				notaCredito.setValor(gSalva.getValor());
				notaCredito.setNumeroNotaCredito(numero);
				notaCredito.setTipoDoc(TipoDoc.FACTURA_RECIBO);
				notaCredito.setDataEmissao(new Date());
				notaCredito.setUsuarioEmitiu(usuario);
				notaCredito.setAlteracao(false);
				notaCredito.setDataSistema(dataSistema);

				NotaCredito salvo = this.notaCreditoRepo.save(notaCredito);

				this.gerarDocService.gerarFileNotaCredito(salvo);

				numeroGerado.setUltimoNumero(proximoNumero);
				numeroGerado.setProximoNumero(proximoNumero + 1);
				this.numeroGeradoRepository.save(numeroGerado);
				
				c.setCodigo(200);
				c.setResultado(salvo.getNumeroNotaCredito());
				c.setMensagem(mensagem);
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

		} else {
			guia.setUsuarioAnulou(usuario);
			guia.setAnulada(true);
			guia.setDataAnulada(new Date());
			guia.setMotivoAnulacaoGuia(entidadePadrao.getMotivoAnulacao());
			guia.setUltimaModificacao(new Date());
			guia.setDataLiquidacao(new Date());
			this.repository.save(guia);
		}
		// RETIRA O ALUNO DO CONTENCIOSO CASO NÃO HOUVER GUIA (S) EM ABERTO
		// RETIR DO CONTENCIOSO
		contenciosoRetirar(guia.getAluno());

		// Gerar Historico da guia de pagamento
		gerarHistorico(guia);

		c.setCodigo(200);
		c.setMensagem(mensagem);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/alterarNotaCredito", method = RequestMethod.PUT,
	 * produces = "application/json")
	 * 
	 * @ResponseBody
	 * 
	 * @CrossOrigin(origins = "*") public ResponseEntity<ResponseCliente>
	 * alterar(@RequestBody EntidadePadrao entidadePadrao) { ResponseCliente c = new
	 * ResponseCliente();
	 * 
	 * LocalDateTime localDate = LocalDateTime.now(); DateTimeFormatter formatter =
	 * DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"); String dataSistema =
	 * localDate.format(formatter);
	 * 
	 * Guia guia = this.repository.findOne(entidadePadrao.getCodigo());
	 * 
	 * if (guia.isAlterada()) { c.setCodigo(300);
	 * c.setMensagem("Esta factura-recibo já foi alterada."); return new
	 * ResponseEntity<ResponseCliente>(c, HttpStatus.OK); }
	 * 
	 * if (guia.isAnulada()) { c.setCodigo(300);
	 * c.setMensagem("Esta factura-recibo já foi Anulada."); return new
	 * ResponseEntity<ResponseCliente>(c, HttpStatus.OK); }
	 * 
	 * Usuario usuario =
	 * this.usuarioRepository.findByUserName(entidadePadrao.getUserName() != null ?
	 * entidadePadrao.getUserName() : null);
	 * 
	 * String definitivoNC = "";
	 * 
	 * Calendar hoje = Calendar.getInstance(); Integer lectivoNC =
	 * hoje.get(Calendar.YEAR); String valor = lectivoNC.toString().substring(2);
	 * lectivoNC = Integer.parseInt(valor); // anoInscricao //Integer lectivoR =
	 * ano.getAnoLectivo(); NumeroGerado numeroGeradoNC =
	 * this.numeroGeradoRepository.findOne(2006); Long proximoNumeroInteiroNC =
	 * numeroGeradoNC.getProximoNumero();
	 * 
	 * // metódo gerar numero de guia chamado definitivoNC =
	 * gerarNumeroNotaCredito(definitivoNC, lectivoNC, proximoNumeroInteiroNC);
	 * 
	 * NotaCredito reciboExistente =
	 * this.notaCreditoRepo.buscarNumeroNotaCredito(definitivoNC); if
	 * (reciboExistente != null) { do { proximoNumeroInteiroNC++; // metódo gerar
	 * numero de guia chamado definitivoNC = gerarNumeroNotaCredito(definitivoNC,
	 * lectivoNC, proximoNumeroInteiroNC); reciboExistente =
	 * this.notaCreditoRepo.buscarNumeroNotaCredito(definitivoNC); } while
	 * (reciboExistente != null); }
	 * 
	 * if(guia.getUsuarioLiquidou()!=null && guia.isLiquidada()) {
	 * guia.setDataAlteracao(new Date()); guia.setUsuarioAlterou(usuario);
	 * guia.setMotivoAnulacaoRecibo(entidadePadrao.getMotivoAnulacao());
	 * guia.setAlterada(true); this.repository.save(guia);
	 * 
	 * NotaCredito notaCredito = new NotaCredito();
	 * 
	 * notaCredito.setIdGuia(guia); notaCredito.setNumeroNotaCredito(definitivoNC);
	 * notaCredito.setTipoDoc(TipoDoc.FACTURA_RECIBO);
	 * notaCredito.setDataEmissao(new Date());
	 * notaCredito.setUsuarioEmitiu(usuario);
	 * notaCredito.setDataSistema(dataSistema);
	 * 
	 * NotaCredito notaCreditoSalva = notaCreditoRepo.save(notaCredito);
	 * 
	 * if(proximoNumeroInteiroNC == 1) { gerarFileNotaCredito(notaCreditoSalva);
	 * }else { System.out.println("Caiu NC 3");
	 * gerarFileNotaCredito2(notaCreditoSalva); }
	 * 
	 * numeroGeradoNC.setUltimoNumero(proximoNumeroInteiroNC);
	 * numeroGeradoNC.setProximoNumero(proximoNumeroInteiroNC + 1);
	 * this.numeroGeradoRepository.save(numeroGeradoNC); }
	 * 
	 * c.setCodigo(200); c.setMensagem("Nota de crédito gerada com sucesso.");
	 * return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK); }
	 */

	@RequestMapping(value = "/paraNegociacao", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> negociar(@RequestBody GuiaAcordoPagamentoCliente plano) {
		ResponseCliente c = new ResponseCliente();
		Guia guia = this.repository.findOne(plano.getId());

		// Usuario usuario =
		// this.usuarioRepository.findByUserName(plano.getUserName()!=null ?
		// plano.getUserName() : null);

		guia.setParaAcordoPagamento(plano.isParaAcordoPagamento());
		guia.setAnulada(true);
		guia.setMotivoAnulacaoGuia("Acordo de pagamento de multas.");
		// guia.setUsuarioAplicouAcordo(usuario);
		guia.setUltimaModificacao(new Date());
		this.repository.save(guia);
		c.setCodigo(200);
		c.setMensagem("Guia encaminha para negociação.");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	private void adicionarEmolumentoAGuia(GuiaLiquidacao liquidacao, Emolumento emolumentoCredito,
			List<AnoLectivo> anoLectivo, Guia guia, double valorMulta) {

		GuiaPagamentoHistorico hGuia = new GuiaPagamentoHistorico();
		
		//Matricula matricula = matriculaRepositoy.buscarUltimaMatricula(guia.getAluno().getNumeroDeAluno());
		
		EmolumentoHistorico emolumentoValor = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(
				emolumentoCredito, guia.getAluno().getCurso(), anoLectivo.get(0));

		hGuia.setAluno(guia.getAluno());
		hGuia.setAnoLectivo(guia.getAnoLectivo());
		hGuia.setEmolumento(emolumentoCredito);
		hGuia.setGuia(guia);
		hGuia.setNumeroDeAluno(guia.getAluno().getNumeroDeAluno());
		float novoValorEmolumento = (float) valorMulta * (-1);

		// SETA-SE UM NOVO VALOR AO EMOLUMENTO DE MULTA PARA O CURSO EM QUESTÃO...
		emolumentoValor.setValor(novoValorEmolumento);

		EmolumentoHistorico novoValorMulta = this.emolumentoHistoricoRepository.save(emolumentoValor);
		// ATRIBUI O NOVO VALOR DA MULTA
		hGuia.setValor(novoValorMulta.getValor());

		hGuia.setEmolumento(emolumentoCredito);
		hGuia.setObs("subtração no valor em crédito");
		this.historicoGuiaRepository.save(hGuia);
		this.guiaService.gerarHistoricoAudit(hGuia);
	}

	/*private void adicionarEmolumentoAGuiaMulta(GuiaLiquidacao liquidacao, Emolumento emolumentoMulta,
			List<AnoLectivo> anoLectivo, Guia guia, double valorMulta) {

		if (valorMulta > 0) {

			GuiaPagamentoHistorico hGuia = new GuiaPagamentoHistorico();
			EmolumentoHistorico emolumentoValor = this.emolumentoHistoricoRepository
					.findByEmolumentoAndCursoAndAnoLectivo(emolumentoMulta, guia.getAluno().getCurso(),
							anoLectivo.get(0));

			hGuia.setAluno(guia.getAluno());
			hGuia.setAnoLectivo(guia.getAnoLectivo());
			hGuia.setEmolumento(emolumentoMulta);
			hGuia.setGuia(guia);
			hGuia.setObs(emolumentoValor.getEmolumento().getEmolumento());
			hGuia.setNumeroDeAluno(guia.getAluno().getNumeroDeAluno());
			float novoValorEmolumento = (float) valorMulta;

			// SETA-SE UM NOVO VALOR AO EMOLUMENTO DE MULTA PARA O CURSO EM QUESTÃO...
			emolumentoValor.setValor(novoValorEmolumento);

			EmolumentoHistorico novoValorMulta = this.emolumentoHistoricoRepository.save(emolumentoValor);
			// ATRIBUI O NOVO VALOR DA MULTA
			hGuia.setValor(novoValorMulta.getValor());

			hGuia.setEmolumento(emolumentoMulta);
			SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");
			hGuia.setObs("Emitida em: " + guia.getDataEmicao() + " liquidada em: "
					+ formatar.format(guia.getDataLiquidacao()));
			this.historicoGuiaRepository.save(hGuia);
			this.guiaService.gerarHistoricoAudit(hGuia);
		}
	}*/

	/*
	 * private void gerarFileProforma(Guia guiaGuardada) {
	 * 
	 * String privateKey = "ChavePrivada.pem"; String nomeArquivo = "Registo.txt";
	 * String signedOutputFile = "Registo1.sha1"; String base64OutputFile =
	 * "Registo1.b64";
	 * 
	 * SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); String
	 * dataEmissao = formato.format(guiaGuardada.getDataEmicao());
	 * 
	 * try { File arquivo = new File(nomeArquivo); FileWriter fileWriter = new
	 * FileWriter(arquivo, false); BufferedWriter bufferedWriter = new
	 * BufferedWriter(fileWriter);
	 * 
	 * // Escrever informações no arquivo bufferedWriter.write(dataEmissao + ";" +
	 * guiaGuardada.getDataSistema() + ";" + guiaGuardada.getNumeroFacturaProforma()
	 * + ";" + 0.0 + ";");
	 * 
	 * // Fechar o BufferedWriter bufferedWriter.close();
	 * 
	 * System.out.println("Arquivo gerado com sucesso!"); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * // Executar comando
	 * "openssl dgst -sha1 -sign ChavePrivada.pem -out Registo1.sha1 Registo1.txt"
	 * executeCommand(String.format("openssl dgst -sha1 -sign %s -out %s %s",
	 * privateKey, signedOutputFile, nomeArquivo));
	 * 
	 * // Executar comando
	 * "openssl enc -base64 -in Registo1.sha1 -out Registo1.b64 -A"
	 * executeCommand(String.format("openssl enc -base64 -in %s -out %s -A",
	 * signedOutputFile, base64OutputFile));
	 * 
	 * try { // Lê o conteúdo do arquivo base64 String base64Data =
	 * readFileAsString(base64OutputFile); guiaGuardada.setHashProforma(base64Data);
	 * repository.save(guiaGuardada);
	 * 
	 * System.out.println("Assa " + base64Data);
	 * 
	 * } catch (FileNotFoundException e) { e.printStackTrace(); } }
	 * 
	 * private void gerarFileProforma2(Guia guiaGuardada) {
	 * 
	 * String privateKey = "ChavePrivada.pem"; String nomeArquivo = "Registo.txt";
	 * String signedOutputFile = "Registo1.sha1"; String base64OutputFile =
	 * "Registo1.b64";
	 * 
	 * SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); String
	 * dataEmissao = formato.format(guiaGuardada.getDataEmicao()); //String
	 * dataEmissaoFR = formato.format(guiaGuardada.getDataEmissaoFr());
	 * 
	 * NumeroGerado numeroGeradoFP = this.numeroGeradoRepository.findOne(6); Long
	 * ultimoNumero = numeroGeradoFP.getUltimoNumero();
	 * 
	 * //Guia guiaProforma = null;
	 * 
	 * String proforma = "PP UGS23/" + ultimoNumero; Guia guiaProforma =
	 * repository.buscarProforma(proforma);
	 * 
	 * try { File arquivo = new File(nomeArquivo); FileWriter fileWriter = new
	 * FileWriter(arquivo, false); BufferedWriter bufferedWriter = new
	 * BufferedWriter(fileWriter);
	 * 
	 * // Escrever informações no arquivo bufferedWriter.write(dataEmissao + ";" +
	 * guiaGuardada.getDataSistema() + ";" + guiaGuardada.getNumeroFacturaProforma()
	 * + ";" + 0.0 + ";" + guiaProforma.getHashProforma());
	 * 
	 * // Fechar o BufferedWriter bufferedWriter.close();
	 * 
	 * } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * executeCommand(String.format("openssl dgst -sha1 -sign %s -out %s %s",
	 * privateKey, signedOutputFile, nomeArquivo));
	 * 
	 * executeCommand(String.format("openssl enc -base64 -in %s -out %s -A",
	 * signedOutputFile, base64OutputFile));
	 * 
	 * try { // Lê o conteúdo do arquivo base64 String base64Data =
	 * readFileAsString(base64OutputFile);
	 * 
	 * guiaGuardada.setHashProforma(base64Data); repository.save(guiaGuardada);
	 * 
	 * } catch (FileNotFoundException e) { e.printStackTrace(); } }
	 * 
	 * private void gerarFileFacturaRecibo(Guia guiaGuardada) {
	 * 
	 * String privateKey = "ChavePrivada.pem"; String nomeArquivo = "Registo.txt";
	 * String signedOutputFile = "Registo1.sha1"; String base64OutputFile =
	 * "Registo1.b64";
	 * 
	 * SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); String
	 * dataEmissaoFr = formato.format(guiaGuardada.getDataEmissaoFr());
	 * 
	 * try { File arquivo = new File(nomeArquivo); FileWriter fileWriter = new
	 * FileWriter(arquivo, false); BufferedWriter bufferedWriter = new
	 * BufferedWriter(fileWriter);
	 * 
	 * // Escrever informações no arquivo bufferedWriter.write(dataEmissaoFr + ";" +
	 * guiaGuardada.getDataSistemaFr() + ";" + guiaGuardada.getNumeroFacturaRecibo()
	 * + ";" + 0.0 + ";");
	 * 
	 * // Fechar o BufferedWriter bufferedWriter.close();
	 * 
	 * System.out.println("Arquivo gerado com sucesso!"); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * // Executar comando
	 * "openssl dgst -sha1 -sign ChavePrivada.pem -out Registo1.sha1 Registo1.txt"
	 * executeCommand(String.format("openssl dgst -sha1 -sign %s -out %s %s",
	 * privateKey, signedOutputFile, nomeArquivo));
	 * 
	 * // Executar comando
	 * "openssl enc -base64 -in Registo1.sha1 -out Registo1.b64 -A"
	 * executeCommand(String.format("openssl enc -base64 -in %s -out %s -A",
	 * signedOutputFile, base64OutputFile));
	 * 
	 * try { // Lê o conteúdo do arquivo base64 String base64Data =
	 * readFileAsString(base64OutputFile);
	 * guiaGuardada.setHashFacturaRecibo(base64Data); repository.save(guiaGuardada);
	 * 
	 * System.out.println("Assa " + base64Data);
	 * 
	 * } catch (FileNotFoundException e) { e.printStackTrace(); } }
	 * 
	 * private void gerarFileFacturaRecibo2(Guia guiaGuardada) {
	 * 
	 * String privateKey = "ChavePrivada.pem"; String nomeArquivo = "Registo.txt";
	 * String signedOutputFile = "Registo1.sha1"; String base64OutputFile =
	 * "Registo1.b64";
	 * 
	 * SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); String
	 * dataEmissaoFR = formato.format(guiaGuardada.getDataEmissaoFr());
	 * 
	 * NumeroGerado numeroGeradoFP = this.numeroGeradoRepository.findOne(7); Long
	 * ultimoNumero = numeroGeradoFP.getUltimoNumero();
	 * 
	 * //Guia guiaProforma = null;
	 * 
	 * String FacturaRecibo = "FR UGS23/" + ultimoNumero; Guia guiaFRecibo =
	 * repository.findFacturaRecibo(FacturaRecibo);
	 * 
	 * try { File arquivo = new File(nomeArquivo); FileWriter fileWriter = new
	 * FileWriter(arquivo, false); BufferedWriter bufferedWriter = new
	 * BufferedWriter(fileWriter);
	 * 
	 * // Escrever informações no arquivo bufferedWriter.write(dataEmissaoFR + ";" +
	 * guiaGuardada.getDataSistemaFr() + ";" + guiaGuardada.getNumeroFacturaRecibo()
	 * + ";" + 0.0 + ";" + guiaFRecibo.getHashFacturaRecibo());
	 * 
	 * // Fechar o BufferedWriter bufferedWriter.close();
	 * 
	 * } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * executeCommand(String.format("openssl dgst -sha1 -sign %s -out %s %s",
	 * privateKey, signedOutputFile, nomeArquivo));
	 * 
	 * executeCommand(String.format("openssl enc -base64 -in %s -out %s -A",
	 * signedOutputFile, base64OutputFile));
	 * 
	 * try { // Lê o conteúdo do arquivo base64 String base64Data =
	 * readFileAsString(base64OutputFile);
	 * 
	 * guiaGuardada.setHashFacturaRecibo(base64Data); repository.save(guiaGuardada);
	 * 
	 * } catch (FileNotFoundException e) { e.printStackTrace(); } }
	 * 
	 * 
	 * private void gerarFileNotaCredito(NotaCredito notaCreditoSalva) {
	 * 
	 * String privateKey = "ChavePrivada.pem"; String nomeArquivo = "Registo.txt";
	 * String signedOutputFile = "Registo1.sha1"; String base64OutputFile =
	 * "Registo1.b64";
	 * 
	 * SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); String
	 * dataEmissao = formato.format(notaCreditoSalva.getDataEmissao());
	 * 
	 * try { File arquivo = new File(nomeArquivo); FileWriter fileWriter = new
	 * FileWriter(arquivo, false); BufferedWriter bufferedWriter = new
	 * BufferedWriter(fileWriter);
	 * 
	 * // Escrever informações no arquivo bufferedWriter.write(dataEmissao + ";" +
	 * notaCreditoSalva.getDataSistema() + ";" +
	 * notaCreditoSalva.getNumeroNotaCredito() + ";" + 0.0 + ";");
	 * 
	 * // Fechar o BufferedWriter bufferedWriter.close();
	 * 
	 * System.out.println("Arquivo gerado com sucesso!"); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * // Executar comando
	 * "openssl dgst -sha1 -sign ChavePrivada.pem -out Registo1.sha1 Registo1.txt"
	 * executeCommand(String.format("openssl dgst -sha1 -sign %s -out %s %s",
	 * privateKey, signedOutputFile, nomeArquivo));
	 * 
	 * // Executar comando
	 * "openssl enc -base64 -in Registo1.sha1 -out Registo1.b64 -A"
	 * executeCommand(String.format("openssl enc -base64 -in %s -out %s -A",
	 * signedOutputFile, base64OutputFile));
	 * 
	 * try { // Lê o conteúdo do arquivo base64 String base64Data =
	 * readFileAsString(base64OutputFile); notaCreditoSalva.setHash(base64Data);
	 * notaCreditoRepo.save(notaCreditoSalva);
	 * 
	 * } catch (FileNotFoundException e) { e.printStackTrace(); } }
	 * 
	 * private void gerarFileNotaCredito2(NotaCredito notaCreditoSalva) {
	 * 
	 * String privateKey = "ChavePrivada.pem"; String nomeArquivo = "Registo.txt";
	 * String signedOutputFile = "Registo1.sha1"; String base64OutputFile =
	 * "Registo1.b64";
	 * 
	 * SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); String
	 * dataEmissaoNC = formato.format(notaCreditoSalva.getDataEmissao());
	 * 
	 * NumeroGerado numeroGeradoFP = this.numeroGeradoRepository.findOne(2006); Long
	 * ultimoNumero = numeroGeradoFP.getUltimoNumero();
	 * 
	 * 
	 * String notaNC = "NC UGS23/" + ultimoNumero; NotaCredito notaCreditoNC =
	 * notaCreditoRepo.buscarNumeroNotaCredito(notaNC);
	 * 
	 * try { File arquivo = new File(nomeArquivo); FileWriter fileWriter = new
	 * FileWriter(arquivo, false); BufferedWriter bufferedWriter = new
	 * BufferedWriter(fileWriter);
	 * 
	 * // Escrever informações no arquivo bufferedWriter.write(dataEmissaoNC + ";" +
	 * notaCreditoSalva.getDataSistema() + ";" +
	 * notaCreditoSalva.getNumeroNotaCredito() + ";" + 0.0 + ";" +
	 * notaCreditoNC.getHash());
	 * 
	 * // Fechar o BufferedWriter bufferedWriter.close();
	 * 
	 * } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * executeCommand(String.format("openssl dgst -sha1 -sign %s -out %s %s",
	 * privateKey, signedOutputFile, nomeArquivo));
	 * 
	 * executeCommand(String.format("openssl enc -base64 -in %s -out %s -A",
	 * signedOutputFile, base64OutputFile));
	 * 
	 * try { // Lê o conteúdo do arquivo base64 String base64Data =
	 * readFileAsString(base64OutputFile);
	 * 
	 * notaCreditoSalva.setHash(base64Data); notaCreditoRepo.save(notaCreditoSalva);
	 * 
	 * } catch (FileNotFoundException e) { e.printStackTrace(); } }
	 * 
	 * private static void executeCommand(String command) { try { Process process =
	 * Runtime.getRuntime().exec(command);
	 * 
	 * // Ler a saída do processo InputStream inputStream =
	 * process.getInputStream(); BufferedReader reader = new BufferedReader(new
	 * InputStreamReader(inputStream)); String line; while ((line =
	 * reader.readLine()) != null) { System.out.println(line); }
	 * 
	 * // Aguardar a finalização do processo int exitCode = process.waitFor();
	 * System.out.println("Comando executado. Código de saída: " + exitCode); }
	 * catch (IOException | InterruptedException e) { e.printStackTrace(); } }
	 * 
	 * private static String readFileAsString(String filePath) throws
	 * FileNotFoundException { StringBuilder contentBuilder = new StringBuilder();
	 * Scanner scanner = new Scanner(new FileInputStream(filePath));
	 * 
	 * while (scanner.hasNextLine()) { contentBuilder.append(scanner.nextLine()); }
	 * 
	 * scanner.close();
	 * 
	 * return contentBuilder.toString(); }
	 */

	// ------------
	public double getWorkingDaysBetweenTwoDates(Date startDateP, Date endDateP, Integer numeroGuia)
			throws ParseException {

		double umaMulta = 0;

		// PRECISAOS MEXER NISSO AGORAAAAA.....

		Guia guia = this.repository.findOne(numeroGuia);

		Contador contador = this.contadorRepository.findOne(31);
		boolean gerarMulta = true;

		if (contador != null) {
			if (contador.getProximoValor() == 0) {
				gerarMulta = false;
			}
		}

		List<GuiaPagamentoHistorico> historico = this.historicoGuiaRepository.findByGuia(guia);

		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");

		Date startDate = formatar.parse(formatar.format(startDateP));
		Date endDate = formatar.parse(formatar.format(endDateP));

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);

		// int workDays = 0;

		// Return 0 if start and end are the same
		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			return umaMulta;
		}

		// RETORNA 0 SE A DA INICIAL FOR MAIOR QUE A DATA FINAL
		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
			return umaMulta;
		}

		Calendar dtVencimento = Calendar.getInstance();
		dtVencimento.setTime(guia.getDataVencimento());
		Calendar dHoje = Calendar.getInstance();

		if (dtVencimento.get(Calendar.DAY_OF_MONTH) > 10 && dtVencimento.get(Calendar.DAY_OF_MONTH) < 22) {
			dHoje.compareTo(dtVencimento);
			if (dHoje.compareTo(dtVencimento) == 1) {
				umaMulta = (guia.getValor() * 0.10);
				if (dHoje.get(Calendar.DAY_OF_MONTH) == dtVencimento.get(Calendar.DAY_OF_MONTH)) {
					umaMulta = 0;
				}
			}
		}

		Integer bb = 0;
		DateFormat df = DateFormat.getDateInstance();
		do {
			// excluding start date
			startCal.add(Calendar.DAY_OF_MONTH, 1);

			boolean mensalidade = false;
			if (startCal.get(Calendar.DAY_OF_MONTH) == 11 || startCal.get(Calendar.DAY_OF_MONTH) == 22) {

				for (GuiaPagamentoHistorico m : historico) {
					// m.getEmolumento().getId() >= 52 && m.getEmolumento().getId() <= 67
					if (m.getEmolumento().isPropina()) {
						mensalidade = true;
					}
				}
				if (mensalidade && gerarMulta) {
					// PEGA A DATA EM QUESTÃO
					Date time = startCal.getTime();
					// PEGA O ANO EM STRING
					// String dataFormatada = df.format(time).substring(6);

					// ANO CALENDARIO
					String anoCalendario = df.format(time).substring(6);

					// ANO VENCIMENTO GUIA
					String anoGuiaEfetivo = df.format(guia.getDataVencimento()).substring(6);

					if (anoCalendario.equals(anoGuiaEfetivo)) {
						umaMulta += (guia.getValor() * 0.10);

						bb++;
					}
				}
			}
		} while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); // excluding end date

		boolean podeMulta = false;

		for (GuiaPagamentoHistorico o : historico) {
			if (o.getEmolumento().isPodeMulta()) {
				podeMulta = true;
			}
		}

		if (!podeMulta) {
			umaMulta = 0;
		}

		return umaMulta;
	}
	// ------------
}
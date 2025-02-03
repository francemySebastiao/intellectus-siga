package ao.co.intellectus.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.DetalhePagamento;
import ao.co.intellectus.DTO.GuiaPagamentoRelatorio;
import ao.co.intellectus.model.Contador;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaPagamentoHistorico;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.RegistroDocumentos;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.ContadorRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.HistoricoGuiaPagamentoRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.repository.RegistroDocumentoRepository;
import ao.co.intellectus.servico.cafold.Conexao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/guiaAndRecibo")
//guiaAndRecibo/ficha/candidatura
public class ControllerGuiaAndRecibo {
	@Autowired
	private GuiaPagamentoRepository guiaRepository;
	@Autowired
	private HistoricoGuiaPagamentoRepository historicoGuiaRepository;
	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private ContadorRepository contadorRepository;
	@Autowired
	private RegistroDocumentoRepository registroDocumentoRepo;
	// @Autowired
	// private CandidatoServie candidatoServie;
	// @Autowired
	// private GuiaService guiaService;

	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> detalhesGuia(@PathVariable Integer id) {
		ResponseCliente c = new ResponseCliente();

		String contexto = "";
		String tipoAccao = "";
		String formaPagamento = "";
		String usuarioAccao;

		Guia guia = id != null ? this.guiaRepository.findOne(id) : null;

		if (guia == null) {
			c.setMensagem("Não existem nenhuma guia com este número");
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		GuiaPagamentoRelatorio cGuia = new GuiaPagamentoRelatorio();
		Matricula matricula = this.matriculaRepository.findByAlunoAndAnoLectivo(guia.getAluno(), guia.getAnoLectivo());
		// recolha de parametros.
		tipoAccao = guia.isLiquidada() ? "Liquidada por" : "Emitida por";
		usuarioAccao = guia.isLiquidada() ? "Ernesto Tadeu T. Sambongo" : "Severino Emilio";
		contexto = guia.isLiquidada() ? "Recibo" : "Guia";
		// formaPagamento= guia.isLiquidada() ?
		// guia.getBordero().getBanco().getBanco():"";

		cGuia.setNumeroContribuinte(guia.getInstituicao().getUnidadeOrganica());
		cGuia.setInstituicao(guia.getInstituicao().getDescricao());
		cGuia.setGrupoOwner(guia.getInstituicao().getGrupoOwner());
		cGuia.setEndereco(guia.getInstituicao().getEndereco());
		cGuia.setNomeCompletoAluno(guia.getAluno().getNome());
		cGuia.setNumeroAluno(Integer.parseInt(guia.getAluno().getNumeroDeAluno()));
		cGuia.setMontanteTotal(guia.getValor());
		cGuia.setDataVencimento(guia.getDataVencimento());
		cGuia.setDataEmissao(guia.getDataEmicao());
		cGuia.setNumeroGuia(guia.getNumeroGuia());
		cGuia.setTipo(contexto);
		cGuia.setTipoAccao(tipoAccao);
		cGuia.setUsuarioAccao(usuarioAccao);
		cGuia.setFormaPagamento(formaPagamento);
		cGuia.setCurso(guia.getAluno().getCurso().getSigla());
		cGuia.setMorada(guia.getAluno().getMorada());
		cGuia.setAnoCurricular(matricula.getAnoCurricular());
		cGuia.setTurma(matricula.getTurmaBase().getTurma());

		cGuia.setEmitidoPor("Ernesto Tadeu Tchiteculo Sambongo");
		List<DetalhePagamento> detalhes = new ArrayList<DetalhePagamento>();
		DetalhePagamento detalhe;
		List<GuiaPagamentoHistorico> histGuias = this.historicoGuiaRepository.findByGuia(guia);
		for (GuiaPagamentoHistorico guiaPagamentoHistorico : histGuias) {
			detalhe = new DetalhePagamento();
			detalhe.setCodigo(guiaPagamentoHistorico.getEmolumento().getCodigo().toString());
			detalhe.setDescricao(guiaPagamentoHistorico.getEmolumento().getEmolumento());
			detalhe.setMontante(guiaPagamentoHistorico.getValor());

			detalhes.add(detalhe);
		}
		cGuia.setDetalhesPagamento(detalhes);
		c.setResultado(cGuia);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	public static Connection conectar() {
		Conexao con = new Conexao();
		return con.getConn();
	}

	@GetMapping("/recibo/pagamento")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioGuiaPagamento(@RequestParam String codigoGuia, @RequestParam String userName,
			@RequestParam String condicao) throws Exception {

		Guia guia = this.guiaRepository.findByNumeroGuia(codigoGuia);
		List<Matricula> inscricoes = this.matriculaRepository.findByAluno(guia.getAluno());
		int ano = 0;
		String turma = "";
		int maximaMatricula = 0;
		for (Matricula m : inscricoes) {
			if (maximaMatricula < m.getId()) {
				maximaMatricula = m.getId();
				turma = m.getTurmaBase().getTurma();
				ano = m.getAnoCurricular();
			}
		}
		byte[] relatrio = servicoGuiaPagamento(codigoGuia, userName, condicao, turma, ano);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoGuiaPagamento(String codigoGuia, String userName, String condicao, String turma, int ano)
			throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("numero_guia", codigoGuia);
		// paramets.put("nome", userName);
		paramets.put("condicao", condicao);
		paramets.put("turma", turma);
		paramets.put("ano", ano);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Guia_Pagamento.jasper");

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	@GetMapping("/requerimento/pagamento")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioRequerimentoPagamento(@RequestParam String numeroGuia, Integer id_guia,  @RequestParam String userName) throws Exception {

		Guia guia = this.guiaRepository.findByNumeroGuia(numeroGuia);
		List<Matricula> inscricoes = this.matriculaRepository.findByAluno(guia.getAluno());
		int ano = 0;
		String turma = "";
		int maximaMatricula = 0;
		for (Matricula m : inscricoes) {
			if (maximaMatricula < m.getId()) {
				maximaMatricula = m.getId();
				turma = m.getTurmaBase().getTurma();
				ano = m.getAnoCurricular();
			}
		}
		byte[] relatrio = servicoRequerimentoPagamento(numeroGuia, id_guia, userName, turma, ano);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoRequerimentoPagamento(String numeroGuia,Integer id_guia, String userName, String turma, int ano)
			throws JRException {
		
		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		
		java.text.DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
		String dataFormatada = formato.format(data);
		
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("numero_guia", numeroGuia);
		paramets.put("id_guia", id_guia);
		//paramets.put("condicao", condicao);
		paramets.put("turma", turma);
		paramets.put("ano", ano);
		paramets.put("data", dataFormatada);

		InputStream inputStream = null;
		
		RegistroDocumentos registroDocumento = this.registroDocumentoRepo.guiaPagamento(id_guia);
		
		if(registroDocumento.getTipoDeclaracao().getId() == 18) {
			System.out.println("Declaração matricula (SIMPLES)");
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Requerimento_Pedido_declaração_simples.jasper");
		}else if(registroDocumento.getTipoDeclaracao().getId() == 19){
			System.out.println("Certificado intermedio - 12500 ");
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Requerimento_certificado_declaracao_posgraduacao.jasper");
		}else if(registroDocumento.getTipoDeclaracao().getId() == 20) {
			System.out.println("Certificado intermedio 1º ano - 10000");
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Requerimento_certificado_intermedio_1ano.jasper");
		}else if(registroDocumento.getTipoDeclaracao().getId() == 21) {
			System.out.println("Certificado provisório");
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Requerimento_certificado_provisorio.jasper");
		}else if(registroDocumento.getTipoDeclaracao().getId() == 22) {
			System.out.println("Certificado Final");
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Requerimento_Certificado_Final.jasper");
		}else if(registroDocumento.getTipoDeclaracao().getId() == 23) {
			System.out.println("Carta pesquisa");
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Requerimento_declaracao_carta_pesquisa.jasper");
		}else if(registroDocumento.getTipoDeclaracao().getId() == 24) {
			System.out.println("Entidade Pesquisa");
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Requerimento_declaracao_entidade_pesquisa.jasper");
		}

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/factura-recibo/candidato")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioFacturaReciboCandidato(@RequestParam String n_faturaRecibo, @RequestParam String condicao) throws Exception {
		byte[] relatrio = servicoFacturaReciboCandidato(n_faturaRecibo, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoFacturaReciboCandidato(String n_faturaRecibo, String condicao) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("numero_factura_recibo", n_faturaRecibo);
		paramets.put("condicao", condicao);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Factura_Recibo.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	@GetMapping("/recibo/candidato")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioGuiaPagamentoCandidato(@RequestParam String codigoGuia) throws Exception {
		byte[] relatrio = servicoGuiaPagamentoCandidato(codigoGuia);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoGuiaPagamentoCandidato(String codigoGuia) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("numero_guia", codigoGuia);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Guia_Candidato_Recibo.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/guia/pagamento")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioReciboPagamento(@RequestParam String codigoGuia) throws Exception {
		byte[] relatrio = servicoReciboPagamento(codigoGuia);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoReciboPagamento(String codigoGuia) throws JRException {

		Map<String, Object> paramets = new HashMap<>();
		paramets.put("numero_guia", codigoGuia);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Guia.jasper");

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	@GetMapping("/guia/pagamento/credito")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioReciboPagamentoCredito(@RequestParam String codigoGuia, @RequestParam String userName) throws Exception {
		byte[] relatrio = servicoReciboPagamentoCredito(codigoGuia, userName);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoReciboPagamentoCredito(String numero_guia, String userName) throws JRException {

		Map<String, Object> paramets = new HashMap<>();
		paramets.put("numero_guia", numero_guia);
		paramets.put("nome", userName);
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Guia_Interno.jasper");

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/reconciliacao/bancario")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioReconciliancaoBancaria(@RequestParam String data1,
			@RequestParam String data2) throws Exception {
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");
		Date dataF1 = formatar.parse(data1);
		Date dataF2 = formatar.parse(data2);
		byte[] relatrio = servicoReconciliancaoBancaria(dataF1, dataF2);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoReconciliancaoBancaria(Date data1, Date dat2) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("data1", data1);
		paramets.put("data2", dat2);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Recon_Bancaria.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/ficha/candidatura/{id}")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> fichaCandidatura(@PathVariable Integer id) throws Exception {
		byte[] relatrio = fichaCandidaturaServico(id);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] fichaCandidaturaServico(Integer id) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("id", id);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_PreInscricao_Online.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/lista/guias/liquidadas")
	// @RequestMapping(value = "/lista/guias/liquidadas", method =
	// RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioListaGuiasLiquidadas(@RequestParam String data1, @RequestParam String data2,
			@RequestParam String tipoDocumento) throws Exception {
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");
		Date dataF1 = formatar.parse(data1);
		Date dataF2 = formatar.parse(data2);
		byte[] relatrio = servicoListaGuiasLiquidadasSimples(dataF1, dataF2, tipoDocumento);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoListaGuiasLiquidadasSimples(Date data1, Date dat2, String tipoDocumento) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("data1", data1);
		paramets.put("data2", dat2);
		InputStream inputStream = null;

		if (tipoDocumento.equalsIgnoreCase("Simples")) {
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Listagem_Simples.jasper");

		}
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/lista/guias/liquidadas/emolumento")
	// @RequestMapping(value = "/lista/guias/liquidadas", method =
	// RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioListaGuiasLiquidadasEmolumento(@RequestParam String data1,
			@RequestParam String data2, @RequestParam String tipoDocumento, String codigoEmolumento) throws Exception {
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");
		Date dataF1 = formatar.parse(data1);
		Date dataF2 = formatar.parse(data2);
		byte[] relatrio = servicoListaGuiasLiquidadas(dataF1, dataF2, tipoDocumento, codigoEmolumento);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoListaGuiasLiquidadas(Date data1, Date dat2, String tipoDocumento, String codigoEmolumento)
			throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("data1", data1);
		paramets.put("data2", dat2);
		InputStream inputStream = null;

		if (codigoEmolumento.equalsIgnoreCase("0")) {

			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Emolumento.jasper");

		} else {
			paramets.put("codigo_emolumento", Integer.parseInt(codigoEmolumento));
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Por_Emolumento.jasper");
		}
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/documentos/anulados")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioDocumentosAnulados(@RequestParam String data1, @RequestParam String data2,
			@RequestParam String tipo) throws Exception {
		System.out.println(data1);
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");
		Date dataF1 = formatar.parse(data1);
		Date dataF2 = formatar.parse(data2);

		byte[] relatrio = servicoDocumentosAnulados(dataF1, dataF2, tipo);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoDocumentosAnulados(Date data1, Date dat2, String tipo) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		

		InputStream inputStream = null;

		if (tipo.equals("Recibo")) {
			paramets.put("data1", data1);
			paramets.put("data2", dat2);
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Recibos_Anulados.jasper");
		} else if (tipo.equals("Guia")) {
			paramets.put("data1", data1);
			paramets.put("data2", dat2);
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Guias_Anuladas.jasper");
		}
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/bolseiros")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioBolseiros(@RequestParam Integer anoLectivo) throws Exception {
		byte[] relatrio = servicoBolseiros(anoLectivo);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoBolseiros(Integer anoLectivo) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("codigo_ano_lectivo", anoLectivo);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Bolseiro.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/bolseiros/instituicao")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioBolseirosPorInstituicacao(@RequestParam Integer anoLectivo,
			@RequestParam Integer instituicao) throws Exception {
		byte[] relatrio = servicoBolseirosPorInstituicacao(anoLectivo, instituicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoBolseirosPorInstituicacao(Integer anoLectivo, Integer instituicao) throws JRException {

		Map<String, Object> paramets = new HashMap<>();
		paramets.put("codigo_ano_lectivo", anoLectivo);
		paramets.put("instituicao", instituicao);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Por_Bolseiro.jasper");

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/bolseiros/GrauCurso")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioBolseirosPorGrauCurso(@RequestParam Integer anoLectivo,
			@RequestParam String grau, @RequestParam String curso) throws Exception {
		byte[] relatrio = servicoBolseirosPorGrauCurso(anoLectivo, grau, curso);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoBolseirosPorGrauCurso(Integer anoLectivo, String grau, String curso) throws JRException {

		Map<String, Object> paramets = new HashMap<>();
		paramets.put("ano", anoLectivo);
		paramets.put("grau", grau);
		paramets.put("curso", curso);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Bolseiros_Por_Grau_Curso.jasper");

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/alunos/contencioso")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioAlunosContencioso(@RequestParam String tipo, Integer anoLectivo,
			@RequestParam(required = false) String turma, String curso) throws Exception {
		byte[] relatrio = servicoAlunosContencioso(tipo, anoLectivo, curso, turma);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoAlunosContencioso(String tipo, Integer anoLectivo, String curso, String turma)
			throws JRException {
		Map<String, Object> paramets = new HashMap<>();

		InputStream inputStream = null;

		if (tipo.equals("ANO_LECTIVO")) {
			paramets.put("codigo_ano", anoLectivo);
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_List_Contencioso.jasper");
		} else if (tipo.equals("POR_TURMA")) {
			paramets.put("cod_ano", anoLectivo);
			paramets.put("cod_curso", curso);
			paramets.put("cod_turma", turma);
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Lista_Contencioso_Turma.jasper");
		}
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);

	}

	@GetMapping("/alunos/matricula/anulada")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioAlunosMatriculaAnulada(@RequestParam Integer anoLectivo, String grau,
			String curso) throws Exception {
		byte[] relatrio = servicoAlunosMatriculaAnulada(anoLectivo, grau, curso);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoAlunosMatriculaAnulada(Integer anoLectivo, String grau, String curso) throws JRException {
		Map<String, Object> paramets = new HashMap<>();

		InputStream inputStream = null;

		paramets.put("cod_ano", anoLectivo);
		paramets.put("grau", grau);
		paramets.put("curso", curso);
		inputStream = this.getClass().getResourceAsStream("/relatorio/R_Listagem_Alunos_Anulados_PorCurso.jasper");

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);

	}

	@GetMapping("/recibo/credito")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioGuiaCredito(@RequestParam String codigoGuia, @RequestParam String userName,
			@RequestParam String condicao) throws Exception {
		Guia guia = this.guiaRepository.findByNumeroGuia(codigoGuia);

		List<Matricula> inscricoes = this.matriculaRepository.findByAluno(guia.getAluno());

		int ano = 0;
		String turma = "";
		int maximaMatricula = 0;
		for (Matricula m : inscricoes) {
			if (maximaMatricula < m.getId()) {
				maximaMatricula = m.getId();
				turma = m.getTurmaBase().getTurma();
				ano = m.getAnoCurricular();
			}
		}

		byte[] relatrio = servicoGuiaCredito(codigoGuia, userName, condicao, turma, ano);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoGuiaCredito(String codigoGuia, String userName, String condicao, String turma, Integer ano)
			throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("numero_guia", codigoGuia);
		paramets.put("nome", userName);
		paramets.put("condicao", condicao);
		paramets.put("turma", turma);
		paramets.put("ano", ano);
		// ALTER RECIBO. HOJE
		// CATATA
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Guia_Pagamento.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/por/utilizadores")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> todosUtilizadores(@RequestParam String data1, @RequestParam String data2,
			@RequestParam Integer codigoUtilizador) throws Exception {
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");

		Date dataF1 = formatar.parse(data1);
		Date dataF2 = formatar.parse(data2);

		byte[] relatrio = servicoTodosUtilizadores(dataF1, dataF2, codigoUtilizador);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoTodosUtilizadores(Date data1, Date dat2, Integer codigoUtilizador) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("data1", data1);
		paramets.put("data2", dat2);
		paramets.put("codigo_utilizador", codigoUtilizador);

		InputStream inputStream = this.getClass()
				.getResourceAsStream("/relatorio/R_Listagem_Simples_Utilizador.jasper");

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	@GetMapping("/todos/utilizadores")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> todosUtilizadoresLiquidados(@RequestParam String data1, @RequestParam String data2) throws Exception {
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");

		Date dataF1 = formatar.parse(data1);
		Date dataF2 = formatar.parse(data2);

		byte[] relatrio = servicoTodosUtilizadoresLiquidados(dataF1, dataF2);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoTodosUtilizadoresLiquidados(Date data1, Date dat2) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("data1", data1);
		paramets.put("data2", dat2);

		InputStream inputStream = this.getClass()
				.getResourceAsStream("/relatorio/R_Listagem_Simples_TodosUtilizadores.jasper");

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@SuppressWarnings("unused")
	@GetMapping("/dias/uteis/{startDateP}/{endDateP}/{numeroGuia}")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> getWorkingDaysBetweenTwoDates(@PathVariable String startDateP,
			@PathVariable String endDateP, @PathVariable Integer numeroGuia) throws ParseException {
		ResponseCliente c = new ResponseCliente();
		double umaMulta = 0;

		Guia guia = this.guiaRepository.findOne(numeroGuia);

		Contador contador = this.contadorRepository.findOne(31);
		boolean gerarMulta = true;

		if (contador != null) {
			if (contador.getProximoValor() == 0) {
				gerarMulta = false;
			}
		}

		List<GuiaPagamentoHistorico> historico = this.historicoGuiaRepository.findByGuia(guia);

		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");
		Date startDate = formatar.parse(startDateP);
		Date endDate = formatar.parse(endDateP);

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);

		int workDays = 0;

		// Return 0 if start and end are the same
		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			c.setResultado(workDays);
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		// RETORNA 0 SE A DA INICIAL FOR MAIOR QUE A DATA FINAL
		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
			// startCal.setTime(endDate);
			// endCal.setTime(startDate);
			c.setResultado(workDays);
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		int bb = 0;

		DateFormat df = DateFormat.getDateInstance();
		guia.getDataVencimento();

		Calendar dtVencimento = Calendar.getInstance();
		dtVencimento.setTime(guia.getDataVencimento());

		Calendar dHoje = Calendar.getInstance();

		if (dtVencimento.get(Calendar.DAY_OF_MONTH) > 10 && dtVencimento.get(Calendar.DAY_OF_MONTH) < 22) {

			// dtVencimento.get(Calendar.DAY_OF_MONTH);
			// dHoje.get(Calendar.DAY_OF_MONTH);

			// dia hoje 5
			// vencimento 15

			dHoje.compareTo(dtVencimento);

			// dtVencimento.setTime(null);
			// dHoje.setTime(null);

			// System.err.println("COMPARAÇÃO ENTRE DUAS
			// DATAS...:"+dHoje.compareTo(dtVencimento));

			if (dHoje.compareTo(dtVencimento) == 1) {
				umaMulta = (guia.getValor() * 0.10);

				if (dHoje.get(Calendar.DAY_OF_MONTH) == dtVencimento.get(Calendar.DAY_OF_MONTH)) {
					umaMulta = 0;
				}
			}
		}

		do {
			// excluding start date
			startCal.add(Calendar.DAY_OF_MONTH, 1);

			// boolean mensalidade = false;
			if (startCal.get(Calendar.DAY_OF_MONTH) == 11 || startCal.get(Calendar.DAY_OF_MONTH) == 22) {

				/*
				 * for (GuiaPagamentoHistorico m : historico) { //m.getEmolumento().getId() >=
				 * 52 && m.getEmolumento().getId() <= 67 if (m.getEmolumento().isPodeMulta()) {
				 * mensalidade = true; } }
				 */
				if (gerarMulta) {
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
						// System.err.println("ANO CORRENTE: "+anoCalendario+" ANO VENCIMENTO GUIA:
						// "+anoGuiaEfetivo+" CONTA: "+ bb);
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

		if (podeMulta) {
			c.setResultado(umaMulta);
		} else {
			c.setResultado(0);
		}

		// c.setResultado(umaMulta);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// DESCONTINUADO POR ORDM DE PENSAMENTO UNIFORMIZADO (ERNESTO & SEVERINO EMILIO
	// INDIGENA ->>> 17-12-2018)
	@GetMapping("/descontinuado/uteis/{startDateP}/{endDateP}/{numeroGuia}")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> descontinuado(@PathVariable String startDateP, @PathVariable String endDateP,
			@PathVariable Integer numeroGuia) throws ParseException {
		ResponseCliente c = new ResponseCliente();

		Guia guia = this.guiaRepository.findOne(numeroGuia);

		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");
		Date startDate = formatar.parse(startDateP);
		Date endDate = formatar.parse(endDateP);

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);

		int workDays = 0;

		// Return 0 if start and end are the same
		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			c.setResultado(workDays);

			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
			startCal.setTime(endDate);
			endCal.setTime(startDate);
		}

		do {
			// excluding start date
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			// IGNORAMOS O SABADO TAMBÉM
			// startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY &&

			/*
			 * if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
			 * if(startCal.get(Calendar.DAY_OF_MONTH) <= 23 ) ++workDays; }
			 */

		} while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); // excluding end date

		// workDays
		double umaMulta = 0;

		/*
		 * if(workDays>=1) { umaMulta=(guia.getValor()*0.10);
		 * System.err.println("Entrei a primeira condição...."); }
		 */
		float qtdMultas = 0;
		float qtdMultaInteiro = 0;
		// workDays=workDays-1;
		if (workDays >= 1) {
			float fixo = 11;
			// qtdMultas=((float)workDays)/fixo;

			qtdMultas = workDays / fixo;

			// qtdMultaInteiro=Math.round(qtdMultas);
			qtdMultaInteiro = qtdMultas;

			if (qtdMultas < 1)
				qtdMultaInteiro = 0;

			for (int i = 0; i <= qtdMultaInteiro; i++) {
				umaMulta += (guia.getValor() * 0.10);
			}
		}

		c.setResultado(umaMulta);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		// return workDays;
	}

	@GetMapping("/mensalidades/liquidadas")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioMensalidadesLiquidadas(@RequestParam Integer codigo_ano) throws Exception {
		byte[] relatrio = servicoMensalidadesLiquidadas(codigo_ano);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoMensalidadesLiquidadas(Integer codigo_ano) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("codigo_ano", codigo_ano);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Listagem_Mensal.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
}
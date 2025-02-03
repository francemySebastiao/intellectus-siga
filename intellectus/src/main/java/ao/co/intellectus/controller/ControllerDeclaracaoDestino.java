package ao.co.intellectus.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.CertificadoIntermedio;
import ao.co.intellectus.model.Contador;
import ao.co.intellectus.model.DeclaracaoDestino;
import ao.co.intellectus.model.Grau;
import ao.co.intellectus.model.NumeroGerado;
import ao.co.intellectus.model.RegistroDocumentos;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.CertificadoIntermedioRepository;
import ao.co.intellectus.repository.ContadorRepository;
import ao.co.intellectus.repository.DeclaracaoDestinoRepository;
import ao.co.intellectus.repository.NumeroGeradoRepository;
import ao.co.intellectus.repository.RegistroDocumentoRepository;
import ao.co.intellectus.servico.cafold.Conexao;
import ao.co.intellectus.servico.cafold.DeclaracoesService;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/declaracaoDestino")
public class ControllerDeclaracaoDestino {
	@Autowired
	private DeclaracaoDestinoRepository declaracaoDestinoReposiotry;
	@Autowired
	private RegistroDocumentoRepository registroDocumentoRepository;
	@Autowired
	private CertificadoIntermedioRepository certificadoIntermedioRepository;
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private DeclaracoesService declaracoesService;
	@Autowired
	private NumeroGeradoRepository numeroGeradoRepository;
	@Autowired
	private ContadorRepository contadorRepository;

	// declaracaoDestino/modelo/final/{numero}
	@RequestMapping(value = "/modelo/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> modeloFinal(@PathVariable Integer numero) {

		List<CertificadoIntermedio> disciplinas = this.certificadoIntermedioRepository.findByNumeroOrderByAnoCurricularAsc(numero);
		Integer maximoAnoCurricular = disciplinas.isEmpty() ? null : disciplinas.get(disciplinas.size()-1).getAnoCurricular();
		if(maximoAnoCurricular != null) {
			return this.declaracoesService.validarDiciplinasPorAno(disciplinas, maximoAnoCurricular);
		}else {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Dados do aluno inconsistente");		
		}
		
	}

	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listarAnoLectivos() {
		ResponseCliente c = new ResponseCliente();

		Iterable<DeclaracaoDestino> todos = this.declaracaoDestinoReposiotry.findAll();

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(todos);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody DeclaracaoDestino dd){
		DeclaracaoDestino declaracaoDestino = this.declaracaoDestinoReposiotry.findByDescricao(dd.getDescricao());
		if(declaracaoDestino != null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Já existe esta declaração.");
		declaracaoDestino = new DeclaracaoDestino();
		declaracaoDestino.setDescricao(dd.getDescricao());
		this.declaracaoDestinoReposiotry.save(declaracaoDestino);
		return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Tipo de Declaração salva com sucesso.");
	}
	
	
	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> alterar(@RequestBody DeclaracaoDestino dd){
		DeclaracaoDestino declaracaoDestino = this.declaracaoDestinoReposiotry.findByDescricao(dd.getDescricao());
		if(declaracaoDestino.getId()!=null && declaracaoDestino.getDescricao().equalsIgnoreCase(dd.getDescricao()))
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Já existe esta declaração.");
		BeanUtils.copyProperties(dd, declaracaoDestino);
		this.declaracaoDestinoReposiotry.save(declaracaoDestino);
		return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Declaração salva com sucesso.");		
	}

	public static Connection conectar() {
		Conexao con = new Conexao();
		return con.getConn();
	}

	/*
	 * DESENVOLVEDOR: Severino Emilio Relatorio: declaração de matricula
	 * ATUALIZAÇÃO: 26-06-2018
	 */
	@GetMapping("/declaracao/matricula")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> declaracaoMatricula(@RequestParam Integer id, @RequestParam Integer anoLectivo,@RequestParam Integer efeito, @RequestParam Integer numeroPedido, @RequestParam String condicao)
			throws Exception {
		byte[] relatrio = servicoDeclaracaoMatricula(id, anoLectivo, efeito, numeroPedido, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoDeclaracaoMatricula(Integer numero, Integer anoLectivo, Integer efeito, Integer numeroPedido,String condicao) throws JRException {

		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
		

		String dataFormatada = formato.format(data);
		Map<String, Object> paramets = new HashMap<>();

		//numeroGerado(numeroPedido);
		
		paramets.put("numero_matricula", numero);
		paramets.put("id", anoLectivo);
		paramets.put("codigo_dec", numeroPedido);
		paramets.put("condicao", condicao);
		paramets.put("data", dataFormatada);
		// BUSCAR GRAU
		InputStream inputStream = null;

		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(numero.toString());

		if (aluno.getCurso().getGrau() == Grau.LICENCIATURA) {
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Dec_Simplies.jasper");
		} else if(aluno.getCurso().getGrau()== Grau.POSGRADUACAO) {
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Dec_Simples_Pos.jasper");
		} else if(aluno.getCurso().getGrau() == Grau.MESTRADO) {
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Declaracao_Simples_Mestrado.jasper");
		}
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		if (condicao.equals("1"))
			alterEstadoDocumento(numeroPedido);

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	
	//GERAR NÚMERO DE DECLARAÇÃO
	public void numeroGerado(Integer id) {
		//BUSCA O REGISTRO DO PEDIDO
		RegistroDocumentos registro = this.registroDocumentoRepository.findOne(id);
		// AUTOMAÇÃO PARA A GERAÇÃO DO NUMERO DA GUIA
		NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(7);
		Long proximoNumeroInteiro = numeroGerado.getProximoNumero();
	
		String gerado = proximoNumeroInteiro.toString();
		List<RegistroDocumentos> declaracaoExistente = this.registroDocumentoRepository.findByNumeroDeclaracaoAndAnoDeclaracao(Integer.parseInt(gerado),registro.getAnoDeclaracao());
		if (declaracaoExistente != null) {
			do {
				proximoNumeroInteiro++;
				gerado = proximoNumeroInteiro.toString();
				declaracaoExistente = this.registroDocumentoRepository.findByNumeroDeclaracaoAndAnoDeclaracao(Integer.parseInt(proximoNumeroInteiro.toString()),registro.getAnoDeclaracao());
				
				//DESSE JEITO NUNCA SAIRIAS DESSE LOOP....
				//declaracaoExistente = this.registroDocumentoRepository.findByNumeroDeclaracao(Integer.parseInt(gerado));
			} while (declaracaoExistente == null);
		}
		// SETAR O VALOR 
		registro.setNumeroDeclaracao(Integer.parseInt(gerado));
		// atualizador os dados da ultima guia e a proxima guia
		numeroGerado.setUltimoNumero(Long.parseLong(gerado));
		numeroGerado.setProximoNumero(Long.parseLong(gerado) + 1);
		this.numeroGeradoRepository.save(numeroGerado);
	}
	
	/* FIM: relatorioDeclaracaoMatricula */

	/*
	 * DESENVOLVEDOR: Severino Emilio Relatorio: Declaracao de lincenciatura
	 * ATUALIZAÇÃO: 26-06-2018
	 */
	@GetMapping("/declaracao/lincenciatura")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> declaracaoLincenciatura(@RequestParam Integer id, @RequestParam Integer numeroPedido,
			@RequestParam String condicao) throws Exception {
		byte[] relatrio = servicoDeclaracaoLincenciatura(id, numeroPedido, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoDeclaracaoLincenciatura(Integer numero, Integer numeroPedido, String condicao)
			throws JRException {
		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
		String dataFormatada = formato.format(data);

		Map<String, Object> paramets = new HashMap<>();
		
		paramets.put("numero_aluno", numero);
		paramets.put("condicao", condicao);
		paramets.put("data", dataFormatada);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Dec_Licenciatura.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		if (condicao.equals("1"))
			alterEstadoDocumento(numeroPedido);

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	/* FIM: relatorioDeclaracaoLincenciatura */
	/*
	 * DESENVOLVEDOR: Severino Emilio Relatorio: Declaracao de lincenciatura
	 * ATUALIZAÇÃO: 26-06-2018
	 */
	@GetMapping("/certificado/final")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> certificadoFinal(@RequestParam Integer id, @RequestParam Integer numeroPedido,@RequestParam String condicao) throws Exception {
		byte[] relatrio = servicoCertificadoFinal(id, numeroPedido, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoCertificadoFinal(Integer numero, Integer numeroPedido, String condicao) throws JRException {
		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
		Map<String, Object> paramets = new HashMap<>();
		
		//numeroGerado(numeroPedido);
		
		paramets.put("numero_matricula", numero);
		paramets.put("condicao", condicao);
		paramets.put("data", formato.format(data));
		paramets.put("numero_pedido",numeroPedido);
		
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(numero.toString());
		
		InputStream inputStream=null;
		if(aluno.getCurso().getGrau()==Grau.LICENCIATURA) {
			 inputStream = this.getClass().getResourceAsStream("/relatorio/R_Certificado_Final.jasper");
		} 
		if(aluno.getCurso().getGrau()==Grau.POSGRADUACAO) {
		    //PAY
			Contador contador = this.contadorRepository.findOne(34);
			
			if(contador!=null) {
			
				if(contador.getProximoValor()==0) {
					paramets.put("cod_regi",numeroPedido);	
					inputStream = this.getClass().getResourceAsStream("/relatorio/R_Certificado_Final__Pos.jasper");	
				}
				
				if(contador.getProximoValor()==1) {
					
					paramets.put("numero_aluno",numero);
					inputStream = this.getClass().getResourceAsStream("/relatorio/R_Diploma_Pos.jasper");
				}
			}
			
            if(contador==null) {
            	paramets.put("cod_regi",numeroPedido);	
				inputStream = this.getClass().getResourceAsStream("/relatorio/R_Certificado_Final__Pos.jasper");	
            }
		}
		
		if(aluno.getCurso().getGrau()==Grau.MESTRADO) {
			
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Certificado_Final_Mestrado.jasper");
		}
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

	if (condicao.equals("1"))
			alterEstadoDocumento(numeroPedido);

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	/* FIM: relatorioDeclaracaoLincenciatura */

	private void alterEstadoDocumento(Integer numeroPedido) {
		RegistroDocumentos pedido = this.registroDocumentoRepository.findOne(numeroPedido);
		pedido.setRetirado(true);
		pedido.setDataEmissao(new Date());
		this.registroDocumentoRepository.save(pedido);
	}

	@GetMapping("/declaracao/matricula/mestrado")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> declaracaoMatriculaMestrado(@RequestParam Integer id,
			@RequestParam Integer anoLectivo, @RequestParam Integer efeito, @RequestParam Integer numeroPedido,
			@RequestParam String condicao) throws Exception {
		byte[] relatrio = servicoDeclaracaoMatriculaMestrdo(id, anoLectivo, efeito, numeroPedido, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoDeclaracaoMatriculaMestrdo(Integer numero, Integer anoLectivo, Integer efeito,
			Integer numeroPedido, String condicao) throws JRException, SQLException {

		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
	

		String dataFormatada = formato.format(data);
		Map<String, Object> paramets = new HashMap<>();

		paramets.put("numero_matricula", numero);
		paramets.put("id", anoLectivo);
		paramets.put("codigo_dec", numeroPedido);
		paramets.put("condicao", condicao);
		paramets.put("data", dataFormatada);

		//Aluno aluno = this.alunoRepository.findByNumeroDeAluno(numero.toString());

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Declaracao_Simples_Mestrado.jasper");	
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		
		if (condicao.equals("1")) {
			alterEstadoDocumento(numeroPedido);
		}
		
		conectar().isClosed();
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/declaracao/matriculaIscid")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> declaracaoMatriculaIscid(@RequestParam Integer id, @RequestParam Integer anoLectivo,
			@RequestParam Integer efeito, @RequestParam Integer numeroPedido, @RequestParam String condicao)
			throws Exception {

		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
		

		String dataFormatada = formato.format(data);
		Map<String, Object> paramets = new HashMap<>();

		paramets.put("numero_matricula", id);
		paramets.put("id", anoLectivo);
		paramets.put("codigo_dec", efeito);
		paramets.put("condicao", condicao);
		paramets.put("data", dataFormatada);
		// BUSCAR GRAU
		InputStream inputStream;

		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(id.toString());

		if (aluno.getCurso().getGrau() == Grau.MESTRADO) {
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Dec_Simplies_Mestrado.jasper");
		} else {
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Dec_Simplies_escid.jasper");
		}
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		if (condicao.equals("1"))
			alterEstadoDocumento(numeroPedido);

		byte[] relatrio = JasperExportManager.exportReportToPdf(jasperPrint);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}
	
	

}
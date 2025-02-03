package ao.co.intellectus.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.BolseirosListaCliente;
import ao.co.intellectus.DTO.ContaCorrenteBuscar;
import ao.co.intellectus.DTO.CreditosResumoCliente;
import ao.co.intellectus.DTO.RegistroCreditoBolceiros;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Banco;
import ao.co.intellectus.model.Bordero;
import ao.co.intellectus.model.ContaCorrenteAluno;
import ao.co.intellectus.model.CreaditoDeAluno;
import ao.co.intellectus.model.EmpresaConvenio;
import ao.co.intellectus.model.HistoricoCredito;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.Moeda;
import ao.co.intellectus.model.NumeroGerado;
import ao.co.intellectus.model.ProgHistoricoCrtBolseiro;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.BancoRepository;
import ao.co.intellectus.repository.BorderoRepository;
import ao.co.intellectus.repository.ContaCorrenteRepository;
import ao.co.intellectus.repository.CreditoAlunoRepository;
import ao.co.intellectus.repository.EmpresaConvenioRepository;
import ao.co.intellectus.repository.HistoricoCreditoRepository;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.repository.MoedaRepository;
import ao.co.intellectus.repository.NumeroGeradoRepository;
import ao.co.intellectus.repository.ProgHistoricoCrtBolseiroRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.servico.cafold.Conexao;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/convenio")
public class ControllerConvenio {
	@Autowired
	private EmpresaConvenioRepository convenioRepository;
	@Autowired
	private AnoLectivoRepository anoLectivo;
	@Autowired
	private CreditoAlunoRepository creditoAlunoRepository;
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private BorderoRepository borderoRepository;
	@Autowired
	private BancoRepository bancoRepository;
	@Autowired
	private MoedaRepository moedaRepository;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private HistoricoCreditoRepository historicoCreditoRepository;
	@Autowired
	private NumeroGeradoRepository numeroGeradoRepository;
	@Autowired
	private ProgHistoricoCrtBolseiroRepository progHistoricoBolseiroRepository;
	
	@Transactional
	@RequestMapping(value = "/registrarCredito", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscar(@RequestBody RegistroCreditoBolceiros convenio) {
		
		
		ResponseCliente c = new ResponseCliente();
		
			
			System.out.println("Estou a bater aqui");
			
			EmpresaConvenio empresa = this.convenioRepository.findOne(convenio.getEmpresa());
			
			AnoLectivo ano          = this.anoLectivo.findOne(convenio.getAnoLectivo());
			Usuario usuario         = this.usuarioRepository.findByUserName(convenio.getUserName() != null ? convenio.getUserName() : null);
			Moeda moeda             = this.moedaRepository.findOne(3);
			Banco banco             = this.bancoRepository.findOne(convenio.getBanco());
			
			
			Instituicao instituicao = this.instituicaoRepository.findOne(2);

			CreaditoDeAluno credito;
			List<BolseirosListaCliente> bolseiros = convenio.getBolseiros();

			if (bolseiros.isEmpty()) {
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				c.setMensagem("Nenhum Aluno, verificar a programação!");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

			double somaValores = 0;
			for (BolseirosListaCliente o : bolseiros) {
				somaValores += o.getValorAdd();
			}

			String mensagem="";
			Integer codigo=0;
			if (convenio.getValor() == somaValores) {

				Bordero bordero;
				Aluno alunoPego;
				ContaCorrenteAluno conta;
				Double valorAnterior;
				for (BolseirosListaCliente bo : bolseiros) {
					System.out.println("Entrou no for ");
					System.out.println("Ole " + bo.getNumeroDeAluno());
					alunoPego = this.alunoRepository.findByNumeroDeAluno(bo.getNumeroDeAluno());
					conta     = this.contaCorrenteRepository.findByAluno(alunoPego);

					// ACERTA A CONTA CORRENTE DO ALUNO
					valorAnterior = conta.getValor();
					
					conta.setValorAnterior(valorAnterior);
					double valorSomado = bo.getValorAdd() + valorAnterior;
					System.out.println("Valor add " + bo.getValorAdd());
					conta.setValor(valorSomado);
					this.contaCorrenteRepository.save(conta);
					System.out.println("Salvou a conta corrente");

					// REGISTAR O BORDERO NA TABELA BORDERO
					bordero = new Bordero();
					bordero.setNumero(convenio.getNumeroBorderoux());
					bordero.setAluno(alunoPego);
					bordero.setBanco(banco);
					bordero.setDataRegistro(new Date());
					bordero.setDataDeposito(convenio.getDataDeposito());
					bordero.setValor(bo.getValorAdd());
					bordero.setMoeda(moeda);
					// SALVAR BORDEREUX
					Bordero borderoSalvo = this.borderoRepository.save(bordero);
					System.out.println("Salvou o bordero");

					// SALVA O HISTORICO DE ALUNO POR EMPRESA DOS CREDORA
					credito = new CreaditoDeAluno();
					credito.setAluno(alunoPego);
					credito.setNumeroDeAluno(Integer.parseInt(alunoPego.getNumeroDeAluno()));
					credito.setEmpresa(empresa);
					credito.setDataRegistro(new Date());
					credito.setAnoLectivo(ano);
					credito.setValor(bo.getValorAdd());
					credito.setDataDeposito(convenio.getDataDeposito());
					credito.setBordero(convenio.getNumeroBorderoux());
					credito.setMoeda(moeda);
					credito.setBanco(banco);
					
					this.creditoAlunoRepository.save(credito);
					
					System.out.println("Salvou o credito ");

					// EXECUTA HISTORICO DE LANÇAMENTO
					HistoricoCredito hCredito = new HistoricoCredito();

					hCredito.setAluno(alunoPego);
					hCredito.setNumeroDeAluno(Integer.parseInt(alunoPego.getNumeroDeAluno()));
					hCredito.setAnoLectivo(ano);
					hCredito.setBanco(banco);
					hCredito.setBordero(borderoSalvo);
					hCredito.setDataDepositoExterno(convenio.getDataDeposito());
					hCredito.setBorderoExterno(convenio.getNumeroBorderoux());

					float valor = (float) bo.getValorAdd();
					hCredito.setValorDeposito(valor);
					hCredito.setMoeda(banco.getMoeda());
					if(usuario==null)
						usuario=this.usuarioRepository.findOne(45);
					hCredito.setUsuarioEmitiu(usuario);
					
					System.out.println("Salvou o usuário ");

					hCredito.setDataRegisto(new Date());
					hCredito.setInstituicao(instituicao);
					hCredito.setBordero(borderoSalvo);
					//GERAR O CÓDIGO DO BORDEREUX INTERNO
					//-------------------------------------------------------------------------------------------------
					String definitivo         = "";
					Integer lectivo           = ano.getAnoLectivo();
					NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(5);
					Long proximoNumeroInteiro = numeroGerado.getProximoNumero();
					
					
					
					//metódo gerar numero de guia chamado
					
					
					definitivo = gerarNumeroBordereuxInterno(definitivo, lectivo, proximoNumeroInteiro);
					
					
					HistoricoCredito borderoExistente = this.historicoCreditoRepository.findByBorderoInterno(definitivo);
					if(borderoExistente!=null) {	
						do {
							proximoNumeroInteiro++;
							//metódo gerar numero de guia chamado
							definitivo = gerarNumeroBordereuxInterno(definitivo, lectivo, proximoNumeroInteiro);
							borderoExistente = this.historicoCreditoRepository.findByBorderoInterno(definitivo);
						}while(borderoExistente!=null);
					}
					
					//setar o valor da guia
					hCredito.setBorderoInterno(definitivo);
					
					//atualizador os dados da ultima guia e a proxima guia
					numeroGerado.setUltimoNumero(proximoNumeroInteiro);
					numeroGerado.setProximoNumero(proximoNumeroInteiro+1);		
					this.numeroGeradoRepository.save(numeroGerado);
					
					//--------------------------------------------------------------------------------------------------

					codigo=0;
					mensagem="Crédito registado com sucesso.";
					this.historicoCreditoRepository.save(hCredito);
					System.out.println("Não chegou no ultimo ponto ");
					
				}
				
				
			}else {
				codigo=3;
				mensagem="O total distribuído deve ser igual ao valor depósito";				
			}

			c.setCodigo(ResponseCode.values()[codigo].getDescricao());
			c.setMensagem(mensagem);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/todas", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> empresasConvenio() {
		Iterable<EmpresaConvenio> empresaConvenio = this.convenioRepository.findAll();
		if(empresaConvenio == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhum registo de empresa encontrado.");
		return ObjectoDeRetornoParcial.MensagemDeRetorno(empresaConvenio, 0,null);	
	}
	
	
	@RequestMapping(value = "/historicoCreditoEmpresas", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> historicoCreditoEmpresas(@RequestBody ContaCorrenteBuscar credito){
				
		CreditosResumoCliente historico=null;
		ArrayList<CreditosResumoCliente> hsGeral=new ArrayList<CreditosResumoCliente>();
		
		List<ProgHistoricoCrtBolseiro> creditos = this.progHistoricoBolseiroRepository.findByAnoLectivoId(credito.getAnoLectivo());
		for (ProgHistoricoCrtBolseiro o : creditos) {	
			historico=new CreditosResumoCliente();
			
			historico.setId(o.getId());
			historico.setEmpresa(o.getEmpresa());
			historico.setAnoLectivo(o.getAnoLectivo());
			historico.setDataRegisto(o.getDataRegistro());
			historico.setDataDepositoExterno(o.getDataDeposito());
			historico.setValorDeposito(o.getValorDepositado());
			historico.setBanco(o.getBanco());
			historico.setMoeda(o.getMoeda());
			historico.setTotalAlunos(o.getTotalAlunos());
			historico.setBorderoExterno(o.getBordero());
			historico.setBorderoInterno(o.getBordero());
			hsGeral.add(historico);
		}
		return ObjectoDeRetornoParcial.MensagemDeRetorno(hsGeral, 0, null);
	}
	
	
	private String gerarNumeroBordereuxInterno(String definitivo, Integer lectivo, Long proximoNumeroInteiro) {
		if(proximoNumeroInteiro>=1      && proximoNumeroInteiro<=9)
			definitivo="B"+lectivo+"00000"  +proximoNumeroInteiro;
		if(proximoNumeroInteiro>=10     && proximoNumeroInteiro<=99)
			definitivo="B"+lectivo+"0000"   +proximoNumeroInteiro;
		if(proximoNumeroInteiro>=100    && proximoNumeroInteiro<=999)
			definitivo="B"+lectivo+"000"    +proximoNumeroInteiro;
		if(proximoNumeroInteiro>=1000   && proximoNumeroInteiro<=9999)
			definitivo="B"+lectivo+"00"     +proximoNumeroInteiro;
		if(proximoNumeroInteiro>=10000  && proximoNumeroInteiro<=99999)
			definitivo="B"+lectivo+"0"      +proximoNumeroInteiro;
		if(proximoNumeroInteiro>=100000 && proximoNumeroInteiro<=999999)
			definitivo="B"+lectivo          +proximoNumeroInteiro.toString();
		return definitivo;
	}
	
	public static Connection conectar() {
		Conexao con = new Conexao();
		return con.getConn();
	}
	
	
	@GetMapping("/credito/alunos/empresa")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioBolseiros(@RequestParam Integer anoLectivo, @RequestParam String empresa, @RequestParam String borderoux) throws Exception {
		
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("ano", anoLectivo);
		paramets.put("empresa", empresa);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Registo_Credito_Bolseiro.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		
		byte[] relatrio = JasperExportManager.exportReportToPdf(jasperPrint);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	
}
package ao.co.intellectus.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.ContaCorenteCliente;
import ao.co.intellectus.DTO.ContaCorrenteBuscar;
import ao.co.intellectus.DTO.HistoricoCreditoAnular;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Bordero;
import ao.co.intellectus.model.ContaCorrenteAluno;
import ao.co.intellectus.model.ContaCorrenteCandidato;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.HistoricoCredito;
import ao.co.intellectus.model.NotaCredito;
import ao.co.intellectus.model.NumeroGerado;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.enumeracao.TipoDoc;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.BorderoRepository;
import ao.co.intellectus.repository.ContaCorrenteCandidatoRepository;
import ao.co.intellectus.repository.ContaCorrenteRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.HistoricoCreditoRepository;
import ao.co.intellectus.repository.NotaCreditoRepository;
import ao.co.intellectus.repository.NumeroGeradoRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.servico.GeradorDeArquivo;
import ao.co.intellectus.servico.GerarNumeroDocumento;

@RestController
@RequestMapping("/contacorrente")
public class ControllerContaCorrente {
	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private HistoricoCreditoRepository historicoCreditoRepository;
	@Autowired
	private BorderoRepository borderoRepository;
	@Autowired
	private ContaCorrenteCandidatoRepository contaCandidatoRepository;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private GuiaPagamentoRepository guiaPagamentoRepo;
	@Autowired
	private NumeroGeradoRepository numeroGeradoRepository;
	@Autowired
	private NotaCreditoRepository notaCreditoRepo;
	@Autowired
	private GerarNumeroDocumento gerarNumeroDocService;
	@Autowired
	private GeradorDeArquivo gerarDocService;
	@Autowired
	private UsuarioRepository usuarioRepo;

	// contacorrente/buscarConta
	@RequestMapping(value = "/buscarConta", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscar(@RequestBody ContaCorrenteBuscar pego) {
		ResponseCliente c = new ResponseCliente();

		if (pego.isTipo()) {
			AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(pego.getAnoLectivo());

			
			ContaCorrenteCandidato contaCorrente = this.contaCandidatoRepository.findByAnoLectivoAndNumeroDeCandidato(anoLectivo, pego.getBuscarId().toString());

			ContaCorenteCliente cc = new ContaCorenteCliente();
			if (contaCorrente != null) {
				cc.setAnoLectivo(contaCorrente.getAnoLectivo().getAnoLectivo());
				cc.setCodigoAluno(contaCorrente.getCandidato().getId());
				cc.setNumeroAluno(contaCorrente.getCandidato().getNumeroCandidato());
				cc.setInstituicao(contaCorrente.getInstituicao().getDescricao());
				cc.setId(contaCorrente.getId());
				cc.setNome(contaCorrente.getCandidato().getNome());
				BeanUtils.copyProperties(contaCorrente, cc, "id", "cursoID", "DescricaoCurso");
			}

			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setResultado(cc);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);

		} else {
			Aluno aluno = this.alunoRepository.findByNumeroDeAluno(pego.getBuscarId().toString());

			ContaCorrenteAluno contaCorrente = this.contaCorrenteRepository.findByAluno(aluno);
			ContaCorenteCliente cc = new ContaCorenteCliente();
			cc.setAnoLectivo(contaCorrente.getAnoLectivo().getAnoLectivo());
			cc.setCodigoAluno(contaCorrente.getAluno().getId());
			cc.setNumeroAluno(Integer.parseInt(contaCorrente.getAluno().getNumeroDeAluno()));
			cc.setInstituicao(contaCorrente.getInstituicao().getDescricao());
			cc.setId(contaCorrente.getId());
			cc.setNome(contaCorrente.getAluno().getNome());
			BeanUtils.copyProperties(contaCorrente, cc, "id", "cursoID", "DescricaoCurso");
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setResultado(cc);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);

		}
	}

	@Transactional
	@RequestMapping(value = "/anular", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> anularHistorico(@RequestBody HistoricoCreditoAnular hc) {
		ResponseCliente c = new ResponseCliente();
		
		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);
		
		Usuario usuario = this.usuarioRepo.findByUserName(hc.getUserName() != null ? hc.getUserName() : null);

		HistoricoCredito hCredito = this.historicoCreditoRepository.findOne(hc.getId());
		Guia guia = guiaPagamentoRepo.findByBordero(hCredito.getBorderoExterno());
		
		ContaCorrenteAluno contaCorrente = this.contaCorrenteRepository.findByAluno(hCredito.getAluno());

		if (!hCredito.isAnulado()) {
			if (contaCorrente.getValor() >= hCredito.getValorDeposito()) {
				double valorContaMenosCredito = contaCorrente.getValor() - hCredito.getValorDeposito();

				// ACERTA A CONTA CORRENTE
				contaCorrente.setValor(valorContaMenosCredito);
				contaCorrente.setDataMovimento(new Date());
				this.contaCorrenteRepository.save(contaCorrente);

				// ACERTA O HISTÓRICO
				// hCredito.setBorderoExterno(borderoExterno);
				hCredito.setAnulado(true);
				hCredito.setMotivoDeAnulacao(hc.getMotivo());
				hCredito.setDataAnulado(new Date());
				this.historicoCreditoRepository.save(hCredito);

				// invalida o bordero anulado.
				Bordero bordero = this.borderoRepository.findOne(hCredito.getBordero().getId());
				if (bordero != null) {
					bordero.setValor(0);
					bordero.setNumero(null);
					this.borderoRepository.save(bordero);
				}
				
				String numero ="";
				
				AnoLectivo anoActivo = anoLectivoRepository.buscarPorEstado();
				String ano = String.valueOf(anoActivo.getAnoLectivo());
				String anoSubstring = ano.substring(2,4);
				Integer anoLimpo = Integer.parseInt(anoSubstring);
				
				NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(2006);
				Long proximoNumero = numeroGerado.getProximoNumero();
				
				numero = gerarNumeroDocService.gerarNumeroNotaCredito(numero, anoLimpo, proximoNumero);
				NotaCredito notaExiste = this.notaCreditoRepo.buscarNumeroNotaCredito(numero);
				if (notaExiste != null) {
					do {
						proximoNumero++;
						
						numero = gerarNumeroDocService.gerarNumeroNotaCredito(numero, anoLimpo, proximoNumero);
						notaExiste = this.notaCreditoRepo.buscarNumeroNotaCredito(numero);
					} while (notaExiste != null);
				}
				
				guia.setAnulada(true);
				guia.setDataAnulada(new Date());
				guia.setDataAnulacaoRecibo(new Date());
				guia.setMotivoAnulacaoRecibo(hc.getMotivo());
				guia.setUsuarioAnulou(usuario);
					
				Guia guiaCand = this.guiaPagamentoRepo.save(guia);
				
				NotaCredito notaCredito = new NotaCredito();
				
				notaCredito.setIdGuia(guiaCand);
				notaCredito.setNumeroNotaCredito(numero);
				notaCredito.setTipoDoc(TipoDoc.FACTURA_RECIBO);
				notaCredito.setDataEmissao(new Date());
				notaCredito.setUsuarioEmitiu(usuario);
				notaCredito.setDataSistema(dataSistema);
				notaCredito.setAlteracao(false);
				
				NotaCredito notaCreditoSalva = notaCreditoRepo.save(notaCredito);
				
				this.gerarDocService.gerarFileNotaCredito(notaCreditoSalva);
				
				numeroGerado.setUltimoNumero(proximoNumero);
				numeroGerado.setProximoNumero(proximoNumero + 1);
				this.numeroGeradoRepository.save(numeroGerado);
				
				c.setResultado(notaCreditoSalva.getNumeroNotaCredito());
				c.setCodigo(200);
				c.setMensagem("Lançamento anulado com sucesso.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			} else {
				c.setCodigo(300);
				c.setMensagem("Valor da conta é insuficiente.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
		} else {
			c.setCodigo(300);
			c.setMensagem("O pedido já havia sido anulado");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}
}

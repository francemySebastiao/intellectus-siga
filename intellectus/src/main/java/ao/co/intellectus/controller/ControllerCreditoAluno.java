package ao.co.intellectus.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.AlunoId;
import ao.co.intellectus.DTO.PassagemDeCredito;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Banco;
import ao.co.intellectus.model.ContaCorrenteAluno;
import ao.co.intellectus.model.HistoricoCredito;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.Moeda;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.BancoRepository;
import ao.co.intellectus.repository.ContaCorrenteRepository;
import ao.co.intellectus.repository.HistoricoCreditoRepository;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.repository.MoedaRepository;

@RestController
@RequestMapping("/creaditoAluno")

public class ControllerCreditoAluno {

	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private MoedaRepository moedaRepository;
	@Autowired
	private HistoricoCreditoRepository historicoCreditoRepository;
	@Autowired
	private BancoRepository bancoReposity;

	@RequestMapping(value = "/buscarCreditoPorAluno", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscar(@RequestBody AlunoId alunoBusca) {
		ResponseCliente c = new ResponseCliente();

		try {
			Aluno alunoPego = this.alunoRepository.findByNumeroDeAluno(alunoBusca.getBuscarId().toString());
			ContaCorrenteAluno aluno = this.contaCorrenteRepository.findByAluno(alunoPego);
			PassagemDeCredito passagem = new PassagemDeCredito();

			passagem.setAlunoOrigemId(Integer.parseInt(aluno.getAluno().getNumeroDeAluno()));

			passagem.setAlunoOrigemNome(aluno.getAluno().getNome());
			passagem.setValorOrigem(aluno.getValor());

			c.setResultado(passagem);
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} catch (Exception ex) {
			ex.getMessage();
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} 
	}


	//FALTA FAZER HISTÓRICO DE SAIDA DA ORIGEM E DE ENTRADA PARA O DISTINO
	@RequestMapping(value = "/passagemDeCredito", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> passarCredito(@RequestBody PassagemDeCredito passagem) {
		ResponseCliente c = new ResponseCliente();
		
		if(passagem.getAlunoOrigemId().equals(passagem.getAlunoDestinoId())) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Origem não pode ser igual ao destino.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		try {
			if (passagem.getValorOrigem() >= passagem.getValorAPassar()) {
				// Aluno de origem
				Aluno alunoOrigem = this.alunoRepository.findByNumeroDeAluno(passagem.getAlunoOrigemId().toString());
				// Aluno destino
				Aluno alunoDestino = this.alunoRepository.findByNumeroDeAluno(passagem.getAlunoDestinoId().toString());
				// Créditos da origem
				ContaCorrenteAluno creditoOrigem = this.contaCorrenteRepository.findByAluno(alunoOrigem);
				// Créditos do destino
				ContaCorrenteAluno creditoDestino = this.contaCorrenteRepository.findByAluno(alunoDestino);

				
				
				
				if(creditoOrigem.getValor() - passagem.getValorAPassar()<0) {
					c.setCodigo(ResponseCode.values()[3].getDescricao());
					c.setMensagem("Valor superior a origem.");
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
				
				List<AnoLectivo> anoLectivo = this.anoLectivoRepository.findByStatus(true);
				Instituicao instituicao = this.instituicaoRepository.findOne(2);
				Moeda moeda             = this.moedaRepository.findByStatus(true);
				creditoOrigem.setValorAnterior(creditoOrigem.getValor());
				creditoOrigem.setValor(creditoOrigem.getValor() - passagem.getValorAPassar());
				creditoDestino.setValorAnterior(creditoDestino.getValor());
				creditoDestino.setValor(creditoDestino.getValor() + passagem.getValorAPassar());

				this.contaCorrenteRepository.save(creditoOrigem);
				this.contaCorrenteRepository.save(creditoDestino);
				
				
				//ORIGEM
				float negativo= (float)passagem.getValorAPassar()*-1;
				historicoCreditoSaida(alunoOrigem, alunoDestino, anoLectivo, instituicao, moeda, negativo);				
				
				//DESTINO
				float positivo= (float)passagem.getValorAPassar();
				historicoCreditoEntrada(alunoOrigem, alunoDestino, anoLectivo, instituicao, moeda, positivo);
				
				c.setCodigo(ResponseCode.values()[0].getDescricao());
				c.setMensagem("Passagem de Crédito efetivada com sucesso!");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			} else {
				c.setCodigo(ResponseCode.values()[3].getDescricao());
				c.setMensagem("Valor superior a origem.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
		} catch (Exception ex) {
			ex.getMessage();
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}


	private void historicoCreditoEntrada(Aluno alunoOrigem, Aluno alunoDestino, List<AnoLectivo> anoLectivo,
			Instituicao instituicao, Moeda moeda, float negativo) {
		//FAZER HISTORICO DE SAIDA PARA UM E ENTRADA PARA OUTRO / DE SUBTRAÇÃO
		HistoricoCredito hCredito = new HistoricoCredito();
		// Aluno aluno = guia.getAluno();
		Banco banco = this.bancoReposity.findOne(15);
		hCredito.setAnoLectivo(anoLectivo.get(0));
	    hCredito.setBanco(banco);
		
		
		hCredito.setAluno(alunoOrigem);
		hCredito.setNumeroDeAluno(Integer.parseInt(alunoOrigem.getNumeroDeAluno()));
		
		hCredito.setBorderoInterno("+MOV" + alunoOrigem.getNumeroDeAluno());
		hCredito.setBorderoExterno("+MOV" + alunoOrigem.getNumeroDeAluno());
			
		hCredito.setAluno(alunoDestino);
		hCredito.setNumeroDeAluno(Integer.parseInt(alunoDestino.getNumeroDeAluno()));
		hCredito.setDataDepositoExterno(new Date());
		// hCredito.setBanco("");
		hCredito.setInstituicao(instituicao);
		hCredito.setMoeda(moeda);
		hCredito.setDataRegisto(new Date());
		
		hCredito.setValorDeposito(negativo);
		//hCredito.setUsuarioEmitiu(usuario);
		this.historicoCreditoRepository.save(hCredito);
	}
	
	
	private void historicoCreditoSaida(Aluno alunoOrigem, Aluno alunoDestino, List<AnoLectivo> anoLectivo,
			Instituicao instituicao, Moeda moeda, float negativo) {
		//FAZER HISTORICO DE SAIDA PARA UM E ENTRADA PARA OUTRO / DE SUBTRAÇÃO
		HistoricoCredito hCredito = new HistoricoCredito();
		// Aluno aluno = guia.getAluno();

		Banco banco = this.bancoReposity.findOne(15);
		hCredito.setAnoLectivo(anoLectivo.get(0));
	    hCredito.setBanco(banco);
		
		
		hCredito.setBorderoInterno("-MOV" + alunoDestino.getNumeroDeAluno());
		hCredito.setBorderoExterno("-MOV" + alunoDestino.getNumeroDeAluno());
			
		hCredito.setAluno(alunoOrigem);
		hCredito.setNumeroDeAluno(Integer.parseInt(alunoOrigem.getNumeroDeAluno()));
		hCredito.setDataDepositoExterno(new Date());
		// hCredito.setBanco("");
		hCredito.setInstituicao(instituicao);
		hCredito.setMoeda(moeda);
		hCredito.setDataRegisto(new Date());
		
		hCredito.setValorDeposito(negativo);
		//hCredito.setUsuarioEmitiu(usuario);
		this.historicoCreditoRepository.save(hCredito);
	}
}
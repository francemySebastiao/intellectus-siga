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
import ao.co.intellectus.DTO.AutorizacaoRegistroInscricaoCliente;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.AutorizacaoRegistroInscricao;
import ao.co.intellectus.model.Prova;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.AutorizacaoInscricaoRepository;
import ao.co.intellectus.repository.ProvaRepository;

@RestController
@RequestMapping("/autorizacaoInscricaoExtraOrdinaria")
public class ControllerAutorizacaoInscricao {

	@Autowired
	private AutorizacaoInscricaoRepository autorizacaoInscricaoRepository;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private ProvaRepository provaRepository;

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@RequestBody AutorizacaoRegistroInscricaoCliente Pautorizacao) {
		ResponseCliente c = new ResponseCliente();
		AutorizacaoRegistroInscricao autorizacao = new AutorizacaoRegistroInscricao();

		Prova pv = this.provaRepository.findOne(Pautorizacao.getProva());
		//List<AutorizacaoRegistroInscricao> autorizacoes = this.autorizacaoInscricaoRepository.findByEmCursoAndProva(true, pv);
		
		List<AutorizacaoRegistroInscricao> autorizacoes = this.autorizacaoInscricaoRepository.findByEmCursoAndProvaAndTipoDisciplinaAndGrau(true, pv, Pautorizacao.getTipoDisciplina(),Pautorizacao.getGrau());
		

		
		if(autorizacoes.isEmpty()){
			AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(Pautorizacao.getAnoLectivo());
			Prova prova           = this.provaRepository.findOne(Pautorizacao.getProva());
			BeanUtils.copyProperties(Pautorizacao, autorizacao, "prova", "anoLectivo");
			autorizacao.setProva(prova);
			autorizacao.setAnoLectivo(anoLectivo);
			autorizacao.setEmCurso(true);
			autorizacao.setDataRegistro(new Date());
			
			this.autorizacaoInscricaoRepository.save(autorizacao);
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setMensagem("Autorizações efetivada com sucesso!");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);	
		}else {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("A época já se encontra aberta.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/autorizacoesEmCurso", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> autorizacoesEmCurso() {
		ResponseCliente c = new ResponseCliente();
	
		AutorizacaoCliente autoriza;
		List<AutorizacaoCliente> autorizas=new ArrayList<AutorizacaoCliente>();
		List<AutorizacaoRegistroInscricao> todos = this.autorizacaoInscricaoRepository.findByEmCurso(true);
		for (AutorizacaoRegistroInscricao ar : todos) {
			autoriza       =new AutorizacaoCliente();
			if(ar.getGrau()!= null)
				autoriza.setGrau(ar.getGrau().toString());
			autoriza.setAnoLectivo(ar.getAnoLectivo().getAnoLectivo());
			autoriza.setAnoLectivoDescricao(ar.getAnoLectivo().getAnoLectivoDescricao());
			autoriza.setDataFim(ar.getDataFim());
			autoriza.setDataInicio(ar.getDataInicio());
			autoriza.setProva(ar.getProva().getProva());
			autoriza.setTipoDisciplina(ar.getTipoDisciplina());
			autoriza.setEmCurso(ar.isEmCurso());
			autoriza.setTipoDisciplinaString(ar.getTipoDisciplina().getDescricao());
			
			autorizas.add(autoriza);
		}
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(autorizas);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/autorizacoes", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> autorizacoes() {
		ResponseCliente c = new ResponseCliente();
	
		AutorizacaoCliente autoriza;
		List<AutorizacaoCliente> autorizas=new ArrayList<AutorizacaoCliente>();
		List<AutorizacaoRegistroInscricao> todos = this.autorizacaoInscricaoRepository.findAll();
		for (AutorizacaoRegistroInscricao ar : todos) {
			autoriza       =new AutorizacaoCliente();
			if(ar.getGrau()!= null)
				autoriza.setGrau(ar.getGrau().getDescricao());
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
}
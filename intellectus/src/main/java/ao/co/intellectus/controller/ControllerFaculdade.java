package ao.co.intellectus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.FaculdadeCliente;
import ao.co.intellectus.model.Faculdade;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.FaculdadeRepository;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;

@RestController
@RequestMapping("/faculdade")
public class ControllerFaculdade {
	 
	@Autowired
	private FaculdadeRepository repository;
	@Autowired
	private InstituicaoRepository instituicaoRepository;

	public ControllerFaculdade(FaculdadeRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(value = "/todas", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listarFaculdades() {
		Iterable<Faculdade> todos = this.repository.findAll();
		if(todos == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhum registo de faculdade encontrado");
		return ObjectoDeRetornoParcial.MensagemDeRetorno(todos, 0, null);
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody FaculdadeCliente faculdadeCliente){
		Instituicao instituicao = this.instituicaoRepository.findOne(faculdadeCliente.getInstituicao());
		if(instituicao == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhum registo de instituição encontrado.");
	
		Faculdade faculdade = new Faculdade();
		faculdade.setFaculdade(faculdadeCliente.getFaculdade());
		faculdade.setCargo(faculdadeCliente.getCargo());
		faculdade.setResponsavel(faculdadeCliente.getResponsavel());
		faculdade.setCargo2(faculdadeCliente.getCargo2());
		faculdade.setResponsavel2(faculdadeCliente.getResponsavel2());
		faculdade.setInstituicao(instituicao);
		faculdade.setCoordenadorMestrado(faculdadeCliente.getCoordenadorMestrado());
		faculdade.setCargoMestrado(faculdade.getCargoMestrado());
		faculdade.setTitulo(faculdade.getTitulo());
		this.repository.save(faculdade);
		return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Faculdade salva com sucesso.");
	}
	
	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody FaculdadeCliente faculdadeCliente){
		Instituicao instituicao = this.instituicaoRepository.findOne(faculdadeCliente.getInstituicao());
		if(instituicao == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhum registo de instituição encontrado.");
	
		Faculdade faculdade = this.repository.findOne(faculdadeCliente.getId());
		faculdade.setFaculdade(faculdadeCliente.getFaculdade());
		faculdade.setCargo(faculdadeCliente.getCargo());
		faculdade.setResponsavel(faculdadeCliente.getResponsavel());
		faculdade.setCargo2(faculdadeCliente.getCargo2());
		faculdade.setResponsavel2(faculdadeCliente.getResponsavel2());
		faculdade.setInstituicao(instituicao);
		faculdade.setCoordenadorMestrado(faculdadeCliente.getCoordenadorMestrado());
		faculdade.setCargoMestrado(faculdade.getCargoMestrado());
		faculdade.setTitulo(faculdade.getTitulo());
		this.repository.save(faculdade);
		return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Faculdade actualizada com sucesso.");
	}
	
}
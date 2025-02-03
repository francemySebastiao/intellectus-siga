package ao.co.intellectus.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.Intervalo;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.IntervaloRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;

@RestController
@RequestMapping("/intervalo")
public class ControllerIntervalo {
	@Autowired
	private IntervaloRepository intervaloRepository;
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> alunos() {
	    Iterable<Intervalo> horario =this.intervaloRepository.findByDiaSemana("Segunda-Feira");
		if(horario == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhum aluno Encontrado.");
		return ObjectoDeRetornoParcial.MensagemDeRetorno(horario, 0, null);
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody Intervalo intervalo){
		this.intervaloRepository.save(intervalo);
		return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Intervalo salvo com sucesso.");
	}
	
	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody Intervalo inter){
		Intervalo intervalo = this.intervaloRepository.findOne(inter.getId());
		if(intervalo == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Intervalo não encontrado.");
		BeanUtils.copyProperties(inter, intervalo);
		this.intervaloRepository.save(intervalo);
		return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Intervalo actualizado com sucesso.");
	}
	
}

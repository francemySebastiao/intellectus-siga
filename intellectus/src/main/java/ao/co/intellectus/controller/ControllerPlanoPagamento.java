package ao.co.intellectus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.PlanoPagamento;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.PlanoPagamentoRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;

@RestController
@RequestMapping("/planoPagamento")
public class ControllerPlanoPagamento {
	
	@Autowired
    private PlanoPagamentoRepository planoPagamentoRepository;
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public Iterable<PlanoPagamento> buscarAnoActivo() {
		return planoPagamentoRepository.findAll();
	}
	
	@RequestMapping(value = "/todosPlanos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> todosPlanos() {
		Iterable<PlanoPagamento> todosPlanos = planoPagamentoRepository.findAll();
		if(todosPlanos == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhem registo de planos Encontrado.");
		return ObjectoDeRetornoParcial.MensagemDeRetorno(todosPlanos, 0, null);
	}
	
	@RequestMapping(value= "/salvar", method =RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin( origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody PlanoPagamento pg){
		
		PlanoPagamento planoPagamento = this.planoPagamentoRepository.findByDescricao(pg.getDescricao());
		if(planoPagamento != null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Já existe este plano de pagamento.");
		planoPagamento = new PlanoPagamento();
		planoPagamento.setDescricao(pg.getDescricao());
		planoPagamento.setEstado(pg.isEstado());
		this.planoPagamentoRepository.save(planoPagamento);
		return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Plano de pagamaneto salvo com sucesso.");
	}
	
	
	@RequestMapping(value= "/alterar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> alterar(@RequestBody PlanoPagamento planoPagamento){
		PlanoPagamento plano = this.planoPagamentoRepository.findOne(planoPagamento.getId());
		if(plano != null) {
			plano.setDescricao(planoPagamento.getDescricao());
			plano.setEstado(planoPagamento.isEstado());
			this.planoPagamentoRepository.save(plano);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0,"Plano de pagamento alterado com sucesso.");
		}else {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2,"Registo de plano não encontrado");
		}
	}
	
}

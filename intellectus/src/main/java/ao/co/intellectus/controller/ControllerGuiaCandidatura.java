package ao.co.intellectus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import afu.org.checkerframework.checker.units.qual.C;
import ao.co.intellectus.DTO.DetalhePagamento;
import ao.co.intellectus.DTO.GuiaCandidaturaClinete;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.Grau;
import ao.co.intellectus.model.GuiaCandidatura;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.GuiaCandidaturaRepository;
import ao.co.intellectus.servico.cafold.CandidatoServie;
import ao.co.intellectus.servico.notificacoes.HttpResponse;

@RestController
@RequestMapping("/guiaCandidatura")
public class ControllerGuiaCandidatura {

	@Autowired
	private GuiaCandidaturaRepository repository;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private CandidatoServie candidadoService;
	//@Autowired
	//private EmolumentoRepository emolumentoRepository;
	
	@Autowired
	private HttpResponse httpResponse;

	@RequestMapping(value = "/todas", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public Iterable<GuiaCandidatura> guias() {
		return repository.findAll();
	}

	@RequestMapping(value = "/buscarPorCandidato/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorCandidato(@PathVariable Integer numero) {

		List<AnoLectivo> anoLectivo = this.anoLectivoRepository.findByStatus(true);
		
		Candidato candidato = this.candidadoService.candidato(numero, anoLectivo.get(0));

		GuiaCandidatura guia = this.repository.findByCandidato(candidato);

		GuiaCandidaturaClinete cGuia = new GuiaCandidaturaClinete();
		
		//Emolumento emolumento = this.emolumentoRepository.findByCodigo(2003);
	
		cGuia.setLiquidar(true);
		DetalhePagamento detalhe = new DetalhePagamento();

		// detalhe do pagamento
		if (guia != null) {
	
			detalhe.setCodigo(guia.getNumeroGuia());

			/*if (guia.getCandidato().isCursoAcesso()) {
				detalhe.setDescricao("Inscrição curso de acesso.");
			} else {
				detalhe.setDescricao("Exame de Acesso");
			}*/
			
		    if(guia.getValor()==7000) {
		    detalhe.setDescricao("Exame de acesso");
		    }else if(guia.getValor()==31075) {
		    	detalhe.setDescricao("Candidatura por equivalência");
		    }else if(guia.getValor()==22600) {
		    	detalhe.setDescricao("Curso de acesso");
		    }else  {
		    	detalhe.setDescricao("Guia de candidatura");
		    }
		    double valorGuia = 0;
			detalhe.setMontante(guia.getValor());
			// dados gerais
			if (guia.getInstituicao() != null) {
				cGuia.setNumeroContribuinte(guia.getInstituicao().getUnidadeOrganica());
				cGuia.setInstituicao(guia.getInstituicao().getDescricao());
				cGuia.setGrupoOwner(guia.getInstituicao().getGrupoOwner());
				cGuia.setEndereco(guia.getInstituicao().getEndereco());
			}

			cGuia.setLiquidada(guia.isLiquidada());

			// cGuia.setI
			cGuia.setNome(guia.getCandidato().getNome());
			cGuia.setNumeroAluno(guia.getCandidato().getId());
			if(guia.getCandidato().getCurso().getGrau()== Grau.MESTRADO) {
				cGuia.setMontanteTotal(valorGuia);	
			}
			cGuia.setIdGuia(guia.getId());
			cGuia.setMontanteTotal(guia.getValor());
			cGuia.setDataVencimento(guia.getDataVencimento());
			cGuia.setEmitidoPor("Ernesto Tadeu Tchiteculo Sambongo");
			cGuia.setDetalhesPagamento(detalhe);
			cGuia.setDataEmissao(guia.getDataEmicao());
			cGuia.setNumeroGuia(guia.getNumeroGuia());
			cGuia.setNumeroFacturaRecibo(guia.getNumeroFacturaRecibo());
			cGuia.setAnoLectivo(guia.getAnoLectivo().getAnoLectivo());
			cGuia.setAnoLectivoDescricao(guia.getAnoLectivo().getAnoLectivoDescricao());
			cGuia.setGrau(guia.getCandidato().getCurso().getGrau().getDescricao());
		}
		return this.httpResponse.MensagemDeRetorno(0, cGuia);
	}

}
package ao.co.intellectus.servico.cafold;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.EmolumentoCliente;
import ao.co.intellectus.DTO.TodasGuiasCliente;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.repository.GuiaPagamentoRepository;

@Service
public class TodasGuiasService {
	
	@Autowired
	private GuiaPagamentoRepository guiaRepository;
	@Autowired
	private EmolumentoService emolumentoService;

	public List<TodasGuiasCliente> todasGuias(String numeroDoAluno){
		List<TodasGuiasCliente> todasGuias = new ArrayList<TodasGuiasCliente>();
		//CUSTO DE TEMPO
		for (Guia guia : this.guiaRepository.findByAlunoNumeroDeAlunoAndAnuladaOrderById(numeroDoAluno,false)) {
			TodasGuiasCliente umDasGuias = new TodasGuiasCliente();
			umDasGuias.setId(guia.getId());
			umDasGuias.setAnoLectivo(guia.getAnoLectivo().getAnoLectivo());
			umDasGuias.setDataEmissao(guia.getDataEmicao());
			umDasGuias.setDataVencimento(guia.getDataVencimento());
			// guiaNaoLiquidada.setMoeda(guia.getMoeda().getDesignacao());
			umDasGuias.setNumeroGuia(guia.getNumeroGuia());
			umDasGuias.setValor(guia.getValor());
			umDasGuias.setLiquidada(guia.isLiquidada());
			//CUSTO DE TEMPO
			List<EmolumentoCliente> emolumentos = this.emolumentoService.emolumentos(guia, guia.getAluno().getCurso(), guia.getAnoLectivo());
			umDasGuias.setEmolumento(emolumentos);
			todasGuias.add(umDasGuias);				
		}	
		return todasGuias;
	}

}

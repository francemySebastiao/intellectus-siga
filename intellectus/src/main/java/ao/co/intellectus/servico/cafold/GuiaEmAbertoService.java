package ao.co.intellectus.servico.cafold;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaEmAbertoCliente;
import ao.co.intellectus.repository.GuiaPagamentoRepository;

@Service
public class GuiaEmAbertoService {
	
	@Autowired
	private GuiaPagamentoRepository guiaRepository;
	
	public List<GuiaEmAbertoCliente> guiasEmAbero(String numeroDoAluno){
		List<Guia> guias = this.guiaRepository.findByAlunoNumeroDeAlunoAndLiquidadaAndAnulada(numeroDoAluno, false, false);
		List<GuiaEmAbertoCliente> guiasNaoLiquidadas = new ArrayList<GuiaEmAbertoCliente>();
		for (Guia guia : guias) {
			GuiaEmAbertoCliente guiaNaoLiquidada = new GuiaEmAbertoCliente();
			guiaNaoLiquidada.setId(guia.getId());
			guiaNaoLiquidada.setAnoLectivo(guia.getAnoLectivo().getAnoLectivo());
			guiaNaoLiquidada.setDataEmissao(guia.getDataEmicao());
			guiaNaoLiquidada.setDataVencimento(guia.getDataVencimento());
			// guiaNaoLiquidada.setMoeda(guia.getMoeda().getDesignacao());
			guiaNaoLiquidada.setNumeroGuia(guia.getNumeroGuia());
			guiaNaoLiquidada.setValor(guia.getValor());
			guiasNaoLiquidadas.add(guiaNaoLiquidada);	
		}	
		return guiasNaoLiquidadas;
	}
	

}

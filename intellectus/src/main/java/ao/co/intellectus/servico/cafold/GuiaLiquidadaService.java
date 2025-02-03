package ao.co.intellectus.servico.cafold;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.DataFiltro;
import ao.co.intellectus.DTO.GuiaLiquidadaCliente;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiasLiquidadas;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.GuiasLiquidadasRepository;

@Service
public class GuiaLiquidadaService {
	@Autowired
	private GuiasLiquidadasRepository guiasLiquidadasRepository;
	@Autowired
	private GuiaPagamentoRepository guiaRepository;
	@Autowired
	private DataService dataServico;

	public List<GuiaLiquidadaCliente> guiasLuiquidadas(String numeroDoAluno){
		List<Guia> recidos = this.guiaRepository.findByAlunoNumeroDeAlunoAndLiquidadaAndAnulada(numeroDoAluno, true, false);
		List<GuiaLiquidadaCliente> guiasLiquidadas = new ArrayList<GuiaLiquidadaCliente>();
		for (Guia guia : recidos) {
			GuiaLiquidadaCliente liquidada = new GuiaLiquidadaCliente();
			liquidada.setId(guia.getId());
			liquidada.setAnoLectivo(guia.getAnoLectivo().getAnoLectivo());
			liquidada.setDataEmissao(guia.getDataEmicao());
			liquidada.setDataLiquidada(guia.getDataLiquidacao());
			// guiaNaoLiquidada.setMoeda(guia.getMoeda().getDesignacao());
			liquidada.setNumeroGuia(guia.getNumeroGuia());
			liquidada.setValor(guia.getValor());
			guiasLiquidadas.add(liquidada);		
		}	
		return guiasLiquidadas;
	}
	
	public List<GuiasLiquidadas> listarPorDataDePara(String de, String para) throws ParseException {
		DataFiltro data =  this.dataServico.stringParaData(de, para);
		return this.guiasLiquidadasRepository.findByDataLiquidacaoBetween(data.getDe(), data.getPara());
	}

}

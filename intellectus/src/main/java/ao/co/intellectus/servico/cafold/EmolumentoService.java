package ao.co.intellectus.servico.cafold;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.EmolumentoCliente;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaPagamentoHistorico;
import ao.co.intellectus.repository.EmolumentoRepository;
import ao.co.intellectus.repository.HistoricoGuiaPagamentoRepository;

@Service
public class EmolumentoService {
	
	@Autowired
	private EmolumentoRepository emolumentos;
	@Autowired
	private HistoricoGuiaPagamentoRepository historico;
	@Autowired private HistoricoGuiaPagamentoRepository historicoGuiaPagamentoRepository;
	
		
	public Emolumento emolumento(GuiaPagamentoHistorico historico) {
		historico = (historico == null) ? null: historico;
		return (historico == null) ? new Emolumento() : this.emolumentos.findOne(historico.getEmolumento().getId());
	}
	
	public List<Emolumento> emolumentos(Guia guia){
		List<Emolumento> emolumentos = new ArrayList<Emolumento>();
		for(GuiaPagamentoHistorico historicos: this.historico.findByGuia(guia)) {
			Emolumento emolumento = this.emolumento(historicos);
			emolumentos.add(emolumento);				
		}
		return emolumentos;
	}
	
	public List<EmolumentoCliente> emolumentos(Guia guia, Curso curso, AnoLectivo anoLectivo){
		List<EmolumentoCliente> emolumentos = new ArrayList<>();
		for(Emolumento emolumento : this.emolumentos(guia)) {
			EmolumentoCliente emolumentoCliente = new EmolumentoCliente();
			emolumentoCliente.setId(emolumento.getId());
			emolumentoCliente.setEmolumento(emolumento.getEmolumento());
			Double valor = this.valorEmolumento(anoLectivo, emolumento, guia);
			emolumentoCliente.setValor(valor);
			emolumentos.add(emolumentoCliente);
		}
		return emolumentos;
	}
	
	private Double valorEmolumento(AnoLectivo anoLectivo,Emolumento emolumento,Guia guia) {
		GuiaPagamentoHistorico  historico= this.historicoGuiaPagamentoRepository.findByAnoLectivoAndEmolumentoAndGuia(anoLectivo, emolumento, guia);
		return (historico ==  null) ? null: Double.valueOf(historico.getValor());
	}
		
}

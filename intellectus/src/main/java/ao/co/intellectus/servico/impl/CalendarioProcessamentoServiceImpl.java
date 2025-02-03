package ao.co.intellectus.servico.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.CalendarioProcessamentoDTO;
import ao.co.intellectus.DTO.CalendarioProcessamentoForm;
import ao.co.intellectus.model.CalendarioProcessamento;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.CalendarioProcessamentoRepository;
import ao.co.intellectus.servico.CalendarioProcessamentoService;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;
import ao.co.intellectus.util.Utils;

@Service
public class CalendarioProcessamentoServiceImpl implements CalendarioProcessamentoService{
	@Autowired
	private CalendarioProcessamentoRepository calendarioProcessamentoRepository;
	
	@Override
	public ResponseEntity<ResponseCliente> salvar(CalendarioProcessamentoForm calendarioDTO) {
		CalendarioProcessamento calendario = this.calendarioProcessamentoRepository.findOne(calendarioDTO.getId());
		if(calendario!=null) {
			calendario.setDataProcessamento(calendarioDTO.getDataProcessamento());
			calendario.setDataVencimento(calendarioDTO.getDataVencimento());
			this.calendarioProcessamentoRepository.save(calendario);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(0, "Calendário salvo com sucesso.");	
		}
		return ObjectoDeRetornoParcial.MensagemDeRetorno(2, "Verifique o os dados enviados.");	
	}

	@Override
	public ResponseEntity<ResponseCliente> buscarTodos() {
	Iterable<CalendarioProcessamento> calendarios = this.calendarioProcessamentoRepository.findAll();
		
		List<CalendarioProcessamentoDTO> calendariosDTO=new ArrayList<CalendarioProcessamentoDTO>();
		CalendarioProcessamentoDTO calendarioDTO;
		for (CalendarioProcessamento candario : calendarios) {
			calendarioDTO=new CalendarioProcessamentoDTO();
			Utils.copyObjecto(candario, calendarioDTO);			
			calendarioDTO.setAnoLectivo(candario.getAnoLectivo().getAnoLectivo());
			calendarioDTO.setPropinaAnoCompleto(candario.getEmAp().getEmolumento());
			calendarioDTO.setProprinaPorDisciplina(candario.getEmolumentoPd().getEmolumento());
			calendariosDTO.add(calendarioDTO);
		}
		return ObjectoDeRetornoParcial.MensagemDeRetorno(calendariosDTO, calendariosDTO.isEmpty() ? 2:0,calendariosDTO.isEmpty() ?  "Nenhum registo de calendário encontrado.":"Operação realizada com sucesso!");
	}
}

package ao.co.intellectus.servico;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.CalendarioProcessamentoForm;
import ao.co.intellectus.model.reponse.ResponseCliente;

@Service
public interface CalendarioProcessamentoService {
	public ResponseEntity<ResponseCliente> salvar(CalendarioProcessamentoForm calendarioDTO);
	public ResponseEntity<ResponseCliente> buscarTodos();
}

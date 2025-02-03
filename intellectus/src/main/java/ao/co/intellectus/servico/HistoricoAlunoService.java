package ao.co.intellectus.servico;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.reponse.ResponseCliente;

@Service
public interface HistoricoAlunoService {
	public ResponseEntity<ResponseCliente> validarNotasPorTurma();
}

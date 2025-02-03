package ao.co.intellectus.servico;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import ao.co.intellectus.DTO.AlunoInscricaoExtraordinaria;
import ao.co.intellectus.model.reponse.ResponseCliente;
@Component
public interface InscricaoExtraOrdinariaService {
	public ResponseEntity<ResponseCliente> salvar(AlunoInscricaoExtraordinaria efetuarMatricula);
	public ResponseEntity<ResponseCliente> buscaAlunoDisciplina(String numero);
}

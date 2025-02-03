package ao.co.intellectus.servico.cafold;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.CertificadoIntermedio;
import ao.co.intellectus.model.reponse.ResponseCliente;

@Service
public class DeclaracoesService {

	public ResponseEntity<ResponseCliente> validarDiciplinasPorAno(List<CertificadoIntermedio> disciplinas,Integer ano) {
		for (CertificadoIntermedio disciplina : disciplinas) {
			if ((disciplina.getAnoCurricular() == ano) && (disciplina.isValidacao())) {
				return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Existe disciplina não concluída");
			}
		}
		return ObjectoDeRetornoParcial.MensagemDeRetorno(disciplinas, 0, null);
	}

}

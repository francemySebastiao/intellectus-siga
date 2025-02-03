package ao.co.intellectus.servico.cafold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.RegistoCertificado;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.RegistoCertificadoRepository;

@Service
public class RegistoCertificadoService {

	@Autowired
	private RegistoCertificadoRepository registosRepository;

	public ResponseEntity<ResponseCliente> salvar(RegistoCertificado registoCertificado) {
		if (registoCertificado.getNumeroCertificado() == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(registoCertificado.getNumeroCertificado(), 2, "Introduza o número do Cerficado.");
		} else if (this.isCerticadoRegistado(registoCertificado.getNumeroCertificado())) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(registoCertificado.getNumeroCertificado(), 2, "Já existe registo deste Cerficado.");
		} else {
			RegistoCertificado alunoRegistado = this.alunoRegistado(registoCertificado.getAluno());
			if (alunoRegistado != null) {
				alunoRegistado.setDataRegisto(registoCertificado.getDataRegisto());
				alunoRegistado.setNumeroCertificado(registoCertificado.getNumeroCertificado());
				this.registosRepository.save(alunoRegistado);
			} else {
				this.registosRepository.save(registoCertificado);
			}
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Certificado registado com sucussso.");
		}
	}

	private boolean isCerticadoRegistado(String numeroCertificado) {
		RegistoCertificado certificado = registosRepository.findByNumeroCertificado(numeroCertificado);
		return (certificado != null);
	}

	private RegistoCertificado alunoRegistado(Aluno aluno) {
		RegistoCertificado alunoRegistado = registosRepository.findByAluno(aluno);
		return alunoRegistado;
	}

}

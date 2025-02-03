package ao.co.intellectus.servico.cafold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.servico.exception.InstituicaoNaoEncontradaException;

@Service
public class InstituicaoService {

	@Autowired
	private InstituicaoRepository instituicaoRepository;
	
	
	public Instituicao instituicao(Integer codigo) {
		Instituicao instituicao = this.instituicaoRepository.findOne(codigo);
		if(instituicao != null)
			return instituicao;
		throw new InstituicaoNaoEncontradaException("Erro ao validar a instituicao.");
	}
	
}

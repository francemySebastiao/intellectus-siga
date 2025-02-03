package ao.co.intellectus.servico.cafold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.Pais;
import ao.co.intellectus.repository.PaisRepository;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;

@Service
public class NacionalidadeService {

	@Autowired
	private PaisRepository paisRepository;
	
	public Pais nacionalidade(Integer codigo) {
		Pais pais = codigo==null ?null: this.paisRepository.findOne(codigo);
		if(pais  == null)
			throw new RegistoNaoEncontradoException("Registo de nacionalidade não foi encontrada");
		return pais;
	}
	
}

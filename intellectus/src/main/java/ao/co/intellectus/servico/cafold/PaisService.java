package ao.co.intellectus.servico.cafold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.Pais;
import ao.co.intellectus.repository.PaisRepository;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;

@Service
public class PaisService {
	
	@Autowired
	private PaisRepository paisRepository;
	
	public Pais pais(Integer id) {
		Pais pais = this.paisRepository.findOne(id);
		if(pais == null)
			throw new RegistoNaoEncontradoException("Nenhum registo de pais encontrado.");
		return pais;
	}

}

package ao.co.intellectus.servico.cafold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.Municipio;
import ao.co.intellectus.repository.MuniciopioRepository;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;

@Service
public class MunicipioService {
	
	@Autowired
	private MuniciopioRepository municiopioRepository;
	
	public Municipio municipio(Integer id) {
		Municipio municipio = this.municiopioRepository.findOne(id);
		if(municipio  == null)
			throw new RegistoNaoEncontradoException("Nenhum registo de municipio encontrado.");
		return municipio;
		
	}

}

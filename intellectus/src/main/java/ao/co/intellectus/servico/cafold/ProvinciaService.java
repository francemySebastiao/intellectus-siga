package ao.co.intellectus.servico.cafold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.Provincia;
import ao.co.intellectus.repository.ProvinciaRepository;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;

@Service
public class ProvinciaService {
	
	@Autowired
	private ProvinciaRepository provinciaRepository;
	
	public Provincia provincia(Integer id) {
		Provincia provincia = this.provinciaRepository.findOne(id);
		if(provincia  == null)
			throw new RegistoNaoEncontradoException("Nenhum registo de provincia encontrado.");
		return provincia;
	}

}

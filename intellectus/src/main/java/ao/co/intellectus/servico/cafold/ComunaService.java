package ao.co.intellectus.servico.cafold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.Comuna;
import ao.co.intellectus.repository.ComunaRepository;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;

@Service
public class ComunaService {

	@Autowired
	private ComunaRepository comunaRepository;
	
	public Comuna comuna(Integer id) {
		Comuna comuna = this.comunaRepository.findOne(id);
		if(comuna == null)
			throw new RegistoNaoEncontradoException("Nenhum registo de comuna encontrada.");
		return comuna;
	}
}

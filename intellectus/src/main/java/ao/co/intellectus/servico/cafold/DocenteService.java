package ao.co.intellectus.servico.cafold;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.Docente;
import ao.co.intellectus.repository.DocenteRepository;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;

@Service
public class DocenteService {
	
	@Autowired
	private DocenteRepository docenteRepository;
	@SuppressWarnings("unused")
	@Autowired
	private BiometricoService biometricoService;
	
	public Docente docente(Integer id) {
		return this.docenteRepository.findOne(id);
	}
	
	public Docente docentePorEmailInstitucional(String email) {
		return (email == null) ? null: this.docenteRepository.findByEmailInstitucional(email);
	}
	
	public Docente docenctePorEmailAndDataDeNascimento(String email, Date data) {
		Optional<Docente> docente = this.docenteRepository.findByEmailInstitucionalAndDataDeNascimento(email, data);
		if(docente.isPresent()) 
			return docente.get();
		throw new RegistoNaoEncontradoException("Registo de docente não encontrado.");
	}
		
}


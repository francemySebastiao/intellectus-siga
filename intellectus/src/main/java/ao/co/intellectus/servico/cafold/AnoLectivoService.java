package ao.co.intellectus.servico.cafold;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;

@Service
public class AnoLectivoService {
	
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	
	public List<AnoLectivo> anosLectivos(boolean activado){
		return this.anoLectivoRepository.findByStatus(activado);
	}
	
	public AnoLectivo anoLectivo(){
		return this.anoLectivoRepository.findByStatus(true).get(0);
	}
	
	public AnoLectivo anoLectivo(Integer id) {
		AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(id);
		if(anoLectivo != null)
			return anoLectivo;
		throw new RegistoNaoEncontradoException("Ano lectivo não encontrado.");
	}
}
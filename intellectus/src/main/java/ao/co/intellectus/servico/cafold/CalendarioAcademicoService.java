package ao.co.intellectus.servico.cafold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.CalendarioAcademicoView;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.configuracao.CalendarioAcademico;
import ao.co.intellectus.repository.CalendarioAcademicoRepository;
import ao.co.intellectus.servico.exception.DataInvalidaException;

@Service
public class CalendarioAcademicoService {

	@Autowired
	private CalendarioAcademicoRepository calendarioAcademicoRepository; 
	@Autowired
	private AnoLectivoService anoLectivoService;
	@Autowired
	private DataService dataService;

	public void salvar(CalendarioAcademicoView calendario) {
		CalendarioAcademico calendarioAcademico = new CalendarioAcademico();
		AnoLectivo anoLectivo = this.anoLectivoService.anoLectivo(calendario.getAnoLectivo());
		calendario = this.validarDatas(calendario);
		calendarioAcademico.setId(calendario.getId());
		calendarioAcademico.setAnoLectivo(anoLectivo);
		calendarioAcademico.setInicioDaCandidatura(calendario.getInicioDaCandidatura());
		calendarioAcademico.setFimDaCandidatura(calendario.getFimDaCandidatura());
		calendarioAcademico.setInicioDaInscricao(calendario.getInicioDaInscricao());
		calendarioAcademico.setFimDaInscricao(calendario.getFimDaInscricao());
		this.calendarioAcademicoRepository.save(calendarioAcademico);
	}
	
	private CalendarioAcademicoView validarDatas(CalendarioAcademicoView calendario) {
		if(!this.dataService.validarDataFinal(calendario.getInicioDaCandidatura(), calendario.getFimDaCandidatura()))
			throw new DataInvalidaException("A data final de candidatura não pode ser inferior a data incial");
		if(!this.dataService.validarDataFinal(calendario.getInicioDaInscricao(), calendario.getFimDaInscricao()))
			throw new DataInvalidaException("A data final da inscrição não pode ser inferior a data incial");
		return calendario;
	}
	
}

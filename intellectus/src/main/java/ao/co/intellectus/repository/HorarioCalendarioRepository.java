package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.HorarioCalendario;
import ao.co.intellectus.model.Semana;
import ao.co.intellectus.model.Semestre;

public interface HorarioCalendarioRepository extends CrudRepository<HorarioCalendario,Integer>{
	
	public List<HorarioCalendario> findByAnoLectivoAndDiaSemanaNomeAndSemestre(AnoLectivo anoLectivo,Semana semana,Semestre semestre);

}

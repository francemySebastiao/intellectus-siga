package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.HorarioProfessor;

public interface HorarioProfessorRepository extends CrudRepository<HorarioProfessor,Integer>{
	
	public List<HorarioProfessor> findByDocente(Integer professor);
	//public HorarioProfessor findByDocenteAndAnoLectivoAndDisciplinaId();
	

}

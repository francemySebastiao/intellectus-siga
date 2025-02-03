package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.MapaMensalidadeAluno;

public interface MapaMensalidadeAlunoRepository extends CrudRepository<MapaMensalidadeAluno,Integer>{
	public MapaMensalidadeAluno findByNumeroDeAluno(Integer numeroDeAluno);
	
	public List<MapaMensalidadeAluno> findByAnoLectivoAnoLectivo(Integer anoLectivo);
	
}

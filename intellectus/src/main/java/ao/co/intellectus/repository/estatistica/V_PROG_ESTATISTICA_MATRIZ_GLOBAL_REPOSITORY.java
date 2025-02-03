package ao.co.intellectus.repository.estatistica;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.estatistica.V_PROG_ESTATISTICA_MATRIZ_GLOBAL;

public interface V_PROG_ESTATISTICA_MATRIZ_GLOBAL_REPOSITORY extends JpaRepository<V_PROG_ESTATISTICA_MATRIZ_GLOBAL, Integer> {
	
	public List<V_PROG_ESTATISTICA_MATRIZ_GLOBAL> findByAnoLectivoAndGrau(Integer anoLectivo,String grau);

}

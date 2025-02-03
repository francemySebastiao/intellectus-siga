package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.CursoEnsinoMedio;

public interface CursoEnsinoMedioRepository extends CrudRepository<CursoEnsinoMedio,Integer>{

	CursoEnsinoMedio findBydescricao(String cursoEnsinoMedio);

}

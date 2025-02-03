package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.InscritosPorAno;

public interface InscritoPorAnoRepository extends CrudRepository<InscritosPorAno, Integer> {
    public InscritosPorAno findByAnoLectivo(Integer anoLectivo);
}

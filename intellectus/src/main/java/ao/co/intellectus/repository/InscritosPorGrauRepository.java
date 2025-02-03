package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.InscritosPorGrau;

public interface InscritosPorGrauRepository extends CrudRepository<InscritosPorGrau, Integer> {
    public List<InscritosPorGrau> findByAno(Integer ano);
}

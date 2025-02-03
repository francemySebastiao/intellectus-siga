package ao.co.intellectus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.Contador;

public interface ContradorRepository extends JpaRepository<Contador,Integer>{

}

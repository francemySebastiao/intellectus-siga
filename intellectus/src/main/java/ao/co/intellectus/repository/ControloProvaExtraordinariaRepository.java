package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.ControloProvaExtraordianaria;

public interface ControloProvaExtraordinariaRepository extends JpaRepository<ControloProvaExtraordianaria, Integer> {
	public List<ControloProvaExtraordianaria> findByNumeroDeAluno(Integer numero);
	
	
}

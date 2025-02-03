package ao.co.intellectus.repository.alerta;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.alerta.Campanha;

public interface CampanhaRepository extends JpaRepository<Campanha, Integer> {

	Campanha findByDesigancao(String designacao);

}

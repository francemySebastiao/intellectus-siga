package ao.co.intellectus.repository.secretaria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.secretaria.MatriculaOnline;

public interface MatriculaOnlineRepository extends JpaRepository<MatriculaOnline, Integer>{
    public List<MatriculaOnline> findByEventoExecutado(boolean eventoExecutado);
}
 
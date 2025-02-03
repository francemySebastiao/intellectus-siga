package ao.co.intellectus.repository.alerta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.alerta.CampanhaPersonalizada;

public interface CampanhaPersonalizadaRepository extends JpaRepository<CampanhaPersonalizada, Long>{
    public List<CampanhaPersonalizada> findByEnviado(boolean enviado);
}

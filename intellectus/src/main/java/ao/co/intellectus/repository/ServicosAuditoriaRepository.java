package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.ServicosAuditoria;

public interface ServicosAuditoriaRepository extends CrudRepository<ServicosAuditoria,Integer>{
   public List<ServicosAuditoria> findByEstado(boolean estado);
}

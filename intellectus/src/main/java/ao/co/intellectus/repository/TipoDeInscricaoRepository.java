package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.TipoInscricao;

public interface TipoDeInscricaoRepository extends JpaRepository<TipoInscricao, Integer>{
   public List<TipoInscricao> findByStatus(boolean status);
   
   public TipoInscricao findByDescricao(String descricao);
   
}

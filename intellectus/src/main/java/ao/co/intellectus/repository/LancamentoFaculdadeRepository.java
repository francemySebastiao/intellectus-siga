package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.Faculdade;
import ao.co.intellectus.model.LancamentoFaculdade;
import ao.co.intellectus.model.Permissao;
import ao.co.intellectus.model.Prova;

public interface LancamentoFaculdadeRepository extends JpaRepository<LancamentoFaculdade,Integer>{
    public List<LancamentoFaculdade> findByFaculdadeAndProvaAndPermissao(Faculdade faculdade,Prova prova,Permissao permissao);
}

package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.AutorizacaoRegistroInscricao;
import ao.co.intellectus.model.Grau;
import ao.co.intellectus.model.Prova;
import ao.co.intellectus.model.TipoDisciplina;

public interface AutorizacaoInscricaoRepository extends JpaRepository<AutorizacaoRegistroInscricao,Integer>{
     public List<AutorizacaoRegistroInscricao> findByEmCurso(boolean emCurso);
     public List<AutorizacaoRegistroInscricao> findByEmCursoAndProva(boolean emCurso,Prova prova);
     
     public List<AutorizacaoRegistroInscricao> findByEmCursoAndProvaAndTipoDisciplina(boolean emCurso,Prova prova,TipoDisciplina tipoDisciplina);
     
     public List<AutorizacaoRegistroInscricao> findByEmCursoAndProvaAndTipoDisciplinaAndGrau(boolean emCurso,Prova prova,TipoDisciplina tipoDisciplina,Grau grau);
     
}

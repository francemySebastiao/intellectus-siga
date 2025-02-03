package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.AutorizacaoLancamentoNota;
import ao.co.intellectus.model.Prova;
import ao.co.intellectus.model.TipoDisciplina;

public interface AutorizacaoLancamentoNrepository extends JpaRepository<AutorizacaoLancamentoNota,Integer>{
    
	public List<AutorizacaoLancamentoNota> findByEmCursoAndProvaEstado(boolean emCurso,boolean estado);
	
	public List<AutorizacaoLancamentoNota> findByEmCurso(boolean emCurso);
	
	public List<AutorizacaoLancamentoNota> findByEmCursoAndProvaEstadoAndTipoDisciplina(boolean emCurso,boolean estado,TipoDisciplina tipoDisciplina);
	
	
    public List<AutorizacaoLancamentoNota> findByEmCursoAndProva(boolean emCurso,Prova prova);
    
    public List<AutorizacaoLancamentoNota> findByEmCursoAndProvaAndTipoDisciplina(boolean emCurso,Prova prova,TipoDisciplina tipoDisciplina);
    
    //public List<AutorizacaoLancamentoNota> findByEmCursoAndProvaAndTipoDisciplinaAndFaculdade(boolean emCurso,Prova prova,TipoDisciplina tipoDisciplina,Faculdade faculdade);
   
    public List<AutorizacaoLancamentoNota> findByEmCursoAndTipoDisciplinaAndProva(boolean emCurso,TipoDisciplina tipoDisciplina,Prova prova);
    
}

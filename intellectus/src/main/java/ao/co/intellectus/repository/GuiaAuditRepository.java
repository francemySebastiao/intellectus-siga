package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaAudit;

public interface GuiaAuditRepository extends CrudRepository<GuiaAudit, Integer>{
    public Guia findById(Integer id);
    public List<Guia> findByAlunoAndLiquidadaAndAnulada(Aluno aluno,boolean liquidada,boolean anulada);
    public List<Guia> findByAlunoAndLiquidadaAndAnuladaOrderByDataVencimento(Aluno aluno,boolean liquidada,boolean anulada);
    public List<Guia> findByAlunoNumeroDeAlunoAndLiquidadaAndAnulada(String numero,boolean liquidada,boolean anulada);
    public List<Guia> findByLiquidadaAndAnulada(boolean liquidada,boolean anulada);
    public Guia findByNumeroGuiaAndLiquidadaAndAnulada(String numero,boolean liquidada,boolean anulada); 
    public Guia findByNumeroGuiaAndLiquidada(String numero,boolean liquidada);
    public List<Guia> findByAluno(Aluno aluno);
    public List<Guia> findByAlunoOrderByAnoLectivoDesc(Aluno aluno);
    public Guia findByNumeroGuia(String numero);
    public List<Guia> findByAlunoAndParaAcordoPagamento(Aluno aluno,boolean acordo);
    public Guia findByNumeroGuiaAndAndParaAcordoPagamento(String numero,boolean paraAcordoPagamento);
    public Guia findByBordero(String bordero);
}

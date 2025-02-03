package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaPagamentoHistorico;

public interface HistoricoGuiaPagamentoRepository extends CrudRepository<GuiaPagamentoHistorico, Integer>{
   public List<GuiaPagamentoHistorico> findByGuia(Guia guia);
   
   public GuiaPagamentoHistorico findByGuiaAndNumeroDeAluno(Guia guia, String NumeroDeAluno);
   public GuiaPagamentoHistorico findByAnoLectivoAndEmolumentoAndGuia(AnoLectivo anoLectivo,Emolumento emolumento,Guia guia);
   public List<GuiaPagamentoHistorico> findByNumeroDeAlunoAndGuiaAnulada(String NumeroDeAluno,boolean anulada);
   
   
   
   public List<GuiaPagamentoHistorico> findByEmolumentoBetween(Emolumento em1,Emolumento em2);
   public List<GuiaPagamentoHistorico> findByAnoLectivoAndNumeroDeAlunoAndEmolumentoBetween(AnoLectivo anoLectivo,String numero,Emolumento em1,Emolumento em2);
   public List<GuiaPagamentoHistorico> findByEmolumentoAndAlunoAndAnoLectivo(Emolumento emolumento,Aluno aluno,AnoLectivo anoLectivo);
   
   
   public List<GuiaPagamentoHistorico> findByEmolumentoAndAlunoAndAnoLectivoAndGuiaAnulada(Emolumento emolumento,Aluno aluno,AnoLectivo anoLectivo,boolean anulada);
   
   public List<GuiaPagamentoHistorico> findByEmolumentoAndAlunoAndAnoLectivoAndGuiaLiquidada(Emolumento emolumento,Aluno aluno,AnoLectivo anoLectivo,boolean liquidada);
   
   
   
   public List<GuiaPagamentoHistorico> findByGuiaAndEmolumento(Guia guia,Emolumento emolumento);
   
   public List<GuiaPagamentoHistorico> findByEmolumentoAndGuiaLiquidadaAndAnoLectivo(Emolumento emolumento,boolean liquidada,AnoLectivo anoLectivo);
   
   
   
   public List<GuiaPagamentoHistorico> findByAnoLectivoAndAlunoAndEmolumento(AnoLectivo anoLectivo,Aluno aluno,Emolumento emolumento);
   
   
   public List<GuiaPagamentoHistorico> findByAnoLectivoAndAlunoAndEmolumentoAndGuiaAnuladaAndGuiaLiquidada(AnoLectivo anoLectivo,Aluno aluno,Emolumento emolumento,boolean anulada,boolean liquidada);
   
   
   public List<GuiaPagamentoHistorico> findByAnoLectivoAndAlunoAndEmolumentoAndGuiaAnulada(AnoLectivo anoLectivo,Aluno aluno,Emolumento emolumento,boolean anulada);
   
   
   public List<GuiaPagamentoHistorico> findByGuiaAndEmolumentoId(Guia guia,Integer emolumento);
   
  


   
   
   //@Query("SELECT g.guia,g.valor FROM EmolumentoHistorico g")
   //public List<EmolumentoHistorico> ListEmolumentos(); 
}

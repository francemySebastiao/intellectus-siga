package ao.co.intellectus.repository;

import java.util.List;

import ao.co.intellectus.DTO.GuiaPagamentoCodigo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.Guia;

public interface GuiaPagamentoRepository extends CrudRepository<Guia, Integer>{
    public Guia findById(Integer id);
    public List<Guia> findByAlunoAndLiquidadaAndAnulada(Aluno aluno,boolean liquidada,boolean anulada);
    public List<Guia> findByAlunoAndLiquidadaAndAnuladaOrderByDataVencimento(Aluno aluno,boolean liquidada,boolean anulada);
    public List<Guia> findByAlunoNumeroDeAlunoAndLiquidadaAndAnulada(String numero,boolean liquidada,boolean anulada);
    public List<Guia> findByAlunoNumeroDeAlunoAndAnuladaOrderById(String numero,boolean anulada);
    public List<Guia> findByLiquidadaAndAnulada(boolean liquidada,boolean anulada);
    public Guia findByNumeroGuiaAndLiquidadaAndAnulada(String numero,boolean liquidada,boolean anulada);
    public Guia findByNumeroGuiaAndLiquidada(String numero,boolean liquidada);
    public List<Guia> findByAluno(Aluno aluno);
    public List<Guia> findByAlunoOrderByAnoLectivoDesc(Aluno aluno);
    
    public Guia findByNumeroGuia(String numero);
    
    public List<Guia> findByNumeroGuiaOrderByIdAsc(String numero);
    public List<Guia> findByNumeroGuiaAndNumeroDeAlunoOrderByIdAsc(String numero,Integer numeroAluno);
    
    public List<Guia> findByAlunoAndParaAcordoPagamento(Aluno aluno,boolean acordo);
    public Guia findByNumeroGuiaAndAndParaAcordoPagamento(String numero,boolean paraAcordoPagamento);
    public Guia findByBordero(String bordero);
    
    public List<Guia> findByGeradaOnlineAndGeradaReferencia(boolean geradaOnline,boolean geradaReferencia);
    
    /*
      private boolean geradaOnline;
	  private boolean geradaReferencia; 
     */
	public Page<Guia> findByNumeroDeAlunoAndAnuladaAndAnoLectivoAnoLectivo(String numeroAluno,
			boolean anulada, Integer anoLectivo, Pageable paginacao);

	public Page<Guia> findByNumeroDeAlunoAndAnoLectivoAnoLectivo(String numeroAluno, Integer anoLectivo,
			Pageable paginacao);

	public Page<Guia> findByNumeroDeAlunoAndAnulada(String numeroAluno, boolean anulada,
			Pageable paginacao);

	public Page<Guia> findByNumeroDeAluno(String numeroAluno, Pageable paginacao);
	
	@Query(value="SELECT * from T_GUIA_PAGAMENTO WHERE n_factura_proforma =:proforma OR n_factura_recibo =:proforma", nativeQuery=true)
	public Guia buscarProforma(@Param("proforma") String proforma);
	
	@Query(value="SELECT * from T_GUIA_PAGAMENTO WHERE n_factura_proforma =:proforma", nativeQuery=true)
	public Guia findProforma(@Param("proforma") String proforma);
	
	@Query(value="SELECT * from T_GUIA_PAGAMENTO WHERE n_factura_recibo =:recibo", nativeQuery=true)
	public Guia findFacturaRecibo(@Param("recibo") String recibo);

	@Query(value="SELECT * from V_NUMERO_FACTURA WHERE N_FACTURA_RECIBO =:documento", nativeQuery=true)
	public Guia buscarDocumentoRecibo(@Param("documento") String documento);
	
	@Query(value="SELECT * from V_NUMERO_FACTURA WHERE N_FACTURA_PROFORMA =:documento", nativeQuery=true)
	public Guia buscarDocumento(@Param("documento") String documento);
	
	@Query(value="SELECT * FROM V_PROG_PROCESSAMENTO_PROFORMA", nativeQuery=true)
	public List<Guia> BUSCAR_GUIA_SEM_PROFORMA_HASH();
	
	@Query(value="SELECT * FROM V_PROG_PROCESSAMENTO_FATURA_RECIBO", nativeQuery=true)
	public List<GuiaPagamentoCodigo> BUSCAR_GUIA_SEM_FACTURA_RECIBO_HASH();
}


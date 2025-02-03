package ao.co.intellectus.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.GuiaCandidatura;

public interface GuiaCandidaturaRepository extends JpaRepository<GuiaCandidatura, Integer> {
	public GuiaCandidatura findByCandidatoAndLiquidada(Candidato cadidato, boolean liquidada);

	public GuiaCandidatura findByNumeroGuia(String numeroGuia);

	public GuiaCandidatura findByCandidato(Candidato cadidato);

	public GuiaCandidatura findByNumeroGuiaAndLiquidada(String numeroGuia, boolean liquidada);

	@Query(value = "SELECT * FROM V_GERECAO_REFERENCIA_CANDIDATOS", nativeQuery = true)
	public List<GuiaCandidatura> buscarGuiasCandidaturaSemReferencia();

	// SELECT * FROM V_GUIA_NAO_VENCIDAS
	@Query(value = "SELECT * FROM V_GUIA_NAO_VENCIDAS", nativeQuery = true)
	public List<GuiaCandidatura> buscarGuiaCandidaturNaoVencidasENaoPagas();

	// SELECT A.* FROM T_GUIA_CANDIDATURA A WHERE A.ANO_LECTIVO_ID=21 AND
	// A.LIQUIDADA=1 AND A.NUMERO_GUIA='2021162190' AND A.VALOR=7000

	@Query(value = "SELECT A.* FROM T_GUIA_CANDIDATURA A WHERE A.ANO_LECTIVO_ID=21 AND A.LIQUIDADA=1 AND A.NUMERO_GUIA=?1 AND A.VALOR=?2", nativeQuery = true)
	public List<GuiaCandidatura> buscarGuiaParaComepensar(String numeroGuia, BigDecimal valor);

	@Query(value = "SELECT A.* FROM T_GUIA_CANDIDATURA A WHERE A.ANO_LECTIVO_ID=21 AND A.LIQUIDADA=0 AND A.NUMERO_GUIA=?1 AND A.VALOR=?2", nativeQuery = true)
	public List<GuiaCandidatura> buscarGuiaParaComepensarAGORA(String numeroGuia, BigDecimal valor);

	@Query(value = "SELECT * FROM T_GUIA_CANDIDATURA WHERE NUMERO_DE_CANDIDATO =:idCandidato", nativeQuery = true)
	public GuiaCandidatura burcarPorNumeroCandidato(@Param("idCandidato") Integer idCandidato);

	@Query(value = "SELECT * FROM T_GUIA_CANDIDATURA WHERE NUMERO_DE_CANDIDATO =:numeroCandidato AND NOME_EMOLUMENTO =:descricao", nativeQuery = true)
	public GuiaCandidatura burcarPorNumeroCandidatoEDescricaoEmolumento(
			@Param("numeroCandidato") Integer numeroCandidato, @Param("descricao") String descricao);

	@Query(value = "SELECT COUNT(*) +1 FROM T_GUIA_CANDIDATURA", nativeQuery = true)
	public Integer TOTAL_GUIA_CANDIDATUR_PLUS();
	
	@Query(value = "SELECT COUNT(*) FROM T_GUIA_CANDIDATURA", nativeQuery = true)
	public Integer TOTAL_GUIA_CANDIDATURA_1();
	
	@Query(value="SELECT * from T_GUIA_CANDIDATURA WHERE n_factura_proforma =:proforma", nativeQuery=true)
	public GuiaCandidatura buscarProforma(@Param("proforma") String proforma);
	
	@Query(value="SELECT * from T_GUIA_CANDIDATURA WHERE n_factura_recibo =:recibo", nativeQuery=true)
	public GuiaCandidatura buscarRecibo(@Param("recibo") String recibo);
	
	@Query(value="SELECT * from V_NUMERO_FACTURA WHERE N_FACTURA_PROFORMA =:documento", nativeQuery=true)
	public GuiaCandidatura buscarDocumento(@Param("documento") String documento);

}
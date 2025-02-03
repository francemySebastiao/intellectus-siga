package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.enumeracao.TipoFatura;

@Entity
@Table(name = "t_guia_candidatura")
public class GuiaCandidatura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private String numeroGuia;
	@OneToOne
	@JoinColumn(name = "numero_de_candidato")
	private Candidato candidato;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_emicao")
	private Date dataEmicao;
	private double valor;
	private boolean anulada;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_anulada")
	private Date dataAnulada;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_vencimento")
	private Date dataVencimento;
	private boolean liquidada;
	private boolean automaticamente;
	private boolean acordo;
	@Column(nullable=true)
	private Integer taxaCambio;
	private String motivoAnulacaoGuia;
	private String motivoAnulacaoRecibo;
	//TABELAS DE CONEXÃO
	@Temporal(TemporalType.DATE)
	private Date dataLiquidacao;
	@ManyToOne
	private AnoLectivo anoLectivo;
	@ManyToOne
	@JoinColumn(name = "codigo_instituicao")
	private Instituicao instituicao;
	@OneToOne
	@JoinColumn(name = "codigo_moeda")
	private Moeda moeda;
	private String bordero;
	private String referencia;
	// AUDITORIA
	@ManyToOne
	@JoinColumn(name = "codigo_usuario_emitiu")
	private Usuario usuarioEmitiu;
	@ManyToOne
	@JoinColumn(name = "codigo_usuario_liquidou")
	private Usuario usuarioLiquidou;
	@ManyToOne
	@JoinColumn(name = "codigo_usuario_anulou")
	private Usuario usuarioAnulou;
	@ManyToOne
	@JoinColumn(name = "codigo_usuario_acordo")
	private Usuario usuarioAplicouAcordo;
	
	@Column(name = "n_facturaProforma")
	private String numeroFacturaProforma;
	
	@Column(name = "n_facturaRecibo")
	private String numeroFacturaRecibo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_factura")
	private TipoFatura tipoFactura;
	
	private String hashProforma;
	private String hashFacturaRecibo;
	private String dataSistema;
	private String dataSistemaFr;
	private String dataSistemaAnulacao;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_emissao_fr")
	private Date dataEmissaoFr;
	
	

	public GuiaCandidatura() {
	}

	public GuiaCandidatura(Candidato candidato, Date dataEmicao, double valor, Date dataVencimento,boolean automaticamente) {
		this.candidato = candidato;
		this.dataEmicao = dataEmicao;
		this.valor = valor;
		this.dataVencimento = dataVencimento;
		this.automaticamente = automaticamente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public Date getDataEmicao() {
		return dataEmicao;
	}

	public void setDataEmicao(Date dataEmicao) {
		this.dataEmicao = dataEmicao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean isAnulada() {
		return anulada;
	}

	public void setAnulada(boolean anulada) {
		this.anulada = anulada;
	}

	public Date getDataAnulada() {
		return dataAnulada;
	}

	public void setDataAnulada(Date dataAnulada) {
		this.dataAnulada = dataAnulada;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public boolean isLiquidada() {
		return liquidada;
	}

	public void setLiquidada(boolean liquidada) {
		this.liquidada = liquidada;
	}

	public boolean isAutomaticamente() {
		return automaticamente;
	}

	public void setAutomaticamente(boolean automaticamente) {
		this.automaticamente = automaticamente;
	}

	public boolean isAcordo() {
		return acordo;
	}

	public void setAcordo(boolean acordo) {
		this.acordo = acordo;
	}

	public String getNumeroGuia() {
		return numeroGuia;
	}

	public void setNumeroGuia(String numeroGuia) {
		this.numeroGuia = numeroGuia;
	}

	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Date getDataLiquidacao() {
		return dataLiquidacao;
	}

	public void setDataLiquidacao(Date dataLiquidacao) {
		this.dataLiquidacao = dataLiquidacao;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getBordero() {
		return bordero;
	}

	public void setBordero(String bordero) {
		this.bordero = bordero;
	}

	public Integer getTaxaCambio() {
		return taxaCambio;
	}

	public void setTaxaCambio(Integer taxaCambio) {
		this.taxaCambio = taxaCambio;
	}

	public String getMotivoAnulacaoGuia() {
		return motivoAnulacaoGuia;
	}

	public void setMotivoAnulacaoGuia(String motivoAnulacaoGuia) {
		this.motivoAnulacaoGuia = motivoAnulacaoGuia;
	}

	public String getMotivoAnulacaoRecibo() {
		return motivoAnulacaoRecibo;
	}

	public void setMotivoAnulacaoRecibo(String motivoAnulacaoRecibo) {
		this.motivoAnulacaoRecibo = motivoAnulacaoRecibo;
	}

	public Moeda getMoeda() {
		return moeda;
	}

	public void setMoeda(Moeda moeda) {
		this.moeda = moeda;
	}

	public Usuario getUsuarioEmitiu() {
		return usuarioEmitiu;
	}

	public void setUsuarioEmitiu(Usuario usuarioEmitiu) {
		this.usuarioEmitiu = usuarioEmitiu;
	}

	public Usuario getUsuarioLiquidou() {
		return usuarioLiquidou;
	}

	public void setUsuarioLiquidou(Usuario usuarioLiquidou) {
		this.usuarioLiquidou = usuarioLiquidou;
	}

	public Usuario getUsuarioAnulou() {
		return usuarioAnulou;
	}

	public void setUsuarioAnulou(Usuario usuarioAnulou) {
		this.usuarioAnulou = usuarioAnulou;
	}

	public Usuario getUsuarioAplicouAcordo() {
		return usuarioAplicouAcordo;
	}

	public void setUsuarioAplicouAcordo(Usuario usuarioAplicouAcordo) {
		this.usuarioAplicouAcordo = usuarioAplicouAcordo;
	}

	public String getNumeroFacturaProforma() {
		return numeroFacturaProforma;
	}

	public void setNumeroFacturaProforma(String numeroFacturaProforma) {
		this.numeroFacturaProforma = numeroFacturaProforma;
	}

	public String getNumeroFacturaRecibo() {
		return numeroFacturaRecibo;
	}

	public void setNumeroFacturaRecibo(String numeroFacturaRecibo) {
		this.numeroFacturaRecibo = numeroFacturaRecibo;
	}

	public TipoFatura getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(TipoFatura tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public String getHashProforma() {
		return hashProforma;
	}

	public void setHashProforma(String hashProforma) {
		this.hashProforma = hashProforma;
	}

	public String getHashFacturaRecibo() {
		return hashFacturaRecibo;
	}

	public void setHashFacturaRecibo(String hashFacturaRecibo) {
		this.hashFacturaRecibo = hashFacturaRecibo;
	}

	public String getDataSistema() {
		return dataSistema;
	}

	public void setDataSistema(String dataSistema) {
		this.dataSistema = dataSistema;
	}

	public String getDataSistemaFr() {
		return dataSistemaFr;
	}

	public void setDataSistemaFr(String dataSistemaFr) {
		this.dataSistemaFr = dataSistemaFr;
	}

	public Date getDataEmissaoFr() {
		return dataEmissaoFr;
	}

	public void setDataEmissaoFr(Date dataEmissaoFr) {
		this.dataEmissaoFr = dataEmissaoFr;
	}

	public String getDataSistemaAnulacao() {
		return dataSistemaAnulacao;
	}

	public void setDataSistemaAnulacao(String dataSistemaAnulacao) {
		this.dataSistemaAnulacao = dataSistemaAnulacao;
	}
	
	
}

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
@Table(name = "t_guia_pagamento")
public class Guia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private String numeroGuia;
	private String numeroDeAluno;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_emissao")
	private Date dataEmicao;
	private double valor;
	private Boolean anulada;
	@Column(name = "data_anulada")
	@Temporal(TemporalType.DATE)
	private Date dataAnulada;
	@Column(name = "data_vencimento")
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	@Temporal(TemporalType.DATE)
	private Date dataAlteracao;

	@Column(name = "data_anulacao_recibo")
	@Temporal(TemporalType.DATE)
	private Date dataAnulacaoRecibo;//TODO - ADICIONAR ESSA COLUNA NA BASE DE TESTE E PRODUÇÃO.
	
	private Boolean liquidada;
	private Boolean alterada;
	private Boolean automaticamente;
	@Column(name ="acordo")
	private Boolean acordo;

	@Temporal(TemporalType.DATE)
	private Date dataLiquidacao;
	private String motivoAnulacaoGuia;
	private String motivoAnulacaoRecibo;
	private float taxaCambio;
	@OneToOne
	@JoinColumn(name = "codigo_moeda")
	private Moeda moeda;
	@ManyToOne
	@JoinColumn(name = "codigo_instituicao")
	private Instituicao instituicao;
	private String referencia;
	@ManyToOne
	@JoinColumn(name = "codigo_aluno")
	private Aluno aluno;
	@ManyToOne
	@JoinColumn(name = "codigo_ano_Lectivo")
	private AnoLectivo anoLectivo;
	private String bordero;
	// adicional auditoria
	@Column(nullable = true)
	private Boolean paraAcordoPagamento;
	// AUDITORIA
	@ManyToOne
	@JoinColumn(name = "codigo_usuario_emitiu")
	private Usuario usuarioEmitiu;
	@ManyToOne
	@JoinColumn(name = "codigo_usuario_liquidou")
	private Usuario usuarioLiquidou;
	@ManyToOne
	@JoinColumn(name = "codigo_usuario_alterou")
	private Usuario usuarioAlterou;
	@ManyToOne
	@JoinColumn(name = "codigo_usuario_anulou")
	private Usuario usuarioAnulou;
	@ManyToOne
	@JoinColumn(name = "codigo_usuario_acordo")
	private Usuario usuarioAplicouAcordo;
	@Column(nullable = true)
	private Boolean gerouCredito;
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimaModificacao;
	@Column(nullable=true)
	private Boolean geradaOnline;
	@Column(nullable=true)
	private Boolean geradaReferencia;
	
	@Column(name = "liquidacao_agt")
	private Boolean liquidacaoAGT;
	
	@Column(name = "n_facturaProforma", unique = true)
	private String numeroFacturaProforma;
	
	@Column(name = "n_facturaRecibo", unique = true)
	private String numeroFacturaRecibo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_factura")
	private TipoFatura tipoFactura;
	
	private String hashProforma;
	private String hashFacturaRecibo;
	private String dataSistema;
	private String dataSistemaFr;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_emissao_fr")
	private Date dataEmissaoFr;
	private Boolean liquidacaoCredito;
	
	

	public Guia() {
	}

	public Guia(Aluno aluno, String numeroDeAluno, Date dataEmicao, double valor, Date dataVencimento,
			Boolean automaticamente) {
		this.aluno = aluno;
		this.dataEmicao = dataEmicao;
		this.valor = valor;
		this.dataVencimento = dataVencimento;
		this.automaticamente = automaticamente;
		this.numeroDeAluno = numeroDeAluno;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroGuia() {
		return numeroGuia;
	}

	public void setNumeroGuia(String numeroGuia) {
		this.numeroGuia = numeroGuia;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
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

	public Boolean isAnulada() {
		return anulada;
	}

	public void setAnulada(Boolean anulada) {
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

	public Boolean isLiquidada() {
		return liquidada;
	}

	public void setLiquidada(Boolean liquidada) {
		this.liquidada = liquidada;
	}

	public Boolean isAutomaticamente() {
		return automaticamente;
	}

	public void setAutomaticamente(Boolean automaticamente) {
		this.automaticamente = automaticamente;
	}

	public Boolean isAcordo() {
		return acordo;
	}

	public void setAcordo(Boolean acordo) {
		this.acordo = acordo;
	}

	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public String getNumeroDeAluno() {
		return numeroDeAluno;
	}

	public void setNumeroDeAluno(String numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}

	public Date getDataLiquidacao() {
		return dataLiquidacao;
	}

	public void setDataLiquidacao(Date dataLiquidacao) {
		this.dataLiquidacao = dataLiquidacao;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Moeda getMoeda() {
		return moeda;
	}

	public void setMoeda(Moeda moeda) {
		this.moeda = moeda;
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

	public float getTaxaCambio() {
		return taxaCambio;
	}

	public void setTaxaCambio(float taxaCambio) {
		this.taxaCambio = taxaCambio;
	}

	public String getBordero() {
		return bordero;
	}

	public void setBordero(String bordero) {
		this.bordero = bordero;
	}

	public Boolean isParaAcordoPagamento() {
		return paraAcordoPagamento;
	}

	public void setParaAcordoPagamento(Boolean paraAcordoPagamento) {
		this.paraAcordoPagamento = paraAcordoPagamento;
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

	public Boolean isGerouCredito() {
		return gerouCredito;
	}

	public void setGerouCredito(Boolean gerouCredito) {
		this.gerouCredito = gerouCredito;
	}

	public Date getUltimaModificacao() {
		return ultimaModificacao;
	}

	public void setUltimaModificacao(Date ultimaModificacao) {
		this.ultimaModificacao = ultimaModificacao;
	}

	public Boolean isGeradaOnline() {
		return geradaOnline;
	}

	public void setGeradaOnline(Boolean geradaOnline) {
		this.geradaOnline = geradaOnline;
	}

	public Boolean isGeradaReferencia() {
		return geradaReferencia;
	}

	public void setGeradaReferencia(Boolean geradaReferencia) {
		this.geradaReferencia = geradaReferencia;
	}

	public Date getDataAnulacaoRecibo() {
		return dataAnulacaoRecibo;
	}

	public void setDataAnulacaoRecibo(Date dataAnulacaoRecibo) {
		this.dataAnulacaoRecibo = dataAnulacaoRecibo;
	}

	public Boolean getAnulada() {
		return anulada;
	}

	public Boolean getLiquidada() {
		return liquidada;
	}

	public Boolean getAutomaticamente() {
		return automaticamente;
	}

	public Boolean getAcordo() {
		return acordo;
	}

	public Boolean getParaAcordoPagamento() {
		return paraAcordoPagamento;
	}

	public Boolean getGerouCredito() {
		return gerouCredito;
	}

	public Boolean getGeradaOnline() {
		return geradaOnline;
	}

	public Boolean getGeradaReferencia() {
		return geradaReferencia;
	}

	public Boolean getLiquidacaoAGT() {
		return liquidacaoAGT;
	}

	public void setLiquidacaoAGT(Boolean liquidacaoAGT) {
		this.liquidacaoAGT = liquidacaoAGT;
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

	public Boolean isAlterada() {
		return alterada;
	}

	public void setAlterada(Boolean alterada) {
		this.alterada = alterada;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Usuario getUsuarioAlterou() {
		return usuarioAlterou;
	}

	public void setUsuarioAlterou(Usuario usuarioAlterou) {
		this.usuarioAlterou = usuarioAlterou;
	}

	public Boolean getAlterada() {
		return alterada;
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

	public Boolean isLiquidacaoCredito() {
		return liquidacaoCredito;
	}

	public void setLiquidacaoCredito(Boolean liquidacaoCredito) {
		this.liquidacaoCredito = liquidacaoCredito;
	}

	@Override
	public String toString() {
		return "GUIA: [NÚMERO GUIA=" + numeroGuia + "]";
	}
	
}
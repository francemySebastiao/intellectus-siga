package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_guia_pagamento_audit")
public class GuiaAudit {
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
	private boolean anulada;
	@Column(name = "data_anulada")
	@Temporal(TemporalType.DATE)
	private Date dataAnulada;
	@Column(name = "data_vencimento")
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	
	private boolean liquidada;
	private boolean automaticamente;
	private boolean acordo;
	
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
	//adicional auditoria
	@Column(nullable=true)
	private boolean paraAcordoPagamento;	
	//AUDITORIA
	@ManyToOne
	@JoinColumn(name="codigo_usuario_emitiu")
	private Usuario usuarioEmitiu;
	@ManyToOne
	@JoinColumn(name="codigo_usuario_liquidou")
	private Usuario usuarioLiquidou;
	@ManyToOne
	@JoinColumn(name="codigo_usuario_anulou")
	private Usuario usuarioAnulou;
	@ManyToOne
	@JoinColumn(name="codigo_usuario_acordo")
	private Usuario usuarioAplicouAcordo;
	@Column(nullable=true) 
	private boolean gerouCredito;
	
	public GuiaAudit() {
	}
	public GuiaAudit(Aluno aluno,String numeroDeAluno, Date dataEmicao, double valor, Date dataVencimento, boolean automaticamente) {
		this.aluno = aluno;
		this.dataEmicao = dataEmicao;
		this.valor = valor;
		this.dataVencimento = dataVencimento;
		this.automaticamente = automaticamente;
		this.numeroDeAluno=numeroDeAluno;
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
	public boolean isParaAcordoPagamento() {
		return paraAcordoPagamento;
	}
	public void setParaAcordoPagamento(boolean paraAcordoPagamento) {
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
	public boolean isGerouCredito() {
		return gerouCredito;
	}
	public void setGerouCredito(boolean gerouCredito) {
		this.gerouCredito = gerouCredito;
	}	
}
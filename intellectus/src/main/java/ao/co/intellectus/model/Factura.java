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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.enumeracao.EstadoFactura;

@Entity
@Table(name = "t_factura")
public class Factura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "codigo_empresa")
	private EmpresaConvenio empresaConvenio;
	@Column(name = "n_factura")
	private String nuneroFactura;
	@Enumerated(EnumType.STRING)
	@Column(name = "estado_factura")
	private EstadoFactura estadoFactura;
	private Boolean liquidacao;
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;
	@ManyToOne
	@JoinColumn(name = "codigo_usuario_emitiu")
	private Usuario usuarioEmitiu;
	@Temporal(TemporalType.DATE)
	private Date dataLiquidacao;
	@ManyToOne
	@JoinColumn(name = "codigo_usuario_liquidou")
	private Usuario usuarioLiquidou;
	@Temporal(TemporalType.DATE)
	private Date dataAnulacao;
	@Temporal(TemporalType.DATE)
	private Date dataAlteracao;
	@ManyToOne
	@JoinColumn(name = "codigo_usuario_anulou")
	private Usuario usuarioAnulou;
	@ManyToOne
	@JoinColumn(name = "codigo_usuario_alterou")
	private Usuario usuarioAlterou;
	private double valor;
	@Column(name = "motivo_anulacao")
	private String motivoAnulacao;
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	private String hash;
	private String dataSistema;
	
	private Boolean alterada;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public EmpresaConvenio getEmpresaConvenio() {
		return empresaConvenio;
	}
	public void setEmpresaConvenio(EmpresaConvenio empresaConvenio) {
		this.empresaConvenio = empresaConvenio;
	}
	public String getNuneroFactura() {
		return nuneroFactura;
	}
	public void setNuneroFactura(String nuneroFactura) {
		this.nuneroFactura = nuneroFactura;
	}
	public EstadoFactura getEstadoFactura() {
		return estadoFactura;
	}
	public void setEstadoFactura(EstadoFactura estadoFactura) {
		this.estadoFactura = estadoFactura;
	}
	public Boolean getLiquidacao() {
		return liquidacao;
	}
	public void setLiquidacao(Boolean liquidacao) {
		this.liquidacao = liquidacao;
	}
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public Usuario getUsuarioEmitiu() {
		return usuarioEmitiu;
	}
	public void setUsuarioEmitiu(Usuario usuarioEmitiu) {
		this.usuarioEmitiu = usuarioEmitiu;
	}
	public Date getDataLiquidacao() {
		return dataLiquidacao;
	}
	public void setDataLiquidacao(Date dataLiquidacao) {
		this.dataLiquidacao = dataLiquidacao;
	}
	public Usuario getUsuarioLiquidou() {
		return usuarioLiquidou;
	}
	public void setUsuarioLiquidou(Usuario usuarioLiquidou) {
		this.usuarioLiquidou = usuarioLiquidou;
	}
	public Date getDataAnulacao() {
		return dataAnulacao;
	}
	public void setDataAnulacao(Date dataAnulacao) {
		this.dataAnulacao = dataAnulacao;
	}
	public Usuario getUsuarioAnulou() {
		return usuarioAnulou;
	}
	public void setUsuarioAnulou(Usuario usuarioAnulou) {
		this.usuarioAnulou = usuarioAnulou;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getMotivoAnulacao() {
		return motivoAnulacao;
	}
	public void setMotivoAnulacao(String motivoAnulacao) {
		this.motivoAnulacao = motivoAnulacao;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	public String getDataSistema() {
		return dataSistema;
	}
	public void setDataSistema(String dataSistema) {
		this.dataSistema = dataSistema;
	}
	public Boolean getAlterada() {
		return alterada;
	}
	public void setAlterada(Boolean alterada) {
		this.alterada = alterada;
	}
	
	public Boolean isAlterada() {
		return alterada;
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
	
}

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
@Table(name = "t_recibo")
public class Recibo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "n_Recibo")
	private String numeroRecibo;
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;
	@Temporal(TemporalType.DATE)
	private Date dataAlteracao;
	@ManyToOne
	@JoinColumn(name = "codigo_usuario_emitiu")
	private Usuario usuarioEmitiu;
	@ManyToOne
	@JoinColumn(name = "codigo_usuario_alterou")
	private Usuario usuarioalterou;
	@ManyToOne
	@JoinColumn(name = "id_factura")
	private Factura idFactura;
	@Column(name = "valor_pago")
	private Double valorPago;
	@Column(name = "numero_operacao")
	private String numeroOperacao;
	@Temporal(TemporalType.DATE)
	private Date dataAnulacao;
	@Column(name = "motivo_anulacao")
	private String motivoAnulacao;
	@ManyToOne
	@JoinColumn(name = "codigo_usuario_anulou")
	private Usuario usuarioAnulou;
	@Enumerated(EnumType.STRING)
	@Column(name = "estado_recibo")
	private EstadoFactura estadoRecibo;
	@ManyToOne
	@JoinColumn(name = "codigo_empresa")
	private EmpresaConvenio empresaConvenio;
	private Boolean alterada;
	private String dataSistema;
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumeroRecibo() {
		return numeroRecibo;
	}
	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
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
	public Factura getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Factura idFactura) {
		this.idFactura = idFactura;
	}
	public Double getValorPago() {
		return valorPago;
	}
	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}
	public String getNumeroOperacao() {
		return numeroOperacao;
	}
	public void setNumeroOperacao(String numeroOperacao) {
		this.numeroOperacao = numeroOperacao;
	}
	public Date getDataAnulacao() {
		return dataAnulacao;
	}
	public void setDataAnulacao(Date dataAnulacao) {
		this.dataAnulacao = dataAnulacao;
	}
	public String getMotivoAnulacao() {
		return motivoAnulacao;
	}
	public void setMotivoAnulacao(String motivoAnulacao) {
		this.motivoAnulacao = motivoAnulacao;
	}
	public Usuario getUsuarioAnulou() {
		return usuarioAnulou;
	}
	public void setUsuarioAnulou(Usuario usuarioAnulou) {
		this.usuarioAnulou = usuarioAnulou;
	}
	public EstadoFactura getEstadoRecibo() {
		return estadoRecibo;
	}
	public void setEstadoRecibo(EstadoFactura estadoRecibo) {
		this.estadoRecibo = estadoRecibo;
	}
	public EmpresaConvenio getEmpresaConvenio() {
		return empresaConvenio;
	}
	public void setEmpresaConvenio(EmpresaConvenio empresaConvenio) {
		this.empresaConvenio = empresaConvenio;
	}
	
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public Usuario getUsuarioalterou() {
		return usuarioalterou;
	}
	public void setUsuarioalterou(Usuario usuarioalterou) {
		this.usuarioalterou = usuarioalterou;
	}
	public Boolean getAlterada() {
		return alterada;
	}
	public void setAlterada(Boolean alterada) {
		this.alterada = alterada;
	}
	public String getDataSistema() {
		return dataSistema;
	}
	public void setDataSistema(String dataSistema) {
		this.dataSistema = dataSistema;
	}
	
}

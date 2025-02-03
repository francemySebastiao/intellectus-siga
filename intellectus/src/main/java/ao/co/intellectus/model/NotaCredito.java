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

import ao.co.intellectus.model.enumeracao.TipoDoc;

@Entity
@Table(name = "t_nota_credito")
public class NotaCredito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "n_notaCredito")
	private String numeroNotaCredito;
	@ManyToOne
	@JoinColumn(name = "id_guiaPagamento")
	private Guia idGuia;
	@ManyToOne
	@JoinColumn(name = "id_guiaCandidatura")
	private GuiaCandidatura idGuiaCandidatura;
	@ManyToOne
	@JoinColumn(name = "id_factura")
	private Factura idFactura;
	@ManyToOne
	@JoinColumn(name = "id_recibo")
	private Recibo idRecibo;
	@Enumerated(EnumType.STRING)
	private TipoDoc tipoDoc;
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;
	@ManyToOne
	@JoinColumn(name = "codigo_usuario_emitiu")
	private Usuario usuarioEmitiu;
	private Double valor;
	private String dataSistema;
	private String hash;
	private Boolean alteracao;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumeroNotaCredito() {
		return numeroNotaCredito;
	}
	public void setNumeroNotaCredito(String numeroNotaCredito) {
		this.numeroNotaCredito = numeroNotaCredito;
	}
	public Guia getIdGuia() {
		return idGuia;
	}
	public void setIdGuia(Guia idGuia) {
		this.idGuia = idGuia;
	}
	public Factura getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Factura idFactura) {
		this.idFactura = idFactura;
	}
	public Recibo getIdRecibo() {
		return idRecibo;
	}
	public void setIdRecibo(Recibo idRecibo) {
		this.idRecibo = idRecibo;
	}
	public TipoDoc getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(TipoDoc tipoDoc) {
		this.tipoDoc = tipoDoc;
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
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getDataSistema() {
		return dataSistema;
	}
	public void setDataSistema(String dataSistema) {
		this.dataSistema = dataSistema;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public GuiaCandidatura getIdGuiaCandidatura() {
		return idGuiaCandidatura;
	}
	public void setIdGuiaCandidatura(GuiaCandidatura idGuiaCandidatura) {
		this.idGuiaCandidatura = idGuiaCandidatura;
	}
	public Boolean getAlteracao() {
		return alteracao;
	}
	public void setAlteracao(Boolean alteracao) {
		this.alteracao = alteracao;
	}
	
	
}
